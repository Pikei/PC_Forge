from selenium.webdriver.chrome.options import Options
from selenium import webdriver
from utils.JsonUtil import JsonUtil
from utils.ProductParser import ProductParser
from product.Product import Product
from product.ProductCategory import UrlCategory
from utils.WebUtil import WebUtil

if __name__ == "__main__":
    json = JsonUtil()
    products: dict[str:Product] = json.load_saved_products()
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
        if link in json.parsed_urls:
            print("SKIPPED: product", json.parsed_urls[link], "already exists")
            continue
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
            print("Successfully parsed:", prod.name)

    for prod in products.values():
        prod.print_product_specs()

    print("Parsed products: " + str(len(products)) + "/" + str(len(all_links)))
    driver.quit()
