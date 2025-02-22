import requests
from bs4 import BeautifulSoup

class Util:
    def __init__(self, base_url, sub_url):
        self.__base_url = base_url
        self.__sub_url = sub_url
        self.all_links = []
        self.__response = None
        self.__html = None
        self.__soup = None

    def find_products(self, product_selector, product_class, next_page_selector, next_page_class):
        self.__response = requests.get(self.__base_url + self.__sub_url)
        self.__html = self.__response.text
        self.__soup = BeautifulSoup(self.__html, features="html.parser")
        products = self.__soup.find_all(product_selector, class_=product_class)
        for i in range(len(products)):
            self.all_links.append(self.__base_url + products[i].get("href"))
        if self.__soup.find(next_page_selector, class_=next_page_class):
            self.__sub_url = self.__soup.find(next_page_selector, class_=next_page_class).find("a").get("href")
            self.find_products(product_selector, product_class, next_page_selector, next_page_class)
