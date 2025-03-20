import re
from collections import OrderedDict
from time import sleep

from selenium import webdriver
from selenium.webdriver import ActionChains
from selenium.webdriver.common.by import By
from selenium.webdriver.remote.webelement import WebElement
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions

class WebUtil:
    def __init__(self, driver: webdriver.Chrome):
        self.driver = driver
        self.__cookies_accepted = False
        self.all_links = []

    def accept_cookies(self):
        try:
            element = WebDriverWait(self.driver, 5).until(
                expected_conditions.presence_of_element_located(
                    (By.CSS_SELECTOR, "button.btn.btn-primary.w-100.btn--save-all")))
            sleep(1)
            element.click()
            self.__cookies_accepted = True
        except:
            return

    def expand_description(self):
        try:
            element = self.driver.find_element(By.CSS_SELECTOR, "toggle-btn.collapsible-description__btn.btn")
            action = ActionChains(self.driver)
            action.move_to_element(element).perform()
            element.click()
            sleep(1)
        except:
            return

    def load_page(self, url: str, by: By, wait_selector: str):
        sleep(0.5)
        self.driver.get(url)
        try:
            WebDriverWait(self.driver, 2).until(
                expected_conditions.presence_of_element_located((by, wait_selector)))
            sleep(1)
        except:
            print("element: ", wait_selector, " does not exist on this page.")
            return False

        if not self.__cookies_accepted:
            self.accept_cookies()

        return True

    def find_products(self, url):
        if not self.load_page(url, By.CSS_SELECTOR, "a.productLink"):
            return None
        current_page_num = self.driver.find_element(By.CSS_SELECTOR, "li.pagination-lg.btn.active").text
        last_page_num = self.driver.find_element(By.CSS_SELECTOR, "div.pagination-btn-nolink-anchor").text
        category_name = self.driver.find_element(By.CSS_SELECTOR, "span.category-name.main").text
        print("Getting product URLs. Current category: \"" + category_name + "\"")
        links = []
        while int(current_page_num) <= int(last_page_num):
            current_page_num = self.driver.find_element(By.CSS_SELECTOR, "li.pagination-lg.btn.active").text
            print("Page:", current_page_num + "/" + last_page_num)
            for link in self.driver.find_elements(By.CSS_SELECTOR, "a.productLink"):
                sleep(0.1)
                links.append(link.get_attribute("href"))
            if int(current_page_num) < int(last_page_num):
                sleep(0.1)
                self.driver.find_element(By.CSS_SELECTOR, "li.pagination-lg.next a.pagination-btn").click()
                sleep(0.5)
            else:
                break
        return links

    def get_value_from_spec_row(self, rows, param_name):
        for row in rows:
            if row.find_element(By.CLASS_NAME, "specification__name").text == param_name:
                return row.find_element(By.CLASS_NAME, "specification__value").text
        return ""

    def check_if_available(self):
        try:
            self.driver.find_element(By.CLASS_NAME, "product-price")
            return True
        except:
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

    def parse_to_html(self, element: WebElement):
        return "<" + element.tag_name + ">" + element.text + "</" + element.tag_name + ">"

    def add_par_and_list(self, paragraphs: list[WebElement], lists: list[WebElement]):
        res = ""
        position = {}
        for p in paragraphs:
            position[p.location['y']] = p
        for l in lists:
            position[l.location['y']] = l

        position = OrderedDict(sorted(position.items()))

        for line in position.values():
            if line.tag_name == "p":
                res += self.parse_to_html(line)
            elif line.tag_name == "ul":
                res += "<" + line.tag_name + ">"
                for li in line.find_elements(By.TAG_NAME, "li"):
                    res += self.parse_to_html(li)
                res += "</" + line.tag_name + ">"
        return res

    def get_description(self, row: WebElement, product_name: str):
        content = ""
        try:
            header = WebDriverWait(row, 10).until(expected_conditions.presence_of_element_located((By.TAG_NAME, "h3")))
            content = self.parse_to_html(header)
        except:
            print("Description row without header")
            content = "<h3>" + product_name + "</h3>"
        paragraphs = row.find_elements(By.TAG_NAME, "p")
        lists = row.find_elements(By.TAG_NAME, "ul")
        if len(paragraphs) > 0 and len(lists) > 0:
            self.add_par_and_list(paragraphs, lists)
        elif len(paragraphs) > 0:
            for p in paragraphs:
                content += self.parse_to_html(p)
        elif len(lists) > 0:
            for ul in lists:
                content += "<" + ul.tag_name + ">"
                for li in ul.find_elements(By.TAG_NAME, "li"):
                    content += self.parse_to_html(li)
                content += "</" + ul.tag_name + ">"
        return content

    def translate_to_bool(self, s: str):
        if re.search(s, "tak", re.IGNORECASE):
            return True
        return False

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


