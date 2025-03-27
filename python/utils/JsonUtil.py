import json
import os

from product.Processor import Processor
from product.Product import Product
from product.ProductCategory import ProductCategory
from product.RAM import RAM
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
            case ProductCategory.RAM:
                return self.parse_json_to_ram(product_json)

    @staticmethod
    def parse_json_to_cpu(product_json):
        return Processor(product_json["name"],
                         product_json["producer"],
                         product_json["category"],
                         product_json["description"],
                         product_json["price"],
                         product_json["producer_code"],
                         product_json["line"],
                         product_json["model"],
                         product_json["cores"],
                         product_json["threads"],
                         product_json["socket"],
                         product_json["unlocked"],
                         product_json["frequency"],
                         product_json["max_frequency"],
                         product_json["integrated_graphics_unit"],
                         product_json["tdp"],
                         product_json["cooler_included"],
                         product_json["packaging"])

    @staticmethod
    def parse_json_to_ram(product_json):
        return RAM(product_json["name"],
                   product_json["producer"],
                   product_json["category"],
                   product_json["description"],
                   product_json["price"],
                   product_json["producer_code"],
                   product_json["line"],
                   product_json["memory_type"],
                   product_json["total_capacity"],
                   product_json["number_of_modules"],
                   product_json["frequency"],
                   product_json["latency"],
                   product_json["lighting"])

    def parse_product_to_json(self, product: Product):
        if isinstance(product, Processor):
            return self.parse_cpu_to_json(product)
        if isinstance(product, RAM):
            return self.parse_ram_to_json(product)

    @staticmethod
    def parse_cpu_to_json(cpu: Processor):
        return {
            "name": cpu.get_name(),
            "producer": cpu.get_producer(),
            "category": cpu.get_category(),
            "description": cpu.get_description(),
            "price": cpu.get_price(),
            "producer_code": cpu.get_producer_code(),
            "line": cpu.get_line(),
            "model": cpu.get_model(),
            "cores": cpu.get_cores(),
            "threads": cpu.get_threads(),
            "socket": cpu.get_socket(),
            "unlocked": cpu.get_unlocked(),
            "frequency": cpu.get_frequency(),
            "max_frequency": cpu.get_max_frequency(),
            "integrated_graphics_unit": cpu.get_integrated_graphics_unit(),
            "tdp": cpu.get_tdp(),
            "cooler_included": cpu.get_cooler_included(),
            "packaging": cpu.get_packaging()
        }

    @staticmethod
    def parse_ram_to_json(ram: RAM):
        return {
            "name": ram.get_name(),
            "producer": ram.get_producer(),
            "category": ram.get_category(),
            "description": ram.get_description(),
            "price": ram.get_price(),
            "producer_code": ram.get_producer_code(),
            "line": ram.get_line(),
            "memory_type": ram.get_memory_type(),
            "total_capacity": ram.get_total_capacity(),
            "number_of_modules": ram.get_number_of_modules(),
            "frequency": ram.get_frequency(),
            "latency": ram.get_latency(),
            "lighting": ram.get_lighting()
        }
