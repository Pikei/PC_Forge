from selenium.webdriver.chrome.options import Options
from selenium import webdriver
from utils.JsonUtil import JsonUtil
from utils.ProductParser import ProductParser
from product.Product import Product
from product.ProductCategory import UrlCategory
from utils.WebUtil import WebUtil


def log_duplicate():
    print("=============================================")
    print("WARNING: Duplicate", prod.get_ean())
    print("------------------- SAVED -------------------")
    products[prod.get_ean()].print_product_specs()
    print("------------------- PARSED -------------------")
    prod.print_product_specs()
    print("=============================================")


if __name__ == "__main__":
    json = JsonUtil()
    products: dict[str:Product] = json.load_saved_products()
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
        elif prod.get_ean() in products.keys():
            log_duplicate()
            if len(prod.get_description()) > len(products[prod.get_ean()].get_description()):
                products[prod.get_ean()] = prod
            else:
                products[prod.get_ean()] = prod
                json.save_product(link, prod)

    for prod in products.values():
        prod.print_product_specs()

    print("Parsed products: " + str(len(products)) + "/" + str(len(all_links)))
    driver.quit()
