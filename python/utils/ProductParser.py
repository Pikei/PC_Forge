from selenium import webdriver
from selenium.webdriver.common.by import By

from product.Processor import Processor
from product.Product import Product
from product.ProductCategory import UrlCategory, ProductCategory
from utils.CommonUtils import CommonUtils
from utils.WebUtil import WebUtil


class ProductParser:
    def __init__(self, driver: webdriver.Chrome, web_util: WebUtil):
        self.url = "https://www.morele.net/"
        self.driver = driver
        self.util = web_util
        CommonUtils.directory_exists("images")

    def parse_product(self, url: str):
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

    def parse_cpu(self, product: Product, rows):
        pack = str(CommonUtils.get_value_from_spec_row(rows, "Wersja opakowania"))
        if pack != "BOX" and pack != "OEM":
            print("Unknown Packaging")
            return None
        line = CommonUtils.get_value_from_spec_row(rows, "Linia")
        model = product.name.split().pop(-1)
        num_of_cores = CommonUtils.extract_int(CommonUtils.get_value_from_spec_row(rows, "Liczba rdzeni"))
        num_of_threads = CommonUtils.extract_int(CommonUtils.get_value_from_spec_row(rows, "Liczba wątków"))
        socket = CommonUtils.get_value_from_spec_row(rows, "Typ gniazda")
        unlocked = CommonUtils.translate_to_bool(CommonUtils.get_value_from_spec_row(rows, "Odblokowany mnożnik"))
        frequency = CommonUtils.extract_float(
            CommonUtils.get_value_from_spec_row(rows, "Częstotliwość taktowania procesora"))
        max_frequency = CommonUtils.extract_float(
            CommonUtils.get_value_from_spec_row(rows, "Częstotliwość maksymalna Turbo"))
        integrated_graphics_unit = CommonUtils.get_value_from_spec_row(rows, "Zintegrowany układ graficzny")
        if integrated_graphics_unit == "Nie posiada":
            integrated_graphics_unit = None
        tdp = CommonUtils.extract_int(CommonUtils.get_value_from_spec_row(rows, "TDP"))
        cooler_included = CommonUtils.translate_to_bool(
            CommonUtils.get_value_from_spec_row(rows, "Załączone chłodzenie"))
        return Processor(product.name, product.producer, product.category, product.description, product.price,
                         product.producer_code, line, model, num_of_cores, num_of_threads, socket, unlocked,
                         frequency, max_frequency, integrated_graphics_unit, tdp, cooler_included, pack)

    def get_product_category(self):
        match self.util.get_elements(By.CSS_SELECTOR, "a.main-breadcrumb")[-1].get_attribute("href"):
            case UrlCategory.CPU:
                return str(ProductCategory.CPU)

    def get_product_name(self):
        name = self.util.get_element(By.CSS_SELECTOR, "h1.prod-name").text
        return name[:name.find(",")]

    def get_product_description(self, product_name):
        self.util.expand_description()
        desc = self.util.get_element(By.CLASS_NAME, "panel-description")
        description = ""
        for row in self.util.get_elements(By.CSS_SELECTOR, "div.row div.text1", desc):
            description += self.util.get_description_row(row, product_name)
        return description

    def parse_exact_product(self, product, spec_rows):
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
