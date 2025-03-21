from selenium import webdriver
from selenium.webdriver.common.by import By
from product.Processor import Processor
from product.Product import Product
from product.ProductCategory import UrlCategory, ProductCategory
from utils.CommonUtils import CommonUtils
from utils.WebUtil import WebUtil

class ProductParser:
    def __init__(self, driver: webdriver.Chrome):
        self.url = "https://www.morele.net/"
        self.driver = driver
        self.util = WebUtil(self.driver)
        CommonUtils.directory_exists("images")

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
        for row in desc.find_elements(By.CSS_SELECTOR, "div.row div.text1"):
            description += self.util.get_description(row, name)
        price = self.util.extract_float(self.driver.find_element(By.CLASS_NAME, "product-price").text)
        self.save_images(producer_code)

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
            case UrlCategory.CPU:
                return ProductCategory.CPU

    def hide_element_if_exists(self, by: By, locator: str):
        elements_to_hide = self.driver.find_elements(by, locator)
        if len(elements_to_hide) > 0:
            for el in elements_to_hide:
                self.driver.execute_script("arguments[0].style.display = 'none';", el)

    def save_images(self, producer_code: str):
        self.hide_element_if_exists(By.CSS_SELECTOR, "button.btn-shopping-lists")
        self.hide_element_if_exists(By.CSS_SELECTOR, "button.btn-share-link")
        self.hide_element_if_exists(By.CSS_SELECTOR, "div.prod-gallery-video-btn")
        images = self.driver.find_elements(By.CSS_SELECTOR, "picture img")
        for img in images:
            if producer_code in img.accessible_name:
                img.screenshot("images\\" + producer_code + ".png")
                break
