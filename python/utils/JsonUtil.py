import json
import os
from product.Processor import Processor
from product.Product import Product
from product.ProductCategory import ProductCategory


class JsonUtil:
    def __init__(self):
        self.json_path = os.path.join(os.getcwd(), "json", "saved_products.json")
        self.parsed_urls = {}

    def load_saved_products(self):
        products: dict[str:Product] = {}
        if not os.path.exists(self.json_path):
            print("Aktualny katalog roboczy:", os.getcwd())
            print("Plik", self.json_path, "nie istnieje")
            return
        with open(self.json_path, encoding="utf-8") as json_file:
            data = json.load(json_file)
            for prod in data.values():
                self.parsed_urls[prod["url"]] = prod["product_data"]["name"]
                products[prod["product_data"]["producer_code"]] = self.parse_product_form_json(prod["product_data"])
        return products

    def parse_product_form_json(self, product_json):
        match product_json["category"]:
            case ProductCategory.CPU:
                return Processor(product_json["name"], product_json["producer"], product_json["category"],
                                 product_json["description"], product_json["price"], product_json["producer_code"],
                                 product_json["line"], product_json["model"], product_json["cores"],
                                 product_json["threads"], product_json["socket"], product_json["unlocked"],
                                 product_json["frequency"], product_json["max_frequency"],
                                 product_json["integrated_graphics_unit"], product_json["tdp"],
                                 product_json["cooler_included"], product_json["packaging"])
