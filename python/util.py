import enum
import re
from string import digits
from time import sleep

from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.chrome.options import Options
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions

from product.Processor import Processor
from product.Product import Product
from product.ProductCategory import ProductCategory


class SpecsUtil:
    def __init__(self, url):
        self.url = url
        self.all_links = []
        options = Options()
        options.add_argument("--headless")
        options.add_argument("--window-size=1920,1080")
        self.__webdriver = webdriver.Chrome(options=options)

    def load_page(self, url: str, by: By, wait_selector: str):
        self.__webdriver.get(url)
        try:
            WebDriverWait(self.__webdriver, 5).until(
                expected_conditions.presence_of_element_located((by, wait_selector)))
        except:
            print("element: ", wait_selector, " does not exist on this page.")
            return False

        cookies = self.__webdriver.find_elements(By.CSS_SELECTOR, "button.btn.btn-primary.w-100.btn--save-all")
        if len(cookies) == 1:
            cookies[0].click()
            print("Cookies accepted")

        return True

    def find_products(self):
        if not self.load_page(self.url, By.CSS_SELECTOR, "a.productLink"):
            return None
        links = self.__webdriver.find_elements(By.CSS_SELECTOR, "a.productLink")
        for link in links:
            self.all_links.append(link.get_attribute("href"))

        try:
            next_page = self.__webdriver.find_element(By.CSS_SELECTOR, "li.pagination-lg.next a.pagination-btn")
            self.url = next_page.get_attribute("href")
            self.find_products()
        except:
            print("Reached last page of products in this category")

        return self.all_links

    def get_value_from_spec_row(self, rows, param_name):
        for row in rows:
            if row.find_element(By.CLASS_NAME, "specification__name").text == param_name:
                return row.find_element(By.CLASS_NAME, "specification__value").text
        return ""

    def check_if_available(self):
        try:
            self.__webdriver.find_element(By.CLASS_NAME, "product-price")
            return True
        except:
            print("Product is unavailable")
            return False

    def is_digit(self, c: chr):
        try:
            int(c)
            return True
        except:
            return False

    def extract_float(self, s: str):
        res = ""
        s = s.replace(",", ".")
        point_found = False
        for char in s:
            if self.is_digit(char):
                res = res + char
                continue
            elif char == "." and not point_found:
                res = res + char
                point_found = True
                continue
            elif point_found and not self.is_digit(char):
                break
        if len(res) > 0:
            return float(res)
        return 0.0

    def extract_int(self, s: str):
        res = ""
        num_found = False
        for char in s:
            if self.is_digit(char):
                num_found = True
                res = res + char
            elif not num_found:
                continue
            else:
                break
        if len(res) > 0:
            return int(res)
        return 0

    def description(self, list):
        desc = []
        for elem in list:
            if elem.text != "":
                desc.append(elem.text)
        if len(desc) == 0:
            desc.append("")
        return desc

    def parse_product(self, cat: ProductCategory):
        name = self.__webdriver.find_element(By.CSS_SELECTOR, "h1.prod-name").text
        name = name[:name.find(",")]
        rows = self.__webdriver.find_elements(By.CLASS_NAME, "specification__row")
        producer = self.get_value_from_spec_row(rows, "Producent")
        product_category = str(cat)
        # TODO nie wszystkie paragrafy opisu się pobierają ( len(h) != len(p) )
        desc = self.__webdriver.find_element(By.CLASS_NAME, "panel-description")
        desc_headers = self.description(desc.find_elements(By.TAG_NAME, "h3"))
        desc_paragraphs = self.description(desc.find_elements(By.TAG_NAME, "p"))
        price = self.extract_float(self.__webdriver.find_element(By.CLASS_NAME, "product-price").text)
        producer_code = self.get_value_from_spec_row(rows, "Kod producenta")
        return Product(name, producer, product_category, desc_headers, desc_paragraphs, price, producer_code)

    def translate_to_bool(self, s: str):
        if re.search(s, "tak", re.IGNORECASE):
            return True
        return False

    def parse_cpu(self, url):
        if not self.load_page(url, By.CLASS_NAME, "product-specification__table"):
            return None
        if not self.check_if_available():
            return None
        prod = self.parse_product(ProductCategory.CPU)
        rows = self.__webdriver.find_elements(By.CLASS_NAME, "specification__row")
        line = self.get_value_from_spec_row(rows, "Linia")
        model = prod.name.split().pop(-1)
        num_of_cores = self.extract_int(self.get_value_from_spec_row(rows, "Liczba rdzeni"))
        num_of_threads = self.extract_int(self.get_value_from_spec_row(rows, "Liczba wątków"))
        socket = self.get_value_from_spec_row(rows, "Typ gniazda")
        unlocked = self.translate_to_bool(self.get_value_from_spec_row(rows, "Odblokowany mnożnik"))
        frequency = self.extract_float(self.get_value_from_spec_row(rows, "Częstotliwość taktowania procesora"))
        max_frequency = self.extract_float(self.get_value_from_spec_row(rows, "Częstotliwość maksymalna Turbo"))
        integrated_graphics_unit = self.get_value_from_spec_row(rows, "Zintegrowany układ graficzny")
        if integrated_graphics_unit == "Nie posiada":
            integrated_graphics_unit = None
        tdp = self.extract_int(self.get_value_from_spec_row(rows, "TDP"))
        cooler_included = self.translate_to_bool(self.get_value_from_spec_row(rows, "Załączone chłodzenie"))
        pack = self.get_value_from_spec_row(rows, "Wersja opakowania")
        return Processor(prod.name, prod.producer, prod.category, prod.description_headers, prod.description_paragraphs,
                         prod.price, prod.producer_code, line, model, num_of_cores,
                         num_of_threads, socket, unlocked, frequency, max_frequency, integrated_graphics_unit, tdp,
                         cooler_included, pack)
