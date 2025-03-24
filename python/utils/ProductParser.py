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
        if self.util.get_elements(By.CLASS_NAME, "product-price") is None:
            print("FAIL: Product is unavailable")
            return None

        spec_rows = self.util.get_elements(By.CLASS_NAME, "specification__row")
        producer_code = CommonUtils.get_value_from_spec_row(spec_rows, "Kod producenta")
        if producer_code == "":
            print("FAIL: Unknown producer code")
            return None

        name = self.get_product_name()
        producer = CommonUtils.get_value_from_spec_row(spec_rows, "Producent")
        product_category = self.get_product_category()
        description = self.get_product_description(name)
        price = CommonUtils.extract_float(self.driver.find_element(By.CLASS_NAME, "product-price").text)
        self.util.save_image(producer_code)

        product = Product(name, producer, product_category, description, price, producer_code)
        return self.parse_exact_product(product, spec_rows)

    def parse_cpu(self, product: Product, spec_rows):
        """
        Pobiera i przetwarza dane z adresu URL na obiekt klasy ``Processor``
        :return: obiekt klasy ``Processor`` lub **None** jeśli pakowanie jest inne niż OEM, lub BOX
        """
        pack = str(CommonUtils.get_value_from_spec_row(spec_rows, "Wersja opakowania"))
        if pack != "BOX" and pack != "OEM":
            print("Unknown Packaging")
            return None
        line = CommonUtils.get_value_from_spec_row(spec_rows, "Linia")
        model = product.name.split().pop(-1)
        num_of_cores = CommonUtils.extract_int(CommonUtils.get_value_from_spec_row(spec_rows, "Liczba rdzeni"))
        num_of_threads = CommonUtils.extract_int(CommonUtils.get_value_from_spec_row(spec_rows, "Liczba wątków"))
        socket = CommonUtils.get_value_from_spec_row(spec_rows, "Typ gniazda")
        unlocked = CommonUtils.translate_to_bool(CommonUtils.get_value_from_spec_row(spec_rows, "Odblokowany mnożnik"))
        frequency = CommonUtils.extract_float(
            CommonUtils.get_value_from_spec_row(spec_rows, "Częstotliwość taktowania procesora"))
        max_frequency = CommonUtils.extract_float(
            CommonUtils.get_value_from_spec_row(spec_rows, "Częstotliwość maksymalna Turbo"))
        integrated_graphics_unit = CommonUtils.get_value_from_spec_row(spec_rows, "Zintegrowany układ graficzny")
        if integrated_graphics_unit == "Nie posiada":
            integrated_graphics_unit = None
        tdp = CommonUtils.extract_int(CommonUtils.get_value_from_spec_row(spec_rows, "TDP"))
        cooler_included = CommonUtils.translate_to_bool(
            CommonUtils.get_value_from_spec_row(spec_rows, "Załączone chłodzenie"))
        return Processor(product.name, product.producer, product.category, product.description, product.price,
                         product.producer_code, line, model, num_of_cores, num_of_threads, socket, unlocked,
                         frequency, max_frequency, integrated_graphics_unit, tdp, cooler_included, pack)

    def get_product_category(self):
        """
        Porównuje link do kategorii znaleziony na stronie z tym zapisanym w ``ProductCategory``
        :return: Ciąg znaków kategorii produktu
        """
        match self.util.get_elements(By.CSS_SELECTOR, "a.main-breadcrumb")[-1].get_attribute("href"):
            case UrlCategory.CPU:
                return str(ProductCategory.CPU)

    def get_product_name(self):
        """
        Pobiera nazwę produktu z załadowanej witryny.
        :return: Nazwa produktu do wyznaczonego miejsca
        """
        name = self.util.get_element(By.CSS_SELECTOR, "h1.prod-name").text
        return name[:name.find(" (")]

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

    def parse_exact_product(self, product, spec_rows):
        """
        Służy do wywołania metody parsującej na obiekt odpowiedniej klasy dziedziczącej z ``Product``
        :param product: obiekt klasy ``Product``
        :param spec_rows: wiersze tabeli specyfikacji
        :return: obiekt odpowiedniej klasy dziedziczącej z ``Product``
        """
        match product.category:
            case ProductCategory.CASE:
                pass
            case ProductCategory.GPU:
                pass
            case ProductCategory.SSD:
                pass
            case ProductCategory.HDD:
                pass
            case ProductCategory.MB:
                pass
            case ProductCategory.POWER_SUPPLY:
                pass
            case ProductCategory.CPU:
                return self.parse_cpu(product, spec_rows)
            case ProductCategory.RAM:
                pass
