from unicodedata import category

from product.ProductCategory import UrlCategory
from util import SpecsUtil
if __name__ == "__main__":
    morele_main = "https://www.morele.net/"
    products = {}
    util = SpecsUtil(morele_main + UrlCategory.CPU)
    links = util.find_products()
    for link in links:
        cpu = util.parse_cpu(link)
        if cpu is None:
            continue
        if cpu.producer_code in products:
            print("product: ", cpu.name, " already exists.")
            if cpu.packaging != products[cpu.producer_code].packaging:
                print(cpu.packaging, " | ", products[cpu.producer_code].packaging)
            if cpu.description_headers != products[cpu.producer_code].description_headers:
                print(cpu.description_headers, " | ", products[cpu.producer_code].description_headers)
            if cpu.description_paragraphs != products[cpu.producer_code].description_paragraphs:
                print(cpu.description_paragraphs, " | ", products[cpu.producer_code].description_paragraphs)
            if cpu.price != products[cpu.producer_code].price:
                print(cpu.price, " | ", products[cpu.producer_code].price)
            if cpu.cooler_included != products[cpu.producer_code].cooler_included:
                print(cpu.cooler_included, " | ", products[cpu.producer_code].cooler_included)
        else:
            products[cpu.producer_code] = cpu
            cpu.print_product_specs()
