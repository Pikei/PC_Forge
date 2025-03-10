from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.chrome.options import Options
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions


class SpecsUtil:
    def __init__(self, url):
        self.url = url
        self.all_links = []
        options = Options()
        options.add_argument("--headless")
        options.add_argument("--window-size=1920,1080")
        self.__webdriver = webdriver.Chrome(options=options)

    def find_products(self):
        self.__webdriver.get(self.url)
        WebDriverWait(self.__webdriver, 10).until(expected_conditions.presence_of_element_located((By.TAG_NAME, "a")))
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

    def parse_cpu(self, url):
        self.__webdriver.get(url)
        WebDriverWait(self.__webdriver, 10).until(
            expected_conditions.presence_of_element_located((By.CLASS_NAME, "product-specification__table")))
        name = self.__webdriver.find_element(By.CSS_SELECTOR, "h1.prod-name").text
        rows = self.__webdriver.find_elements(By.CLASS_NAME, "specification__row")
        name = name[:name.find(",")]
        producer = self.get_value_from_spec_row(rows, "Producent")
        product_category = "CPU"
        desc = self.__webdriver.find_element(By.CLASS_NAME, "desc-items1").text
        price = self.__webdriver.find_element(By.ID, "product_price").text
        producer_code = self.get_value_from_spec_row(rows, "Kod producenta")
        line = self.get_value_from_spec_row(rows, "Linia")
        model = name.split().pop(-1)
        num_of_cores = self.get_value_from_spec_row(rows, "Liczba rdzeni")
        num_of_threads = self.get_value_from_spec_row(rows, "Liczba wątków")
        socket = self.get_value_from_spec_row(rows, "Typ gniazda")
        unlocked = self.get_value_from_spec_row(rows, "Odblokowany mnożnik")
        frequency = self.get_value_from_spec_row(rows, "Częstotliwość taktowania procesora")
        max_frequency = self.get_value_from_spec_row(rows, "Częstotliwość maksymalna Turbo")
        integrated_graphics_unit = self.get_value_from_spec_row(rows, "Zintegrowany układ graficzny")
        tdp = self.get_value_from_spec_row(rows, "TDP")
        cooler_included = self.get_value_from_spec_row(rows, "Załączone chłodzenie")
        print("--------------- ", name, " ---------------")
        print("Product type: ", product_category)
        print("Producer: ", producer)
        print("Price: ", price)
        print("Description: ", desc)
        print("Producer code: ", producer_code)
        print("Line: ", line)
        print("Model: ", model)
        print("Numer of cores: ", num_of_cores)
        print("Numer of threads: ", num_of_threads)
        print("Socket: ", socket)
        print("Unlocked: ", unlocked)
        print("Frequency: ", frequency)
        print("Max frequency: ", max_frequency)
        print("Integrated Graphics Unit: ", integrated_graphics_unit)
        print("TDP: ", tdp)
        print("Cooler included: ", cooler_included)
        print("----------------------------\n")
