from product.Motherboard import Motherboard
from product.Processor import Processor
from product.Product import Product
from product.ProductCategory import ProductCategory
from product.RAM import RAM


class ProductValidator:

    @staticmethod
    def log_error(message):
        print("ERROR:", message)
        return False

    @staticmethod
    def validate(product: Product):
        if product is None:
            return False
        if product.get_name() is None or product.get_name() == '':
            return ProductValidator.log_error("Could not find product name")
        if product.get_producer() is None or product.get_producer() == '':
            return ProductValidator.log_error("Could not find producer")
        if product.get_category() is None or product.get_category() == '':
            return ProductValidator.log_error("Could not find product category")
        if product.get_description() is None or product.get_description() == '':
            return ProductValidator.log_error("Could not find description")
        if product.get_price() is None or product.get_price() <= 0.0:
            return ProductValidator.log_error("Could not find product price")
        if product.get_producer_code() is None or product.get_producer_code() == '':
            return ProductValidator.log_error("Could not find producer code")

        match product.get_category():
            case str(ProductCategory.CPU):
                return ProductValidator.validate_cpu(product)
            case str(ProductCategory.RAM):
                return ProductValidator.validate_ram(product)
            case str(ProductCategory.MB):
                return ProductValidator.validate_motherboard(product)
        return False

    @staticmethod
    def validate_cpu(cpu: Product):
        if isinstance(cpu, Processor):
            if cpu.get_line() is None or cpu.get_line() == '':
                return ProductValidator.log_error("Could not find line")
            if cpu.get_model() is None or cpu.get_model() == '':
                return ProductValidator.log_error("Could not find model")
            if cpu.get_cores() is None or cpu.get_cores() <= 0:
                return ProductValidator.log_error("Could not find number of cores")
            if cpu.get_threads() is None or cpu.get_threads() <= 0:
                return ProductValidator.log_error("Could not find number of threads")
            if cpu.get_socket() is None or cpu.get_socket() == '':
                return ProductValidator.log_error("Could not find socket")
            if cpu.get_unlocked() is None:
                return ProductValidator.log_error("Could not find if processor is unlocked")
            if cpu.get_frequency() is None or cpu.get_frequency() <= 0.0:
                return ProductValidator.log_error("Could not find frequency")
            if cpu.get_max_frequency() is None or cpu.get_max_frequency() <= 0.0:
                return ProductValidator.log_error("Could not find max frequency")
            if cpu.get_integrated_graphics_unit() == '':
                return ProductValidator.log_error("Could not find integrated graphics unit")
            if cpu.get_tdp() is None or cpu.get_tdp() <= 0:
                return ProductValidator.log_error("Could not find TDP")
            if cpu.get_cooler_included() is None:
                return ProductValidator.log_error("Could not find if cooler is included")
            if cpu.get_packaging() is None or cpu.get_packaging() == '':
                return ProductValidator.log_error("Could not find packaging")
        else:
            return ProductValidator.log_error(
                f"Could not parse product of category '{cpu.get_category()}' to object of matching class.")
        return True

    @staticmethod
    def validate_ram(ram: Product):
        if isinstance(ram, RAM):
            if ram.get_line() is None or ram.get_line() == '':
                return ProductValidator.log_error("Could not find line")
            if ram.get_memory_type() is None or ram.get_memory_type() == '':
                return ProductValidator.log_error("Could not find memory type")
            if ram.get_total_capacity() is None or ram.get_total_capacity() == 0:
                return ProductValidator.log_error("Could not find total capacity")
            if ram.get_number_of_modules() is None or ram.get_number_of_modules() <= 0:
                return ProductValidator.log_error("Could not find number of modules")
            if ram.get_frequency() is None or ram.get_frequency() <= 0.0:
                return ProductValidator.log_error("Could not find frequency")
            if ram.get_latency() is None or ram.get_latency() == '':
                return ProductValidator.log_error("Could not find latency")
            if ram.get_lighting() is None:
                return ProductValidator.log_error("Could not find lighting")
        else:
            return ProductValidator.log_error(
                f"Could not parse product of category '{ram.get_category()}' to object of matching class.")
        return True

    @staticmethod
    def validate_motherboard(mb: Product):
        if isinstance(mb, Motherboard):
            if mb.get_standard() is None or mb.get_standard() == "":
                return ProductValidator.log_error("Could not find motherboard standard")
            if mb.get_chipset() is None or mb.get_chipset() == "":
                return ProductValidator.log_error("Could not find chipset")
            if mb.get_cpu_socket() is None or mb.get_cpu_socket() == "":
                return ProductValidator.log_error("Could not find CPU socket")
            if mb.get_memory_standard() is None or mb.get_memory_standard() == "":
                return ProductValidator.log_error("Could not find memory standard")
            if mb.get_number_of_memory_slots() is None or mb.get_number_of_memory_slots() <= 0:
                return ProductValidator.log_error("Could not find number of memory slots")
            if mb.get_supported_memory_frequencies() is None or len(mb.get_supported_memory_frequencies()) == 0:
                return ProductValidator.log_error("Could not find supported memory frequencies")
            if mb.get_max_memory_capacity() is None or mb.get_max_memory_capacity() <= 0:
                return ProductValidator.log_error("Could not find max memory capacity")
            if mb.get_integrated_audio_card() is None or mb.get_integrated_audio_card() == "":
                return ProductValidator.log_error("Could not find integrated audio card")
            if mb.get_audio_channels() is None or mb.get_audio_channels() <= 0.0:
                return ProductValidator.log_error("Could not find audio channels")
            if mb.get_integrated_network_card() is None or mb.get_integrated_network_card() == "":
                return ProductValidator.log_error("Could not find integrated network card")
            if mb.get_bluetooth() is None:
                return ProductValidator.log_error("Could not find if motherboard has bluetooth or not")
            if mb.get_wifi() is None:
                return ProductValidator.log_error("Could not find if motherboard has Wi-Fi or not")
            if mb.get_expansion_slots() is None or len(mb.get_expansion_slots()) == 0:
                return ProductValidator.log_error("Could not find expansion slots")
            if mb.get_drive_interfaces() is None or len(mb.get_drive_interfaces()) == 0:
                return ProductValidator.log_error("Could not find drive interfaces")
            if mb.get_outside_connectors() is None or len(mb.get_outside_connectors()) == 0:
                return ProductValidator.log_error("Could not find outside connectors")
            if mb.get_width() is None or mb.get_width() <= 0.0:
                return ProductValidator.log_error("Could not find width")
            if mb.get_depth() is None or mb.get_depth() <= 0.0:
                return ProductValidator.log_error("Could not find depth")
        else:
            return ProductValidator.log_error(
                f"Could not parse product of category '{mb.get_category()}' to object of matching class.")
        return True
