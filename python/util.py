from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.chrome.options import Options
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions

class Util:
    def __init__(self, url):
        self.url = url
        self.all_links = []
        options = Options()
        options.add_argument("--headless")
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
            self.__webdriver.quit()
        return self.all_links
