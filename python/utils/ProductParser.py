from selenium import webdriver
from selenium.webdriver.common.by import By

from product.Processor import Processor
from product.Product import Product
from product.ProductCategory import UrlCategory, ProductCategory
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
        description: str = self.get_product_description(name)

        product = Product(name, producer, "", description, product_price, producer_code)

        match self.util.get_elements(By.CSS_SELECTOR, "a.main-breadcrumb")[-1].get_attribute("href"):
            case UrlCategory.CPU:
                product = self.parse_cpu(product, spec_rows)

        if product is not None:
            self.util.save_image(producer_code)
            print("Parsed:", product.name)
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

        product.name = product.name[:product.name.find(",")]

        product.category = str(ProductCategory.CPU)

        line = CommonUtils.get_value_from_spec_row(spec_rows, "Linia")

        model = product.name.split().pop(-1)

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

        return Processor(product.name, product.producer, product.category, product.description, product.price,
                         product.producer_code, line, model, num_of_cores, num_of_threads, socket, unlocked,
                         frequency, max_frequency, integrated_graphics_unit, tdp, cooler_included, pack)


    def get_product_description(self, product_name):
        """
        Pobiera wiersze opisu produktu przekształcając je na kod HTML
        :param product_name: Nazwa produktu będąca alternatywnym nagłówkiem w przypadku jego braku w danym wierszu opisu
        :return: Kod HTML opisu produktu
        """
        self.util.expand_description()
        desc = self.util.get_element(By.CLASS_NAME, "panel-description")
        description = ""
        for row in self.util.get_elements(By.CSS_SELECTOR, "div.row div.text1", desc):
            description += self.util.get_description_row(row, product_name)
        return description
