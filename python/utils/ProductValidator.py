from product.Processor import Processor
from product.Product import Product
from product.ProductCategory import ProductCategory
from product.RAM import RAM


class ProductValidator:

    @staticmethod
    def log_error(message):
        print("ERROR: An error occurred while parsing product.", message)
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
