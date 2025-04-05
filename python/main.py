import sys

from selenium.webdriver.chrome.options import Options
from selenium import webdriver

from utils.DatabaseUtil import DatabaseUtil
from utils.JsonUtil import JsonUtil
from utils.ProductParser import ProductParser
from product.Product import Product
from product.ProductCategory import UrlCategory
from utils.WebUtil import WebUtil


def log_duplicate(new_product, saved_product):
    print("=============================================")
    print("WARNING: Duplicate", new_product.get_ean())
    print("------------------- SAVED -------------------")
    saved_product.print_product_specs()
    print("------------------- PARSED -------------------")
    new_product.print_product_specs()
    print("=============================================")


def scrape_data(products: dict[int:Product]):
    options = Options()
    options.add_argument("--headless")
    options.add_argument("--window-size=1920,1080")
    driver = webdriver.Chrome(options=options)
    web_util = WebUtil(driver)
    parser = ProductParser(driver, web_util)

    all_links = []
    for url in UrlCategory:
        for link in web_util.find_products(url):
            all_links.append(link)

    for i in range(0, len(all_links)):
        link = all_links[i]
        print(f"[{i + 1}/{len(all_links)}] ", end="")
        if link in json.parsed_urls:
            print("SKIPPED: product", json.parsed_urls[link], "already exists")
            continue
        prod = parser.parse_product(link)
        if prod is None:
            continue
        elif prod.get_producer_code() in products.keys():
            log_duplicate(prod, products[prod.get_producer_code()])
        else:
            products[prod.get_producer_code()] = prod
            json.save_product(link, prod)
    driver.quit()
    print("Parsed products: " + str(len(products)) + "/" + str(len(all_links)))


if __name__ == "__main__":
    json = JsonUtil()
    products: dict[int:Product] = json.load_saved_products()
    if "--scrape-new-data" in sys.argv:
        scrape_data(products)
    if "--print-specs" in sys.argv:
        for prod in products.values():
            prod.print_product_specs()

    db_util = DatabaseUtil(products)
    db_util.add_products_to_database()
