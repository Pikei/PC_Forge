import json
import os

from product.Processor import Processor
from product.Product import Product
from product.ProductCategory import ProductCategory
from utils.CommonUtils import CommonUtils

class JsonUtil:
    def __init__(self):
        file_name = "saved_products.json"
        self.json_path = os.path.join(os.getcwd(), "json", file_name)
        self.parsed_urls = {}
        CommonUtils.directory_exists("json")

    def save_product(self, url: str, product: Product):
        data_to_save = {}
        data = []
        data_to_save["url"] = url
        data_to_save["product_data"] = self.parse_product_to_json(product)
        try:
            with open(self.json_path, mode="r+", encoding="utf-8") as json_file:
                data = json.load(json_file)
                data.append(data_to_save)
                json_file.seek(0)
                json.dump(data, json_file, indent=4)
        except:
            with open(self.json_path, mode="w", encoding="utf-8") as json_file:
                data.append(data_to_save)
                json.dump(data, json_file, indent=4)

    def load_saved_products(self):
        products: dict[str:Product] = {}
        if not os.path.exists(self.json_path):
            return {}
        with open(self.json_path, encoding="utf-8") as json_file:
            data = json.load(json_file)
            for prod in data:
                self.parsed_urls[prod["url"]] = prod["product_data"]["name"]
                products[prod["product_data"]["producer_code"]] = self.parse_json_to_product(prod["product_data"])
        return products

    def parse_json_to_product(self, product_json):
        match product_json["category"]:
            case ProductCategory.CPU:
                return self.parse_json_to_cpu(product_json)

    def parse_json_to_cpu(self, product_json):
        return Processor(product_json["name"], product_json["producer"], product_json["category"],
                         product_json["description"], product_json["price"], product_json["producer_code"],
                         product_json["line"], product_json["model"], product_json["cores"],
                         product_json["threads"], product_json["socket"], product_json["unlocked"],
                         product_json["frequency"], product_json["max_frequency"],
                         product_json["integrated_graphics_unit"], product_json["tdp"],
                         product_json["cooler_included"], product_json["packaging"])

    def parse_product_to_json(self, product: Product):
        if isinstance(product, Processor):
            return self.parse_cpu_to_json(product)

    def parse_cpu_to_json(self, cpu: Processor):
        return {"name": cpu.name, "producer": cpu.producer, "category": cpu.category, "description": cpu.description,
                "price": cpu.price, "producer_code": cpu.producer_code, "line": cpu.line, "model": cpu.model,
                "cores": cpu.cores, "threads": cpu.threads, "socket": cpu.socket, "unlocked": cpu.unlocked,
                "frequency": cpu.frequency, "max_frequency": cpu.max_frequency,
                "integrated_graphics_unit": cpu.integrated_graphics_unit, "tdp": cpu.tdp,
                "cooler_included": cpu.cooler_included, "packaging": cpu.packaging}
