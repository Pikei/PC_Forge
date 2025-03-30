from product.Case import Case
from product.GraphicsCard import GraphicsCard
from product.Motherboard import Motherboard
from product.PowerSuppy import PowerSupply
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
            case str(ProductCategory.GPU):
                return ProductValidator.validate_gpu(product)
            case str(ProductCategory.POWER_SUPPLY):
                return ProductValidator.validate_power_supply(product)
            case str(ProductCategory.CASE):
                return ProductValidator.validate_case(product)
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

    @staticmethod
    def validate_gpu(gpu: Product):
        if isinstance(gpu, GraphicsCard):
            if gpu.get_chipset_producer() is None or gpu.get_chipset_producer() == "":
                return ProductValidator.log_error("Could not find Chipset producer")
            if gpu.get_chipset() is None or gpu.get_chipset() == "":
                return ProductValidator.log_error("Could not find Chipset")
            if gpu.get_core_frequency() is None or gpu.get_core_frequency() <= 0:
                return ProductValidator.log_error("Could not find Core frequency")
            if gpu.get_max_core_frequency() is None:
                return ProductValidator.log_error("Could not find Max core frequency")
            if gpu.get_stream_processors() is None or gpu.get_stream_processors() <= 0:
                return ProductValidator.log_error("Could not find Stream processors")
            if gpu.get_rop_units() is None or gpu.get_rop_units() <= 0:
                return ProductValidator.log_error("Could not find ROP units")
            if gpu.get_texturing_units() is None or gpu.get_texturing_units() <= 0:
                return ProductValidator.log_error("Could not find Texturing units")
            if gpu.get_rt_cores() is None:
                return ProductValidator.log_error("Could not find RT cores")
            if gpu.get_tensor_cores() is None:
                return ProductValidator.log_error("Could not find Tensor cores")
            if gpu.get_dlss() is None or gpu.get_dlss() == "":
                return ProductValidator.log_error("Could not find DLSS")
            if gpu.get_connector() is None or gpu.get_connector() == "":
                return ProductValidator.log_error("Could not find Connector")
            if gpu.get_card_length() is None or gpu.get_card_length() <= 0:
                return ProductValidator.log_error("Could not find Card length")
            if gpu.get_resolution() is None or gpu.get_resolution() == "":
                return ProductValidator.log_error("Could not find Resolution")
            if gpu.get_recommended_ps() is None or gpu.get_recommended_ps() <= 0:
                return ProductValidator.log_error("Could not find Recommended power of power supply")
            if gpu.get_lightning() is None:
                return ProductValidator.log_error("Could not find Lightning")
            if gpu.get_ram() is None or gpu.get_ram() == 0:
                return ProductValidator.log_error("Could not find RAM")
            if gpu.get_ram_type() is None or gpu.get_ram_type() == "":
                return ProductValidator.log_error("Could not find RAM type")
            if gpu.get_data_bus() is None or gpu.get_data_bus() <= 0:
                return ProductValidator.log_error("Could not find Data bus")
            if gpu.get_memory_freq() is None or gpu.get_memory_freq() <= 0:
                return ProductValidator.log_error("Could not find Memory frequency")
            if gpu.get_cooling_type() is None or gpu.get_cooling_type() == "":
                return ProductValidator.log_error("Could not find Cooling type")
            if gpu.get_number_of_fans() is None:
                return ProductValidator.log_error("Could not find Number of fans")
        else:
            return ProductValidator.log_error(
                f"Could not parse product of category '{gpu.get_category()}' to object of matching class.")
        return True

    @staticmethod
    def validate_power_supply(ps: Product):
        if isinstance(ps, PowerSupply):
            if ps.get_standard() is None or ps.get_standard() == "":
                return ProductValidator.log_error("Could not find standard")
            if ps.get_power() is None or ps.get_power() <= 0:
                return ProductValidator.log_error("Could not find power")
            if ps.get_efficiency() is None:
                return ProductValidator.log_error("Could not find efficiency")
            if ps.get_cooling_type() is None or ps.get_cooling_type() == "":
                return ProductValidator.log_error("Could not find cooling type")
            if ps.get_fan_diameter() is None:
                return ProductValidator.log_error("Could not find fan diameter")
            if ps.get_protections() is None:
                return ProductValidator.log_error("Could not find protections")
            if ps.get_modular_cabling() is None:
                return ProductValidator.log_error("Could not find if cabling is modular or not")
            if ps.get_atx24() is None:
                return ProductValidator.log_error("Could not find number of ATX 24pin connectors")
            if ps.get_pcie16() is None:
                return ProductValidator.log_error("Could not find number of PCI-E 16pin connectors")
            if ps.get_pcie8() is None:
                return ProductValidator.log_error("Could not find number of PCI-E 8pin connectors")
            if ps.get_pcie6() is None:
                return ProductValidator.log_error("Could not find number of PCI-E 6pin connectors")
            if ps.get_cpu8() is None:
                return ProductValidator.log_error("Could not find number of CPU 8pin connectors")
            if ps.get_cpu4() is None:
                return ProductValidator.log_error("Could not find number of CPU 4pin connectors")
            if ps.get_sata() is None:
                return ProductValidator.log_error("Could not find number of SATA connectors")
            if ps.get_molex() is None:
                return ProductValidator.log_error("Could not find number of Molex connectors")
            if ps.get_height() is None or ps.get_height() <= 0:
                return ProductValidator.log_error("Could not find height")
            if ps.get_width() is None or ps.get_width() <= 0:
                return ProductValidator.log_error("Could not find width")
            if ps.get_depth() is None or ps.get_depth() <= 0:
                return ProductValidator.log_error("Could not find depth")
            if ps.get_lightning() is None:
                return ProductValidator.log_error("Could not find lightning")
        else:
            return ProductValidator.log_error(
                f"Could not parse product of category '{ps.get_category()}' to object of matching class.")
        return True

    @staticmethod
    def validate_case(case: Product):
        if isinstance(case, Case):
            if case.get_color() is None or case.get_color() == "":
                return ProductValidator.log_error("Could not find color")
            if case.get_lightning() is None:
                return ProductValidator.log_error("Could not find if case has lightning or not")
            if case.get_height() is None or case.get_height() <= 0.0:
                return ProductValidator.log_error("Could not find height")
            if case.get_width() is None or case.get_width() <= 0.0:
                return ProductValidator.log_error("Could not find width")
            if case.get_depth() is None or case.get_depth() <= 0.0:
                return ProductValidator.log_error("Could not find depth")
            if case.get_weight() is None or case.get_weight() <= 0.0:
                return ProductValidator.log_error("Could not find weight")
            if case.get_case_type() is None or case.get_case_type() == "":
                return ProductValidator.log_error("Could not find case type")
            if case.get_mb_compatibility() is None or len(case.get_mb_compatibility()) == 0:
                return ProductValidator.log_error("Could not find motherboard standards compatibility")
            if case.get_window() is None:
                return ProductValidator.log_error("Could not find if case has window or not")
            if case.get_max_gpu_length() is None or case.get_max_gpu_length() <= 0.0:
                return ProductValidator.log_error("Could not find max GPU length")
            if case.get_max_cpu_cooler_height() is None or case.get_max_cpu_cooler_height() <= 0.0:
                return ProductValidator.log_error("Could not find max COU cooler height")
            if case.get_usb20() is None:
                return ProductValidator.log_error("Could not find number of USB 2.0 ports")
            if case.get_usb30() is None:
                return ProductValidator.log_error("Could not find number of USB 3.0 ports")
            if case.get_usb31() is None:
                return ProductValidator.log_error("Could not find number of USB 3.1 ports")
            if case.get_usb32() is None:
                return ProductValidator.log_error("Could not find number of USB 3.2 ports")
            if case.get_usbc() is None:
                return ProductValidator.log_error("Could not find number of USB-C ports")
            if case.get_card_reader() is None:
                return ProductValidator.log_error("Could not find card reader")
            if case.get_headphones_connector() is None:
                return ProductValidator.log_error("Could not find if case has headphones connector or not")
            if case.get_microphone_connector() is None:
                return ProductValidator.log_error("Could not find if case has microphone connector or not")
            if case.get_num_of_internal_25_bays() is None:
                return ProductValidator.log_error("Could not find number of internal 2.5 inch. bays")
            if case.get_num_of_internal_35_bays() is None:
                return ProductValidator.log_error("Could not find number of internal 3.5 inch. bays")
            if case.get_num_of_external_35_bays() is None:
                return ProductValidator.log_error("Could not find number of external 3.5 inch. bays")
            if case.get_num_of_external_525_bays() is None:
                return ProductValidator.log_error("Could not find number of external 5.2 inch. bays")
            if case.get_num_of_extension_slot() is None:
                return ProductValidator.log_error("Could not find number of extension slots")
            if case.get_front_fans() is None or case.get_front_fans() == "":
                return ProductValidator.log_error("Could not find front panel fans")
            if case.get_side_fans() is None or case.get_side_fans() == "":
                return ProductValidator.log_error("Could not find side panel fans")
            if case.get_bottom_fans() is None or case.get_bottom_fans() == "":
                return ProductValidator.log_error("Could not find bottom panel fans")
            if case.get_top_fans() is None or case.get_top_fans() == "":
                return ProductValidator.log_error("Could not find top panel fans")
            if case.get_power_supply() is None:
                return ProductValidator.log_error("Could not find if case has power supply or not")
            if case.get_ps_power() is None:
                return ProductValidator.log_error("Could not find PS power")
        else:
            return ProductValidator.log_error(
                f"Could not parse product of category '{case.get_category()}' to object of matching class.")
        return True
