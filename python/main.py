from selenium.webdriver.chrome.options import Options
from selenium import webdriver

from ProductParser import ProductParser
from product.Product import Product
from product.ProductCategory import UrlCategory, ProductCategory
from util import WebUtil

if __name__ == "__main__":
    products: dict[str:Product] = {}
    options = Options()
    options.add_argument("--headless")
    options.add_argument("--window-size=1920,1080")
    driver = webdriver.Chrome(options=options)
    parser = ProductParser(driver)
    util = WebUtil(driver)
    all_links = []
    for url in UrlCategory:
        for link in util.find_products(url):
            all_links.append(link)

    for link in all_links:
        prod = parser.parse_product(link)
        if prod is None:
            continue
        if prod.producer_code in products:
            print("=========Duplicate===========")
            prod.print_product_specs()
            products[prod.producer_code].print_product_specs()
            print("==============================")
        else:
            products[prod.producer_code] = prod
            print("SUCCESS: Parsed:", prod.name)

    for prod in products.values():
        prod.print_product_specs()

    print("Parsed products: " + str(len(products)) + "/" + str(len(all_links)))
