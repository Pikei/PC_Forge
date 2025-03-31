from product.Product import Product


class Case(Product):
    def __init__(self, name: str, producer: str, category: str, description: str, price: float, producer_code: str,
                 color: str, lightning: bool, height: float, width: float, depth: float, weight: float, case_type: str,
                 mb_compatibility: list[str], window: bool, max_gpu_length: float, max_cpu_cooler_height: float,
                 usb20: int, usb30: int, usb31: int, usb32: int, usbc: int, card_reader: bool,
                 headphones_connector: bool, microphone_connector: bool,
                 num_of_internal_25_bays: int, num_of_internal_35_bays: int,
                 num_of_external_35_bays: int, num_of_external_525_bays: int,
                 num_of_extension_slot: int, front_fans: str, back_fans: str, side_fans: str, bottom_fans: str,
                 top_fans: str,
                 power_supply: bool, ps_power: int):
        super().__init__(name, producer, category, description, price, producer_code)
        self.color: str = color
        self.lightning: bool = lightning
        self.height: float = height
        self.width: float = width
        self.depth: float = depth
        self.weight: float = weight
        self.case_type: str = case_type
        self.mb_compatibility: list[str] = mb_compatibility
        self.window: bool = window
        self.max_gpu_length: float = max_gpu_length
        self.max_cpu_cooler_height: float = max_cpu_cooler_height
        self.usb20: int = usb20
        self.usb30: int = usb30
        self.usb31: int = usb31
        self.usb32: int = usb32
        self.usbc: int = usbc
        self.card_reader: bool = card_reader
        self.headphones_connector: bool = headphones_connector
        self.microphone_connector: bool = microphone_connector
        self.num_of_internal_25_bays: int = num_of_internal_25_bays
        self.num_of_internal_35_bays: int = num_of_internal_35_bays
        self.num_of_external_35_bays: int = num_of_external_35_bays
        self.num_of_external_525_bays: int = num_of_external_525_bays
        self.num_of_extension_slot: int = num_of_extension_slot
        self.front_fans: str = front_fans
        self.back_fans: str = back_fans
        self.side_fans: str = side_fans
        self.bottom_fans: str = bottom_fans
        self.top_fans: str = top_fans
        self.power_supply: bool = power_supply
        self.ps_power: int = ps_power

    def print_product_specs(self):
        super().print_product_specs()
        print("Color:", self.color)
        print("Lightning:", self.lightning)
        print("Height:", self.height, "mm")
        print("Width:", self.width, "mm")
        print("Depth:", self.depth, "mm")
        if self.weight > 0.0:
            print("Weight:", self.weight, "kg")
        else:
            print("Weight: no data")
        print("Case type:", self.case_type)
        print("Motherboard standards compatibility:", self.mb_compatibility)
        print("Window:", self.window)
        print("Max GPU length:", self.max_gpu_length, "mm")
        print("Max CPU cooler height:", self.max_cpu_cooler_height, "mm")
        print("Number of USB 2.0 ports:", self.usb20)
        print("Number of USB 3.0 ports:", self.usb30)
        print("Number of USB 3.1 ports:", self.usb31)
        print("Number of USB 3.2 ports:", self.usb32)
        print("Number of USB-C ports:", self.usbc)
        print("Card reader:", self.card_reader)
        print("Headphones connector:", self.headphones_connector)
        print("Microphone connector:", self.microphone_connector)
        print("Number of internal 2.5 inch. bays:", self.num_of_internal_25_bays)
        print("Number of internal 3.5 inch. bays:", self.num_of_internal_35_bays)
        print("Number of external 3.5 inch. bays:", self.num_of_external_35_bays)
        print("Number of external 5.2 inch. bays:", self.num_of_external_525_bays)
        print("Number of extension slots:", self.num_of_extension_slot)
        print("Front panel fans:", self.front_fans)
        print("Back panel fans:", self.back_fans)
        print("Side panel fans:", self.side_fans)
        print("Bottom panel fans:", self.bottom_fans)
        print("Top panel fans:", self.top_fans)
        print("Power supply:", self.power_supply)
        if self.power_supply:
            print("PS power:", self.ps_power)
        super().print_end()

    def get_color(self):
        return self.color

    def set_color(self, color: str):
        self.color = color

    def get_lightning(self):
        return self.lightning

    def set_lightning(self, lightning: bool):
        self.lightning = lightning

    def get_height(self):
        return self.height

    def set_height(self, height: float):
        self.height = height

    def get_width(self):
        return self.width

    def set_width(self, width: float):
        self.width = width

    def get_depth(self):
        return self.depth

    def set_depth(self, depth: float):
        self.depth = depth

    def get_weight(self):
        return self.weight

    def set_weight(self, weight: float):
        self.weight = weight

    def get_case_type(self):
        return self.case_type

    def set_case_type(self, case_type: str):
        self.case_type = case_type

    def get_mb_compatibility(self):
        return self.mb_compatibility

    def set_mb_compatibility(self, mb_compatibility: list[str]):
        self.mb_compatibility = mb_compatibility

    def get_window(self):
        return self.window

    def set_window(self, window: bool):
        self.window = window

    def get_max_gpu_length(self):
        return self.max_gpu_length

    def set_max_gpu_length(self, max_gpu_length: float):
        self.max_gpu_length = max_gpu_length

    def get_max_cpu_cooler_height(self):
        return self.max_cpu_cooler_height

    def set_max_cpu_cooler_height(self, max_cpu_cooler_height: float):
        self.max_cpu_cooler_height = max_cpu_cooler_height

    def get_usb20(self):
        return self.usb20

    def set_usb20(self, usb20: int):
        self.usb20 = usb20

    def get_usb30(self):
        return self.usb30

    def set_usb30(self, usb30: int):
        self.usb30 = usb30

    def get_usb31(self):
        return self.usb31

    def set_usb31(self, usb31: int):
        self.usb31 = usb31

    def get_usb32(self):
        return self.usb32

    def set_usb32(self, usb32: int):
        self.usb32 = usb32

    def get_usbc(self):
        return self.usbc

    def set_usbc(self, usbc: int):
        self.usbc = usbc

    def get_card_reader(self):
        return self.card_reader

    def set_card_reader(self, card_reader: bool):
        self.card_reader = card_reader

    def get_headphones_connector(self):
        return self.headphones_connector

    def set_headphones_connector(self, headphones_connector: bool):
        self.headphones_connector = headphones_connector

    def get_microphone_connector(self):
        return self.microphone_connector

    def set_microphone_connector(self, microphone_connector: bool):
        self.microphone_connector = microphone_connector

    def get_num_of_internal_25_bays(self):
        return self.num_of_internal_25_bays

    def set_num_of_internal_25_bays(self, num_of_internal_25_bays: int):
        self.num_of_internal_25_bays = num_of_internal_25_bays

    def get_num_of_internal_35_bays(self):
        return self.num_of_internal_35_bays

    def set_num_of_internal_35_bays(self, num_of_internal_35_bays: int):
        self.num_of_internal_35_bays = num_of_internal_35_bays

    def get_num_of_external_35_bays(self):
        return self.num_of_external_35_bays

    def set_num_of_external_35_bays(self, num_of_external_35_bays: int):
        self.num_of_external_35_bays = num_of_external_35_bays

    def get_num_of_external_525_bays(self):
        return self.num_of_external_525_bays

    def set_num_of_external_525_bays(self, num_of_external_525_bays: int):
        self.num_of_external_525_bays = num_of_external_525_bays

    def get_num_of_extension_slot(self):
        return self.num_of_extension_slot

    def set_num_of_extension_slot(self, num_of_extension_slot: int):
        self.num_of_extension_slot = num_of_extension_slot

    def get_front_fans(self):
        return self.front_fans

    def set_front_fans(self, front_fans: str):
        self.front_fans = front_fans

    def get_back_fans(self):
        return self.back_fans

    def set_back_fans(self, back_fans: str):
        self.back_fans = back_fans

    def get_side_fans(self):
        return self.side_fans

    def set_side_fans(self, side_fans: str):
        self.side_fans = side_fans

    def get_bottom_fans(self):
        return self.bottom_fans

    def set_bottom_fans(self, bottom_fans: str):
        self.bottom_fans = bottom_fans

    def get_top_fans(self):
        return self.top_fans

    def set_top_fans(self, top_fans: str):
        self.top_fans = top_fans

    def get_power_supply(self):
        return self.power_supply

    def set_power_supply(self, power_supply: bool):
        self.power_supply = power_supply

    def get_ps_power(self):
        return self.ps_power

    def set_ps_power(self, ps_power: int):
        self.ps_power = ps_power
