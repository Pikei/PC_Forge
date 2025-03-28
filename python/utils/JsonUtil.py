import json
import os

from product.GraphicsCard import GraphicsCard
from product.Motherboard import Motherboard
from product.PowerSuppy import PowerSupply
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
            case ProductCategory.GPU:
                return self.parse_json_to_graphics_card(product_json)
            case ProductCategory.POWER_SUPPLY:
                return self.parse_json_to_power_supply(product_json)

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

    @staticmethod
    def parse_json_to_graphics_card(product_json):
        return GraphicsCard(product_json["name"],
                            product_json["producer"],
                            product_json["category"],
                            product_json["description"],
                            product_json["price"],
                            product_json["producer_code"],
                            product_json["chipset_producer"],
                            product_json["chipset"],
                            product_json["core_frequency"],
                            product_json["max_core_frequency"],
                            product_json["stream_processors"],
                            product_json["rop_units"],
                            product_json["texturing_units"],
                            product_json["rt_cores"],
                            product_json["tensor_cores"],
                            product_json["dlss"],
                            product_json["connector"],
                            product_json["card_length"],
                            product_json["resolution"],
                            product_json["recommended_ps"],
                            product_json["lightning"],
                            product_json["ram"],
                            product_json["ram_type"],
                            product_json["data_bus"],
                            product_json["memory_freq"],
                            product_json["cooling_type"],
                            product_json["number_of_fans"])

    @staticmethod
    def parse_json_to_power_supply(product_json):
        return PowerSupply(product_json["name"],
                           product_json["producer"],
                           product_json["category"],
                           product_json["description"],
                           product_json["price"],
                           product_json["producer_code"],
                           product_json["standard"],
                           product_json["power"],
                           product_json["efficiency_certificate"],
                           product_json["efficiency"],
                           product_json["cooling_type"],
                           product_json["fan_diameter"],
                           product_json["protections"],
                           product_json["modular_cabling"],
                           product_json["atx24"],
                           product_json["pcie16"],
                           product_json["pcie8"],
                           product_json["pcie6"],
                           product_json["cpu8"],
                           product_json["cpu4"],
                           product_json["sata"],
                           product_json["molex"],
                           product_json["height"],
                           product_json["width"],
                           product_json["depth"],
                           product_json["lightning"])
    
    def parse_product_to_json(self, product: Product):
        if isinstance(product, Processor):
            return self.parse_cpu_to_json(product)
        if isinstance(product, RAM):
            return self.parse_ram_to_json(product)
        if isinstance(product, Motherboard):
            return self.parse_motherboard_to_json(product)
        if isinstance(product, GraphicsCard):
            return self.parse_graphics_card_to_json(product)
        if isinstance(product, PowerSupply):
            return self.parse_power_supply_to_json(product)

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

    @staticmethod
    def parse_graphics_card_to_json(gpu: GraphicsCard):
        return {
            "name": gpu.get_name(),
            "producer": gpu.get_producer(),
            "category": gpu.get_category(),
            "description": gpu.get_description(),
            "price": gpu.get_price(),
            "producer_code": gpu.get_producer_code(),
            "chipset_producer": gpu.get_chipset_producer(),
            "chipset": gpu.get_chipset(),
            "core_frequency": gpu.get_core_frequency(),
            "max_core_frequency": gpu.get_max_core_frequency(),
            "stream_processors": gpu.get_stream_processors(),
            "rop_units": gpu.get_rop_units(),
            "texturing_units": gpu.get_texturing_units(),
            "rt_cores": gpu.get_rt_cores(),
            "tensor_cores": gpu.get_tensor_cores(),
            "dlss": gpu.get_dlss(),
            "connector": gpu.get_connector(),
            "card_length": gpu.get_card_length(),
            "resolution": gpu.get_resolution(),
            "recommended_ps": gpu.get_recommended_ps(),
            "lightning": gpu.get_lightning(),
            "ram": gpu.get_ram(),
            "ram_type": gpu.get_ram_type(),
            "data_bus": gpu.get_data_bus(),
            "memory_freq": gpu.get_memory_freq(),
            "cooling_type": gpu.get_cooling_type(),
            "number_of_fans": gpu.get_number_of_fans()
        }

    @staticmethod
    def parse_power_supply_to_json(ps: PowerSupply):
        return {
            "name": ps.get_name(),
            "producer": ps.get_producer(),
            "category": ps.get_category(),
            "description": ps.get_description(),
            "price": ps.get_price(),
            "producer_code": ps.get_producer_code(),
            "standard": ps.get_standard(),
            "power": ps.get_power(),
            "efficiency_certificate": ps.get_efficiency_certificate(),
            "efficiency": ps.get_efficiency(),
            "cooling_type": ps.get_cooling_type(),
            "fan_diameter": ps.get_fan_diameter(),
            "protections": ps.get_protections(),
            "modular_cabling": ps.get_modular_cabling(),
            "atx24": ps.get_atx24(),
            "pcie16": ps.get_pcie16(),
            "pcie8": ps.get_pcie8(),
            "pcie6": ps.get_pcie6(),
            "cpu8": ps.get_cpu8(),
            "cpu4": ps.get_cpu4(),
            "sata": ps.get_sata(),
            "molex": ps.get_molex(),
            "height": ps.get_height(),
            "width": ps.get_width(),
            "depth": ps.get_depth(),
            "lightning": ps.get_lightning()
        }
