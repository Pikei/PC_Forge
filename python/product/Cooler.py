import sqlalchemy
from sqlalchemy import Column, ForeignKey

from product.Product import Product, Base


class CoolerCpuCompatibility(Base):
    __tablename__ = "cooler_socket_compatibility"
    socket_id = Column("socket_id", ForeignKey("cpu_socket.socket_id", ondelete="CASCADE", onupdate="CASCADE"),
                       primary_key=True, nullable=False)
    ean = Column("ean", ForeignKey("cooler.ean", ondelete="CASCADE", onupdate="CASCADE"), primary_key=True,
                 nullable=False)

class Cooler(Product):
    __tablename__ = "cooler"
    ean = Column("ean", ForeignKey("product.ean", ondelete="CASCADE", onupdate="CASCADE"), primary_key=True,
                 nullable=False)
    lightning = Column("lightning", sqlalchemy.Boolean, nullable=False)
    num_of_fans = Column("fans", sqlalchemy.Integer, nullable=False)
    fan_diameter = Column("fan_diameter", sqlalchemy.Integer, nullable=False)
    fan_speed = Column("fan_speed", sqlalchemy.Integer, nullable=False)
    noise_level = Column("noise_level", sqlalchemy.Float, nullable=False)


    def __init__(self, name: str, producer: str, category: str, description: str, price: float, producer_code: str,
                 ean: int,
                 socket_compatibility: list[str], lightning: bool, num_of_fans: int, fan_diameter: int, fan_speed: int,
                 noise_level: float):
        super().__init__(name, producer, category, description, price, producer_code, ean)
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
    __tablename__ = "air_cooler"
    ean = Column("ean", ForeignKey("cooler.ean", ondelete="CASCADE", onupdate="CASCADE"), primary_key=True,
                 nullable=False)
    vertical_installation = Column("vertical_installation", sqlalchemy.Boolean, nullable=False)
    height = Column("height", sqlalchemy.Integer, nullable=False)
    width = Column("width", sqlalchemy.Integer, nullable=False)
    depth = Column("depth", sqlalchemy.Integer, nullable=False)
    base_material = Column("base_material", sqlalchemy.VARCHAR(255), nullable=False)
    num_of_heat_pipes = Column("heat_pipes", sqlalchemy.Integer, nullable=False)
    heat_pipe_diameter = Column("heat_pipe_diameter", sqlalchemy.Integer, nullable=False)

    def __init__(self, name: str, producer: str, category: str, description: str, price: float, producer_code: str,
                 ean: int,
                 socket_compatibility: list[str], lightning: bool, num_of_fans: int, fan_diameter: int, fan_speed: int,
                 noise_level: float, vertical_installation: bool, height: int, width: int, depth: int,
                 base_material: str,
                 num_of_heat_pipes: int, heat_pipe_diameter: int):
        super().__init__(name, producer, category, description, price, producer_code, ean, socket_compatibility,
                         lightning,
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
    __tablename__ = "liquid_cooler"
    ean = Column("ean", ForeignKey("cooler.ean", ondelete="CASCADE", onupdate="CASCADE"), primary_key=True,
                 nullable=False)
    cooler_size = Column("cooler_size", sqlalchemy.Integer, nullable=False)
    def __init__(self, name: str, producer: str, category: str, description: str, price: float, producer_code: str,
                 ean: int,
                 socket_compatibility: list[str], lightning: bool, num_of_fans: int, fan_diameter: int, fan_speed: int,
                 noise_level: float, cooler_size: int):
        super().__init__(name, producer, category, description, price, producer_code, ean, socket_compatibility,
                         lightning,
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
