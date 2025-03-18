from selenium import webdriver
from selenium.webdriver.common.by import By

from product.Processor import Processor
from product.Product import Product
from product.ProductCategory import UrlCategory, ProductCategory
from util import WebUtil


class ProductParser:
    def __init__(self, driver: webdriver.Chrome):
        self.url = "https://www.morele.net/"
        self.driver = driver
        self.util = WebUtil(self.driver)

    def parse_cpu(self, product: Product, rows):
        pack = str(self.util.get_value_from_spec_row(rows, "Wersja opakowania"))
        if pack != "BOX" and pack != "OEM":
            print("Unknown Packaging")
            return None
        line = self.util.get_value_from_spec_row(rows, "Linia")
        model = product.name.split().pop(-1)
        num_of_cores = self.util.extract_int(self.util.get_value_from_spec_row(rows, "Liczba rdzeni"))
        num_of_threads = self.util.extract_int(self.util.get_value_from_spec_row(rows, "Liczba wątków"))
        socket = self.util.get_value_from_spec_row(rows, "Typ gniazda")
        unlocked = self.util.translate_to_bool(self.util.get_value_from_spec_row(rows, "Odblokowany mnożnik"))
        frequency = self.util.extract_float(
            self.util.get_value_from_spec_row(rows, "Częstotliwość taktowania procesora"))
        max_frequency = self.util.extract_float(
            self.util.get_value_from_spec_row(rows, "Częstotliwość maksymalna Turbo"))
        integrated_graphics_unit = self.util.get_value_from_spec_row(rows, "Zintegrowany układ graficzny")
        if integrated_graphics_unit == "Nie posiada":
            integrated_graphics_unit = None
        tdp = self.util.extract_int(self.util.get_value_from_spec_row(rows, "TDP"))
        cooler_included = self.util.translate_to_bool(self.util.get_value_from_spec_row(rows, "Załączone chłodzenie"))
        return Processor(product.name, product.producer, product.category, product.description, product.price,
                         product.producer_code, line, model, num_of_cores, num_of_threads, socket, unlocked,
                         frequency, max_frequency, integrated_graphics_unit, tdp, cooler_included, pack)

    def parse_product(self, url: str):
        print("Parsing:", url)
        if not self.util.load_page(url, By.CLASS_NAME, "product-specification__table"):
            print("FAIL: product does not have specification table")
            return None
        if not self.util.check_if_available():
            print("FAIL: Product is unavailable")
            return None
        rows = self.driver.find_elements(By.CLASS_NAME, "specification__row")
        producer_code = self.util.get_value_from_spec_row(rows, "Kod producenta")
        if producer_code == "":
            print("FAIL: Unknown producer code")
            return None
        name = self.driver.find_element(By.CSS_SELECTOR, "h1.prod-name").text
        name = name[:name.find(",")]
        producer = self.util.get_value_from_spec_row(rows, "Producent")
        cat_url = self.driver.find_elements(By.CSS_SELECTOR, "a.main-breadcrumb")[-1].get_attribute("href")
        product_category = str(self.product_category(cat_url))
        self.util.expand_description()
        desc = self.driver.find_element(By.CLASS_NAME, "panel-description")
        description = ""
        # for child in desc.find_elements(By.XPATH, "./*"):
        #     if child.text != "":
        #         description += "<" + child.tag_name + ">" + child.text + "</" + child.tag_name + ">"
        # description = ""
        for row in desc.find_elements(By.CSS_SELECTOR, "div.row div.text1"):
            # if row.text != "":
            description += self.util.get_description(row)
        price = self.util.extract_float(self.driver.find_element(By.CLASS_NAME, "product-price").text)
        product = Product(name, producer, product_category, description, price, producer_code)
        match product_category:
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
                return self.parse_cpu(product, rows)
            case ProductCategory.RAM:
                pass

    @staticmethod
    def product_category(category_url):
        match category_url:
            # case UrlCategory.CASE:
            #     return ProductCategory.CASE
            # case UrlCategory.GPU:
            #     return ProductCategory.GPU
            # case UrlCategory.SSD:
            #     return ProductCategory.SSD
            # case UrlCategory.HDD:
            #     return ProductCategory.HDD
            # case UrlCategory.MB:
            #     return ProductCategory.MB
            # case UrlCategory.POWER_SUPPLY:
            #     return ProductCategory.POWER_SUPPLY
            case UrlCategory.CPU:
                return ProductCategory.CPU
            # case UrlCategory.RAM:
            #     return ProductCategory.RAM
