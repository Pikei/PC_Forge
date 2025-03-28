import json
import os

from product.Motherboard import Motherboard
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
            case ProductCategory.MB:
                return self.parse_json_to_motherboard(product_json)

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

    @staticmethod
    def parse_json_to_motherboard(product_json):
        return Motherboard(product_json["name"],
                           product_json["producer"],
                           product_json["category"],
                           product_json["description"],
                           product_json["price"],
                           product_json["producer_code"],
                           product_json["standard"],
                           product_json["chipset"],
                           product_json["cpu_socket"],
                           product_json["memory_standard"],
                           product_json["number_of_memory_slots"],
                           product_json["supported_memory_frequencies"],
                           product_json["max_memory_capacity"],
                           product_json["integrated_audio_card"],
                           product_json["audio_channels"],
                           product_json["integrated_network_card"],
                           product_json["bluetooth"],
                           product_json["wifi"],
                           product_json["expansion_slots"],
                           product_json["drive_interfaces"],
                           product_json["outside_connectors"],
                           product_json["width"],
                           product_json["depth"])

    def parse_product_to_json(self, product: Product):
        if isinstance(product, Processor):
            return self.parse_cpu_to_json(product)
        if isinstance(product, RAM):
            return self.parse_ram_to_json(product)
        if isinstance(product, Motherboard):
            return self.parse_motherboard_to_json(product)

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

    @staticmethod
    def parse_motherboard_to_json(mb: Motherboard):
        return {
            "name": mb.get_name(),
            "producer": mb.get_producer(),
            "category": mb.get_category(),
            "description": mb.get_description(),
            "price": mb.get_price(),
            "producer_code": mb.get_producer_code(),
            "standard": mb.get_standard(),
            "chipset": mb.get_chipset(),
            "cpu_socket": mb.get_cpu_socket(),
            "memory_standard": mb.get_memory_standard(),
            "number_of_memory_slots": mb.get_number_of_memory_slots(),
            "supported_memory_frequencies": mb.get_supported_memory_frequencies(),
            "max_memory_capacity": mb.get_max_memory_capacity(),
            "integrated_audio_card": mb.get_integrated_audio_card(),
            "audio_channels": mb.get_audio_channels(),
            "integrated_network_card": mb.get_integrated_network_card(),
            "bluetooth": mb.get_bluetooth(),
            "wifi": mb.get_wifi(),
            "expansion_slots": mb.get_expansion_slots(),
            "drive_interfaces": mb.get_drive_interfaces(),
            "outside_connectors": mb.get_outside_connectors(),
            "width": mb.get_width(),
            "depth": mb.get_depth()
        }
