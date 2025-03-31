from product.Product import Product


class Cooler(Product):
    def __init__(self, name: str, producer: str, category: str, description: str, price: float, producer_code: str,
                 socket_compatibility: list[str], lightning: bool, num_of_fans: int, fan_diameter: int, fan_speed: int,
                 noise_level: float):
        super().__init__(name, producer, category, description, price, producer_code)
        self.socket_compatibility: list[str] = socket_compatibility
        self.lightning: bool = lightning
        self.num_of_fans: int = num_of_fans
        self.fan_diameter: int = fan_diameter
        self.fan_speed: int = fan_speed
        self.noise_level: float = noise_level

    def print_product_specs(self):
        super().print_product_specs()
        print("Socket compatibility:", self.socket_compatibility)
        print("Lightning:", self.lightning)
        print("Number of fans:", self.num_of_fans)
        print("Fan diameter:", self.fan_diameter, "mm")
        print("Fan speed:", self.fan_speed, "rpm")
        print("Noise level:", self.noise_level, "dBA")

    def get_socket_compatibility(self):
        return self.socket_compatibility

    def set_socket_compatibility(self, socket_compatibility: list[str]):
        self.socket_compatibility = socket_compatibility

    def get_lightning(self):
        return self.lightning

    def set_lightning(self, lightning: bool):
        self.lightning = lightning

    def get_num_of_fans(self):
        return self.num_of_fans

    def set_num_of_fans(self, num_of_fans: int):
        self.num_of_fans = num_of_fans

    def get_fan_diameter(self):
        return self.fan_diameter

    def set_fan_diameter(self, fan_diameter: int):
        self.fan_diameter = fan_diameter

    def get_fan_speed(self):
        return self.fan_speed

    def set_fan_speed(self, fan_speed: int):
        self.fan_speed = fan_speed

    def get_noise_level(self):
        return self.noise_level

    def set_noise_level(self, noise_level: float):
        self.noise_level = noise_level


class AirCooler(Cooler):

    def __init__(self, name: str, producer: str, category: str, description: str, price: float, producer_code: str,
                 socket_compatibility: list[str], lightning: bool, num_of_fans: int, fan_diameter: int, fan_speed: int,
                 noise_level: float, vertical_installation: bool, height: int, width: int, depth: int,
                 base_material: str,
                 num_of_heat_pipes: int, heat_pipe_diameter: int):
        super().__init__(name, producer, category, description, price, producer_code, socket_compatibility, lightning,
                         num_of_fans, fan_diameter, fan_speed, noise_level)
        self.vertical_installation: bool = vertical_installation
        self.height: int = height
        self.width: int = width
        self.depth: int = depth
        self.base_material: str = base_material
        self.num_of_heat_pipes: int = num_of_heat_pipes
        self.heat_pipe_diameter: int = heat_pipe_diameter

    def print_product_specs(self):
        super().print_product_specs()
        print("Vertical installation:", self.vertical_installation)
        print("Height:", self.height, "mm")
        print("Width:", self.width, "mm")
        print("Depth:", self.depth, "mm")
        print("Base material:", self.base_material)
        print("Number of heat pipes:", self.num_of_heat_pipes)
        print("Heat pipe diameter:", self.heat_pipe_diameter, "mm")
        super().print_end()

    def get_vertical_installation(self):
        return self.vertical_installation

    def set_vertical_installation(self, vertical_installation):
        self.vertical_installation = vertical_installation

    def get_height(self):
        return self.height

    def set_height(self, height: bool):
        self.height = height

    def get_width(self):
        return self.width

    def set_width(self, width: int):
        self.width = width

    def get_depth(self):
        return self.depth

    def set_depth(self, depth: int):
        self.depth = depth

    def get_base_material(self):
        return self.base_material

    def set_base_material(self, base_material: int):
        self.base_material = base_material

    def get_num_of_heat_pipes(self):
        return self.num_of_heat_pipes

    def set_num_of_heat_pipes(self, num_of_heat_pipes: str):
        self.num_of_heat_pipes = num_of_heat_pipes

    def get_heat_pipe_diameter(self):
        return self.heat_pipe_diameter

    def set_heat_pipe_diameter(self, heat_pipe_diameter: int):
        self.heat_pipe_diameter = heat_pipe_diameter


class LiquidCooler(Cooler):
    def __init__(self, name: str, producer: str, category: str, description: str, price: float, producer_code: str,
                 socket_compatibility: list[str], lightning: bool, num_of_fans: int, fan_diameter: int, fan_speed: int,
                 noise_level: float, cooler_size: int):
        super().__init__(name, producer, category, description, price, producer_code, socket_compatibility, lightning,
                         num_of_fans, fan_diameter, fan_speed, noise_level)
        self.cooler_size: int = cooler_size

    def print_product_specs(self):
        super().print_product_specs()
        print("Cooler size:", self.cooler_size, "mm")
        super().print_end()

    def get_cooler_size(self):
        return self.cooler_size

    def set_cooler_size(self, cooler_size: int):
        self.cooler_size = cooler_size
