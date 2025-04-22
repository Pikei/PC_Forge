import json
import os

from product.Case import Case
from product.Cooler import AirCooler, LiquidCooler
from product.Drive import HardDiskDrive, SolidStateDrive
from product.GraphicsCard import GraphicsCard
from product.Motherboard import Motherboard
from product.PowerSupply import PowerSupply
from product.Processor import Processor
from product.Product import Product
from product.ProductCategory import ProductCategory
from product.RAM import RAM
from utils.CommonUtils import CommonUtils


class JsonUtil:
    """
    Klasa odpowiedzialna za odczytywanie i zapisywanie obiektów do pliku JSON
    """

    def __init__(self):
        """
        Konstruktor klasy ``JsonUtil``. Ustawia nazwę pliku docelowego i ścieżkę do niego
        """
        file_name = "saved_products.json"
        self.json_path = os.path.join(os.getcwd(), "json", file_name)
        self.parsed_urls = {}
        CommonUtils.directory_exists("json")

    def save_product(self, url: str, product: Product):
        """
        Metoda zapisująca produkt w pliku JSON
        :param url: adres URL do oryginalnej strony z produktem
        :param product: obiekt klasy rozszerzającej z klasy ``Product``
        """
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
        """
        Metoda ładująca słownik produktów z pliku JSON
        :return: słownik obiektów klas rozszerzających ``Product``
        """
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
        """
        Wywołuje odpowiednią metodę do przetworzenia obiektu w pliku JSON na obiekt korelującej klasy,
        w zależności od kategorii produktu
        :param product_json: obiekt w pliku JSON
        :return: obiekt klasy dziedziczącej z ``Product``
        """
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
            case ProductCategory.CASE:
                return self.parse_json_to_case(product_json)
            case ProductCategory.AIR_COOLER:
                return self.parse_json_to_air_cooler(product_json)
            case ProductCategory.LIQUID_COOLER:
                return self.parse_json_to_liquid_cooler(product_json)
            case ProductCategory.HDD:
                return self.parse_json_to_hdd(product_json)
            case ProductCategory.SSD:
                return self.parse_json_to_ssd(product_json)

    @staticmethod
    def parse_json_to_cpu(product_json):
        """
        Przetwarza obiekt JSON na obiekt klasy ``Processor``
        :param product_json: obiekt JSON
        :return: obiekt klasy ``Processor``
        """
        return Processor(product_json["name"],
                         product_json["producer"],
                         product_json["category"],
                         product_json["description"],
                         product_json["price"],
                         product_json["producer_code"],
                         product_json["EAN"],
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
        """
        Przetwarza obiekt JSON na obiekt klasy ``RAM``
        :param product_json: obiekt JSON
        :return: obiekt klasy ``RAM``
        """
        return RAM(product_json["name"],
                   product_json["producer"],
                   product_json["category"],
                   product_json["description"],
                   product_json["price"],
                   product_json["producer_code"],
                   product_json["EAN"],
                   product_json["line"],
                   product_json["memory_type"],
                   product_json["total_capacity"],
                   product_json["number_of_modules"],
                   product_json["frequency"],
                   product_json["latency"],
                   product_json["lighting"])

    @staticmethod
    def parse_json_to_motherboard(product_json):
        """
        Przetwarza obiekt JSON na obiekt klasy ``Motherboard``
        :param product_json: obiekt JSON
        :return: obiekt klasy ``Motherboard``
        """
        return Motherboard(product_json["name"],
                           product_json["producer"],
                           product_json["category"],
                           product_json["description"],
                           product_json["price"],
                           product_json["producer_code"],
                           product_json["EAN"],
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
        """
        Przetwarza obiekt JSON na obiekt klasy ``GraphicsCard``
        :param product_json: obiekt JSON
        :return: obiekt klasy ``GraphicsCard``
        """
        return GraphicsCard(product_json["name"],
                            product_json["producer"],
                            product_json["category"],
                            product_json["description"],
                            product_json["price"],
                            product_json["producer_code"],
                            product_json["EAN"],
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
        """
        Przetwarza obiekt JSON na obiekt klasy ``PowerSupply``
        :param product_json: obiekt JSON
        :return: obiekt klasy ``PowerSupply``
        """
        return PowerSupply(product_json["name"],
                           product_json["producer"],
                           product_json["category"],
                           product_json["description"],
                           product_json["price"],
                           product_json["producer_code"],
                           product_json["EAN"],
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

    @staticmethod
    def parse_json_to_case(product_json):
        """
        Przetwarza obiekt JSON na obiekt klasy ``Case``
        :param product_json: obiekt JSON
        :return: obiekt klasy ``Case``
        """
        return Case(product_json["name"],
                    product_json["producer"],
                    product_json["category"],
                    product_json["description"],
                    product_json["price"],
                    product_json["producer_code"],
                    product_json["EAN"],
                    product_json["color"],
                    product_json["lightning"],
                    product_json["height"],
                    product_json["width"],
                    product_json["depth"],
                    product_json["weight"],
                    product_json["case_type"],
                    product_json["mb_compatibility"],
                    product_json["window"],
                    product_json["max_gpu_length"],
                    product_json["max_cpu_cooler_height"],
                    product_json["usb20"],
                    product_json["usb30"],
                    product_json["usb31"],
                    product_json["usb32"],
                    product_json["usbc"],
                    product_json["card_reader"],
                    product_json["headphones_connector"],
                    product_json["microphone_connector"],
                    product_json["num_of_internal_25_bays"],
                    product_json["num_of_internal_35_bays"],
                    product_json["num_of_external_35_bays"],
                    product_json["num_of_external_525_bays"],
                    product_json["num_of_extension_slot"],
                    product_json["front_fans"],
                    product_json["back_fans"],
                    product_json["side_fans"],
                    product_json["bottom_fans"],
                    product_json["top_fans"],
                    product_json["power_supply"],
                    product_json["ps_power"])

    @staticmethod
    def parse_json_to_air_cooler(product_json):
        """
        Przetwarza obiekt JSON na obiekt klasy ``AirCooler``
        :param product_json: obiekt JSON
        :return: obiekt klasy ``AirCooler``
        """
        return AirCooler(product_json["name"],
                         product_json["producer"],
                         product_json["category"],
                         product_json["description"],
                         product_json["price"],
                         product_json["producer_code"],
                         product_json["EAN"],
                         product_json["socket_compatibility"],
                         product_json["lightning"],
                         product_json["num_of_fans"],
                         product_json["fan_diameter"],
                         product_json["fan_speed"],
                         product_json["noise_level"],
                         product_json["vertical_installation"],
                         product_json["height"],
                         product_json["width"],
                         product_json["depth"],
                         product_json["base_material"],
                         product_json["num_of_heat_pipes"],
                         product_json["heat_pipe_diameter"])

    @staticmethod
    def parse_json_to_liquid_cooler(product_json):
        """
        Przetwarza obiekt JSON na obiekt klasy ``LiquidCooler``
        :param product_json: obiekt JSON
        :return: obiekt klasy ``LiquidCooler``
        """
        return LiquidCooler(product_json["name"],
                            product_json["producer"],
                            product_json["category"],
                            product_json["description"],
                            product_json["price"],
                            product_json["producer_code"],
                            product_json["EAN"],
                            product_json["socket_compatibility"],
                            product_json["lightning"],
                            product_json["num_of_fans"],
                            product_json["fan_diameter"],
                            product_json["fan_speed"],
                            product_json["noise_level"],
                            product_json["cooler_size"])

    @staticmethod
    def parse_json_to_hdd(product_json):
        """
        Przetwarza obiekt JSON na obiekt klasy ``HardDiskDrive``
        :param product_json: obiekt JSON
        :return: obiekt klasy ``HardDiskDrive``
        """
        return HardDiskDrive(product_json["name"],
                             product_json["producer"],
                             product_json["category"],
                             product_json["description"],
                             product_json["price"],
                             product_json["producer_code"],
                             product_json["EAN"],
                             product_json["drive_format"],
                             product_json["storage"],
                             product_json["interface"],
                             product_json["rotational_speed"])

    @staticmethod
    def parse_json_to_ssd(product_json):
        """
        Przetwarza obiekt JSON na obiekt klasy ``SolidStateDrive``
        :param product_json: obiekt JSON
        :return: obiekt klasy ``SolidStateDrive``
        """
        return SolidStateDrive(product_json["name"],
                               product_json["producer"],
                               product_json["category"],
                               product_json["description"],
                               product_json["price"],
                               product_json["producer_code"],
                               product_json["EAN"],
                               product_json["drive_format"],
                               product_json["storage"],
                               product_json["interface"],
                               product_json["read_speed"],
                               product_json["write_speed"])

    def parse_product_to_json(self, product: Product):
        """
        Wywołuje odpowiednie metody do przetwarzania obiektów na zapis JSON, w zależności od instancji produktu
        :param product: obiekt klasy dziedziczącej z klasy ``Product``
        :return: zapis JSON obiektu klasy dziedziczącej z klasy ``Product``
        """
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
        if isinstance(product, Case):
            return self.parse_case_to_json(product)
        if isinstance(product, AirCooler):
            return self.parse_air_cooler_to_json(product)
        if isinstance(product, LiquidCooler):
            return self.parse_liquid_cooler_to_json(product)
        if isinstance(product, HardDiskDrive):
            return self.parse_hdd_to_json(product)
        if isinstance(product, SolidStateDrive):
            return self.parse_ssd_to_json(product)

    @staticmethod
    def parse_cpu_to_json(cpu: Processor):
        """
        Przetwarza informacje w obiekcie klasy ``Processor`` na zapis JSON
        :param cpu: obiekt klasy ``Processor``
        :return: format JSON obiektu
        """
        return {
            "name": cpu.get_name(),
            "producer": cpu.get_producer(),
            "category": cpu.get_category(),
            "description": cpu.get_description(),
            "price": cpu.get_price(),
            "producer_code": cpu.get_producer_code(),
            "EAN": cpu.get_ean(),
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
        """
        Przetwarza informacje w obiekcie klasy ``RAM`` na zapis JSON
        :param ram: obiekt klasy ``RAM``
        :return: format JSON obiektu
        """
        return {
            "name": ram.get_name(),
            "producer": ram.get_producer(),
            "category": ram.get_category(),
            "description": ram.get_description(),
            "price": ram.get_price(),
            "producer_code": ram.get_producer_code(),
            "EAN": ram.get_ean(),
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
        """
        Przetwarza informacje w obiekcie klasy ``Motherboard`` na zapis JSON
        :param mb: obiekt klasy ``Motherboard``
        :return: format JSON obiektu
        """
        return {
            "name": mb.get_name(),
            "producer": mb.get_producer(),
            "category": mb.get_category(),
            "description": mb.get_description(),
            "price": mb.get_price(),
            "producer_code": mb.get_producer_code(),
            "EAN": mb.get_ean(),
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
        """
        Przetwarza informacje w obiekcie klasy ``GraphicsCard`` na zapis JSON
        :param gpu: obiekt klasy ``GraphicsCard``
        :return: format JSON obiektu
        """
        return {
            "name": gpu.get_name(),
            "producer": gpu.get_producer(),
            "category": gpu.get_category(),
            "description": gpu.get_description(),
            "price": gpu.get_price(),
            "producer_code": gpu.get_producer_code(),
            "chipset_producer": gpu.get_chipset_producer(),
            "EAN": gpu.get_ean(),
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
        """
        Przetwarza informacje w obiekcie klasy ``PowerSupply`` na zapis JSON
        :param ps: obiekt klasy ``PowerSupply``
        :return: format JSON obiektu
        """
        return {
            "name": ps.get_name(),
            "producer": ps.get_producer(),
            "category": ps.get_category(),
            "description": ps.get_description(),
            "price": ps.get_price(),
            "producer_code": ps.get_producer_code(),
            "EAN": ps.get_ean(),
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

    @staticmethod
    def parse_case_to_json(case: Case):
        """
        Przetwarza informacje w obiekcie klasy ``Case`` na zapis JSON
        :param case: obiekt klasy ``Case``
        :return: format JSON obiektu
        """
        return {
            "name": case.get_name(),
            "producer": case.get_producer(),
            "category": case.get_category(),
            "description": case.get_description(),
            "price": case.get_price(),
            "producer_code": case.get_producer_code(),
            "EAN": case.get_ean(),
            "color": case.get_color(),
            "lightning": case.get_lightning(),
            "height": case.get_height(),
            "width": case.get_width(),
            "depth": case.get_depth(),
            "weight": case.get_weight(),
            "case_type": case.get_case_type(),
            "mb_compatibility": case.get_mb_compatibility(),
            "window": case.get_window(),
            "max_gpu_length": case.get_max_gpu_length(),
            "max_cpu_cooler_height": case.get_max_cpu_cooler_height(),
            "usb20": case.get_usb20(),
            "usb30": case.get_usb30(),
            "usb31": case.get_usb31(),
            "usb32": case.get_usb32(),
            "usbc": case.get_usbc(),
            "card_reader": case.get_card_reader(),
            "headphones_connector": case.get_headphones_connector(),
            "microphone_connector": case.get_microphone_connector(),
            "num_of_internal_25_bays": case.get_num_of_internal_25_bays(),
            "num_of_internal_35_bays": case.get_num_of_internal_35_bays(),
            "num_of_external_35_bays": case.get_num_of_external_35_bays(),
            "num_of_external_525_bays": case.get_num_of_external_525_bays(),
            "num_of_extension_slot": case.get_num_of_extension_slot(),
            "front_fans": case.get_front_fans(),
            "back_fans": case.get_back_fans(),
            "side_fans": case.get_side_fans(),
            "bottom_fans": case.get_bottom_fans(),
            "top_fans": case.get_top_fans(),
            "power_supply": case.get_power_supply(),
            "ps_power": case.get_ps_power()
        }

    @staticmethod
    def parse_air_cooler_to_json(cooler: AirCooler):
        """
        Przetwarza informacje w obiekcie klasy ``AirCooler`` na zapis JSON
        :param cooler: obiekt klasy ``AirCooler``
        :return: format JSON obiektu
        """
        return {
            "name": cooler.get_name(),
            "producer": cooler.get_producer(),
            "category": cooler.get_category(),
            "description": cooler.get_description(),
            "price": cooler.get_price(),
            "producer_code": cooler.get_producer_code(),
            "socket_compatibility": cooler.get_socket_compatibility(),
            "lightning": cooler.get_lightning(),
            "num_of_fans": cooler.get_num_of_fans(),
            "fan_diameter": cooler.get_fan_diameter(),
            "fan_speed": cooler.get_fan_speed(),
            "noise_level": cooler.get_noise_level(),
            "vertical_installation": cooler.get_vertical_installation(),
            "height": cooler.get_height(),
            "width": cooler.get_width(),
            "depth": cooler.get_depth(),
            "base_material": cooler.get_base_material(),
            "num_of_heat_pipes": cooler.get_num_of_heat_pipes(),
            "heat_pipe_diameter": cooler.get_heat_pipe_diameter(),
        }

    @staticmethod
    def parse_liquid_cooler_to_json(cooler: LiquidCooler):
        """
        Przetwarza informacje w obiekcie klasy ``LiquidCooler`` na zapis JSON
        :param cooler: obiekt klasy ``LiquidCooler``
        :return: format JSON obiektu
        """
        return {
            "name": cooler.get_name(),
            "producer": cooler.get_producer(),
            "category": cooler.get_category(),
            "description": cooler.get_description(),
            "price": cooler.get_price(),
            "producer_code": cooler.get_producer_code(),
            "EAN": cooler.get_ean(),
            "socket_compatibility": cooler.get_socket_compatibility(),
            "lightning": cooler.get_lightning(),
            "num_of_fans": cooler.get_num_of_fans(),
            "fan_diameter": cooler.get_fan_diameter(),
            "fan_speed": cooler.get_fan_speed(),
            "noise_level": cooler.get_noise_level(),
            "cooler_size": cooler.get_cooler_size(),
        }

    @staticmethod
    def parse_hdd_to_json(hdd: HardDiskDrive):
        """
        Przetwarza informacje w obiekcie klasy ``HardDiskDrive`` na zapis JSON
        :param hdd: obiekt klasy ``HardDiskDrive``
        :return: format JSON obiektu
        """
        return {
            "name": hdd.get_name(),
            "producer": hdd.get_producer(),
            "category": hdd.get_category(),
            "description": hdd.get_description(),
            "price": hdd.get_price(),
            "producer_code": hdd.get_producer_code(),
            "EAN": hdd.get_ean(),
            "drive_format": hdd.get_drive_format(),
            "storage": hdd.get_storage(),
            "interface": hdd.get_interface(),
            "rotational_speed": hdd.get_rotational_speed(),
        }

    @staticmethod
    def parse_ssd_to_json(ssd: SolidStateDrive):
        """
        Przetwarza informacje w obiekcie klasy ``SolidStateDrive`` na zapis JSON
        :param ssd: obiekt klasy ``SolidStateDrive``
        :return: format JSON obiektu
        """
        return {
            "name": ssd.get_name(),
            "producer": ssd.get_producer(),
            "category": ssd.get_category(),
            "description": ssd.get_description(),
            "price": ssd.get_price(),
            "producer_code": ssd.get_producer_code(),
            "EAN": ssd.get_ean(),
            "drive_format": ssd.get_drive_format(),
            "storage": ssd.get_storage(),
            "interface": ssd.get_interface(),
            "read_speed": ssd.get_read_speed(),
            "write_speed": ssd.get_write_speed()
        }
