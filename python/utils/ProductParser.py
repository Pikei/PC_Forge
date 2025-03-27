from selenium import webdriver
from selenium.webdriver.common.by import By

from product.Processor import Processor
from product.Product import Product
from product.ProductCategory import UrlCategory, ProductCategory
from product.RAM import RAM
from utils.CommonUtils import CommonUtils
from utils.WebUtil import WebUtil


class ProductParser:
    """
    Klasa pobierająca i przetwarzająca dane z adresu URL na obiekty klas produktów
    """

    def __init__(self, driver: webdriver.Chrome, web_util: WebUtil):
        """
        Konstruktor klasy ProductParser
        :param driver: obiekt klasy ``WebDriver``
        :param web_util: obiekt klasy ``WebUtil``
        """
        self.url = "https://www.morele.net/"
        self.driver = driver
        self.util = web_util
        CommonUtils.directory_exists("images")

    def get_product_description(self, product_name):
        """
        Pobiera wiersze opisu produktu przekształcając je na kod HTML
        :param product_name: Nazwa produktu będąca alternatywnym nagłówkiem w przypadku jego braku w danym wierszu opisu
        :return: Kod HTML opisu produktu
        """
        description = ""
        self.util.expand_description()

        desc_rows = self.util.get_elements(By.CSS_SELECTOR, "div.panel-description div.row div.text1", timeout=15)
        if len(desc_rows) == 0:
            desc_rows = self.util.get_elements(By.CSS_SELECTOR, "div.auto-description div.desc-items div.row",
                                               timeout=15)
        if len(desc_rows) == 0:
            description += self.util.get_description_row(self.util.get_element(By.CLASS_NAME, "panel-description"),
                                                         product_name)
        else:
            for row in desc_rows:
                description += self.util.get_description_row(row, product_name)
        return description

    def parse_product(self, url: str):
        """
        Pobiera dane wspólne dla wszystkich produktów z podanego adresu URL.
        :param url: Adres URL do produktu
        :return: obiekt odpowiedniej klasy rozszerzającej ``Product`` lub **None** jeśli nie znaleziono
        kluczowych elementów na stronie (tabela specyfikacji, cena, kod producenta)
        """
        print("Parsing:", url)

        if not self.util.load_page(url, By.CLASS_NAME, "product-specification__table"):
            print("FAIL: product does not have specification table")
            return None

        price = self.util.get_element(By.CLASS_NAME, "product-price")
        if price is None:
            print("FAIL: Product is unavailable")
            return None

        spec_rows = self.util.get_elements(By.CLASS_NAME, "specification__row")

        producer_code: str = CommonUtils.get_value_from_spec_row(spec_rows, "Kod producenta")
        if producer_code == "":
            print("FAIL: Unknown producer code")
            return None

        product_price: float = CommonUtils.extract_float(price.text)
        name: str = self.util.get_element(By.CSS_SELECTOR, "h1.prod-name").text
        producer: str = CommonUtils.get_value_from_spec_row(spec_rows, "Producent")

        product = Product(name, producer, "", "", product_price, producer_code)

        match self.util.get_elements(By.CSS_SELECTOR, "a.main-breadcrumb")[-1].get_attribute("href"):
            case UrlCategory.CPU:
                product = self.parse_cpu(product, spec_rows)
            case UrlCategory.RAM:
                product = self.parse_ram(product, spec_rows)

        if product is not None:
            self.util.save_image(producer_code)
            print("Parsed:", product.get_name())
        return product

    def parse_cpu(self, product: Product, spec_rows):
        """
        Pobiera i przetwarza dane z adresu URL na obiekt klasy ``Processor``
        :param product: obiekt klasy ``Product``, zawierający uniwersalne dane dla wszystkich typów produktów
        :param spec_rows: wiersze tabeli specyfikacji
        :return: obiekt klasy ``Processor`` lub **None** jeśli pakowanie jest inne niż OEM, lub BOX
        """
        pack = str(CommonUtils.get_value_from_spec_row(spec_rows, "Wersja opakowania"))
        if pack != "BOX" and pack != "OEM":
            print("Unknown Packaging")
            return None

        product.set_name(product.get_name()[:product.get_name().find(",")])

        product.set_description(self.get_product_description(product.get_name()))

        product.set_category(str(ProductCategory.CPU))

        line = CommonUtils.get_value_from_spec_row(spec_rows, "Linia")

        model = product.get_name().split().pop(-1)

        spec_value = CommonUtils.get_value_from_spec_row(spec_rows, "Liczba rdzeni")
        num_of_cores = CommonUtils.extract_int(spec_value)

        spec_value = CommonUtils.get_value_from_spec_row(spec_rows, "Liczba wątków")
        num_of_threads = CommonUtils.extract_int(spec_value)

        socket = CommonUtils.get_value_from_spec_row(spec_rows, "Typ gniazda")

        spec_value = CommonUtils.get_value_from_spec_row(spec_rows, "Odblokowany mnożnik")
        unlocked = CommonUtils.translate_to_bool(spec_value)

        spec_value = CommonUtils.get_value_from_spec_row(spec_rows, "Częstotliwość taktowania procesora")
        frequency = CommonUtils.extract_float(spec_value)

        spec_value = CommonUtils.get_value_from_spec_row(spec_rows, "Częstotliwość maksymalna Turbo")

        max_frequency = CommonUtils.extract_float(spec_value)

        integrated_graphics_unit = CommonUtils.get_value_from_spec_row(spec_rows, "Zintegrowany układ graficzny")
        if integrated_graphics_unit == "Nie posiada":
            integrated_graphics_unit = None

        spec_value = CommonUtils.get_value_from_spec_row(spec_rows, "TDP")
        tdp = CommonUtils.extract_int(spec_value)

        spec_value = CommonUtils.get_value_from_spec_row(spec_rows, "Załączone chłodzenie")
        cooler_included = CommonUtils.translate_to_bool(spec_value)

        return Processor(product.get_name(), product.get_producer(), product.get_category(), product.get_description(),
                         product.get_price(), product.get_producer_code(), line, model, num_of_cores, num_of_threads,
                         socket, unlocked, frequency, max_frequency, integrated_graphics_unit, tdp, cooler_included,
                         pack)

    def parse_ram(self, product, spec_rows):
        """
        Pobiera i przetwarza dane z adresu URL na obiekt klasy ``RAM``
        :param product: obiekt klasy ``Product``, zawierający uniwersalne dane dla wszystkich typów produktów
        :param spec_rows: wiersze tabeli specyfikacji
        :return: obiekt klasy ``RAM``
        """
        product.set_name(product.get_name()[:product.get_name().find(",")])
        line = CommonUtils.get_value_from_spec_row(spec_rows, "Linia")
        memory_type = CommonUtils.get_value_from_spec_row(spec_rows, "Liczba modułów")

        # Pojemność łączna na stronie wylistowana jest w Gigabajtach [GB].
        # Jednak w bardzo rzadkich przypadkach jest to jednostka Megabajtów [MB]. Aby uniknąć zapisu wszystkich wartości
        # w jednostkach MB, co by oznaczało, że każdą wartość powyżej 1GB należałoby pomnożyć przez 1024,
        # oraz przechowywania danych jako liczb zmiennoprzecinkowych, np. 0.512 [GB]
        # pojemności pamięci wylistowane w jednostkach [MB] zapisywane są jako liczby ujemne
        spec_value = CommonUtils.get_value_from_spec_row(spec_rows, "Pojemność łączna")
        total_capacity = CommonUtils.extract_int(spec_value)
        if "MB" in spec_value:
            total_capacity = total_capacity * (-1)

        spec_value = CommonUtils.get_value_from_spec_row(spec_rows, "Liczba modułów")
        number_of_modules = CommonUtils.extract_int(spec_value)

        spec_value = CommonUtils.get_value_from_spec_row(spec_rows, "Częstotliwość pracy [MHz]")
        frequency = CommonUtils.extract_int(spec_value)

        latency = CommonUtils.get_value_from_spec_row(spec_rows, "Opóźnienie")

        spec_value = CommonUtils.get_value_from_spec_row(spec_rows, "Częstotliwość pracy [MHz]")
        lighting = CommonUtils.translate_to_bool(spec_value)

        return RAM(product.get_name(), product.get_producer(), product.get_category(), product.get_description(),
                   product.get_price(), product.get_producer_code(), line, memory_type, total_capacity,
                   number_of_modules, frequency, latency, lighting)
