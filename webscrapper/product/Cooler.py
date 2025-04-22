import sqlalchemy
from sqlalchemy import Column, ForeignKey, Integer, Boolean, Float

from product.Product import Product, Base


class CoolerCpuCompatibility(Base):
    """
    Klasa będąca reprezentacją tabeli cooler_socket_compatibility w bazie danych.
    Przechowuje id gniazd procesorów i numery EAN chłodzeń jako relacja wiele do wielu.
    """
    __tablename__ = "cooler_socket_compatibility"
    compatibility_id = Column("compatibility_id", Integer, primary_key=True, autoincrement=True)
    socket_id = Column("socket_id", ForeignKey("cpu_socket.socket_id", ondelete="CASCADE", onupdate="CASCADE"),
                       primary_key=True, nullable=False)
    ean = Column("ean", ForeignKey("cooler.ean", ondelete="CASCADE", onupdate="CASCADE"), primary_key=True,
                 nullable=False)


class Cooler(Product):
    """
    Klasa będąca reprezentacją tabeli ``cooler`` w bazie danych. Jest ona nadtypem dla chłodzeń powietrznych i wodnych.
    Zawiera informacje wspólne dla wszystkich typów chłodzeń.
    """
    __tablename__ = "cooler"
    ean = Column("ean", ForeignKey("product.ean", ondelete="CASCADE", onupdate="CASCADE"), primary_key=True,
                 nullable=False)
    lightning = Column("lightning", Boolean, nullable=False)
    num_of_fans = Column("fans", Integer, nullable=False)
    fan_diameter = Column("fan_diameter", Integer, nullable=False)
    fan_speed = Column("fan_speed", Integer)
    noise_level = Column("noise_level", Float)

    def __init__(self, name: str, producer: str, category: str, description: str, price: float, producer_code: str,
                 ean: int,
                 socket_compatibility: list[str], lightning: bool, num_of_fans: int, fan_diameter: int, fan_speed: int,
                 noise_level: float):
        """
        Konstruktor klasy ``Cooler``
        :param name: nazwa układu chłodzenia procesora
        :param producer: producent układu chłodzenia procesora
        :param category: kod kategorii (``AIR_COOLER`` lub ``LIQUID_COOLER``)
        :param description: opis układu chłodzenia procesora w kodzie HTML
        :param price: cena układu chłodzenia procesora
        :param producer_code: kod producenta układu chłodzenia procesora
        :param ean: kod EAN (European Article Number)
        :param socket_compatibility: lista gniazd procesora, z którymi kompatybilny jest układ chłodzenie
        :param lightning: informacja, czy układ chłodzenia ma podświetlenie
        :param num_of_fans: liczba wiatraków
        :param fan_diameter: średnica wiatraków
        :param fan_speed: prędkość obrotowa wiatraków [obr./min]
        :param noise_level: poziom głośności generowany przez układ chłodzenia
        """
        super().__init__(name, producer, category, description, price, producer_code, ean)
        self.socket_compatibility: list[str] = socket_compatibility
        self.lightning: bool = lightning
        self.num_of_fans: int = num_of_fans
        self.fan_diameter: int = fan_diameter
        self.fan_speed: int = fan_speed
        self.noise_level: float = noise_level

    def print_product_specs(self):
        """
        Metoda wypisująca specyfikacje dla układów chłodzeń procesora
        """
        super().print_product_specs()
        print("Socket compatibility:", self.socket_compatibility)
        print("Lightning:", self.lightning)
        print("Number of fans:", self.num_of_fans)
        print("Fan diameter:", self.fan_diameter, "mm")
        print("Fan speed:", self.fan_speed, "rpm")
        print("Noise level:", self.noise_level, "dBA")

    def get_socket_compatibility(self):
        """
        Getter listy kompatybilnych gniazd procesora
        :return: lista kompatybilnych gniazd procesora
        """
        return self.socket_compatibility

    def set_socket_compatibility(self, socket_compatibility: list[str]):
        """
        Setter listy kompatybilnych gniazd procesora
        :param socket_compatibility: lista kompatybilnych gniazd procesora
        """
        self.socket_compatibility = socket_compatibility

    def get_lightning(self):
        """
        Getter informacji czy płyta główna posiada podświetlenie
        :return: ``True`` jeśli płyta główna posiada podświetlenie, ``False`` w przeciwnym razie
        """
        return self.lightning

    def set_lightning(self, lightning: bool):
        """
        Setter informacji, czy płyta główna posiada podświetlenie
        :param lightning: informacja, czy płyta główna posiada podświetlenie (``True`` lub ``False``)
        """
        self.lightning = lightning

    def get_num_of_fans(self):
        """
        Getter liczby wiatraków w układzie chłodzenia
        :return: liczba wiatraków
        """
        return self.num_of_fans

    def set_num_of_fans(self, num_of_fans: int):
        """
        Setter liczby wiatraków w układzie chłodzenia
        :param num_of_fans: liczba wiatraków
        """
        self.num_of_fans = num_of_fans

    def get_fan_diameter(self):
        """
        Getter średnicy wiatraków w układzie chłodzenia
        :return: średnicy wiatraków
        """
        return self.fan_diameter

    def set_fan_diameter(self, fan_diameter: int):
        """
        Setter średnicy wiatraków w układzie chłodzenia
        :param fan_diameter: średnicy wiatraków
        """
        self.fan_diameter = fan_diameter

    def get_fan_speed(self):
        """
        Getter prędkości obrotowej wiatraków w układzie chłodzenia
        :return: prędkość obrotowa wiatraków [obr./min]
        """
        return self.fan_speed

    def set_fan_speed(self, fan_speed: int):
        """
        Setter prędkości obrotowej wiatraków w układzie chłodzenia
        :param fan_speed: prędkość obrotowa wiatraków [obr./min]
        """
        self.fan_speed = fan_speed

    def get_noise_level(self):
        """
        Getter generowanego przez układ chłodzenia poziomu hałasu
        :return: poziom hałasu [dBA]
        """
        return self.noise_level

    def set_noise_level(self, noise_level: float):
        """
        Setter generowanego przez układ chłodzenia poziomu hałasu
        :param noise_level: poziom hałasu [dBA]
        """
        self.noise_level = noise_level


class AirCooler(Cooler):
    """
    Klasa będąca reprezentacją tabeli ``air_cooler`` w bazie danych
    """
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
        """
        Konstruktor klasy ``AirCooler``
        :param name: nazwa układu chłodzenia procesora
        :param producer: producent układu chłodzenia procesora
        :param category: kod kategorii (``AIR_COOLER`` lub ``LIQUID_COOLER``)
        :param description: opis układu chłodzenia procesora w kodzie HTML
        :param price: cena układu chłodzenia procesora
        :param producer_code: kod producenta układu chłodzenia procesora
        :param ean: kod EAN (European Article Number)
        :param socket_compatibility: lista gniazd procesora, z którymi kompatybilny jest układ chłodzenie
        :param lightning: informacja, czy układ chłodzenia ma podświetlenie
        :param num_of_fans: liczba wiatraków
        :param fan_diameter: średnica wiatraków
        :param fan_speed: prędkość obrotowa wiatraków [obr./min]
        :param noise_level: poziom głośności generowany przez układ chłodzenia
        :param vertical_installation: informacja, czy układ chłodzenia jest montowany w sposób wertykalny (pionowy)
        :param height: wysokość układu chłodzenia [mm]
        :param width: szerokość układu chłodzenia [mm]
        :param depth: głębokość układu chłodzenia [mm]
        :param base_material: materiał podstawy
        :param num_of_heat_pipes: liczba ciepłowodów
        :param heat_pipe_diameter: średnica ciepłowodów [mm]
        """
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
        """
        Metoda wypisująca specyfikacje dla układów chłodzeń powietrzem do procesora
        """
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
        """
        Getter informacji, czy układ chłodzenia jest montowany w sposób wertykalny (pionowy)
        :return: informacja, czy układ chłodzenia jest montowany w sposób wertykalny (``True`` lub ``False``)
        """
        return self.vertical_installation

    def set_vertical_installation(self, vertical_installation):
        """
        Setter informacji, czy układ chłodzenia jest montowany w sposób wertykalny (pionowy)
        :param vertical_installation: ``True`` jeśli układ chłodzenia jest montowany w sposób wertykalny, ``False`` w przeciwnym razie
        """
        self.vertical_installation = vertical_installation

    def get_height(self):
        """
        Getter wysokości układu chłodzenia
        :return: wysokość układu chłodzenia [mm]
        """
        return self.height

    def set_height(self, height: bool):
        """
        Setter wysokości układu chłodzenia
        :param height: wysokość układu chłodzenia [mm]
        """
        self.height = height

    def get_width(self):
        """
        Getter szerokości układu chłodzenia
        :return: szerokość układu chłodzenia [mm]
        """
        return self.width

    def set_width(self, width: int):
        """
        Setter szerokości układu chłodzenia
        :param width: szerokość układu chłodzenia [mm]
        """
        self.width = width

    def get_depth(self):
        """
        Getter głębokości układu chłodzenia
        :return: głębokość układu chłodzenia [mm]
        """
        return self.depth

    def set_depth(self, depth: int):
        """
        Setter głębokości układu chłodzenia
        :param depth: głębokość układu chłodzenia [mm]
        """
        self.depth = depth

    def get_base_material(self):
        """
        Getter materiału podstawy
        :return: materiał podstawy
        """
        return self.base_material

    def set_base_material(self, base_material: int):
        """
        Setter materiału podstawy
        :param base_material: materiał podstawy
        """
        self.base_material = base_material

    def get_num_of_heat_pipes(self):
        """
        Getter liczby ciepłowodów
        :return: liczba ciepłowodów
        """
        return self.num_of_heat_pipes

    def set_num_of_heat_pipes(self, num_of_heat_pipes: str):
        """
        Setter liczby ciepłowodów
        :param num_of_heat_pipes: liczba ciepłowodów
        """
        self.num_of_heat_pipes = num_of_heat_pipes

    def get_heat_pipe_diameter(self):
        """
        Getter średnicy ciepłowodów
        :return: średnica ciepłowodów [mm]
        """
        return self.heat_pipe_diameter

    def set_heat_pipe_diameter(self, heat_pipe_diameter: int):
        """
        Setter średnicy ciepłowodów
        :param heat_pipe_diameter: średnica ciepłowodów [mm]
        """
        self.heat_pipe_diameter = heat_pipe_diameter


class LiquidCooler(Cooler):
    """
    Klasa będąca reprezentacją tabeli ``liquid_cooler`` w bazie danych
    """
    __tablename__ = "liquid_cooler"
    ean = Column("ean", ForeignKey("cooler.ean", ondelete="CASCADE", onupdate="CASCADE"), primary_key=True,
                 nullable=False)
    cooler_size = Column("cooler_size", sqlalchemy.Integer, nullable=False)

    def __init__(self, name: str, producer: str, category: str, description: str, price: float, producer_code: str,
                 ean: int,
                 socket_compatibility: list[str], lightning: bool, num_of_fans: int, fan_diameter: int, fan_speed: int,
                 noise_level: float, cooler_size: int):
        """

        :param name: nazwa układu chłodzenia procesora
        :param producer: producent układu chłodzenia procesora
        :param category: kod kategorii (``LIQUID_COOLER``)
        :param description: opis układu chłodzenia procesora w kodzie HTML
        :param price: cena układu chłodzenia procesora
        :param producer_code: kod producenta układu chłodzenia procesora
        :param ean: kod EAN (European Article Number)
        :param socket_compatibility: lista gniazd procesora, z którymi kompatybilny jest układ chłodzenie
        :param lightning: informacja, czy układ chłodzenia ma podświetlenie
        :param num_of_fans: liczba wiatraków
        :param fan_diameter: średnica wiatraków
        :param fan_speed: prędkość obrotowa wiatraków [obr./min]
        :param noise_level: poziom głośności generowany przez układ chłodzenia
        :param cooler_size: rozmiar chłodnicy [mm]
        """
        super().__init__(name, producer, category, description, price, producer_code, ean, socket_compatibility,
                         lightning,
                         num_of_fans, fan_diameter, fan_speed, noise_level)
        self.cooler_size: int = cooler_size

    def print_product_specs(self):
        """
        Metoda wypisująca specyfikacje dla układów chłodzeń cieczą do procesora
        """
        super().print_product_specs()
        print("Cooler size:", self.cooler_size, "mm")
        super().print_end()

    def get_cooler_size(self):
        """
        Getter rozmiaru chłodnicy
        :return: rozmiar chłodnicy [mm]
        """
        return self.cooler_size

    def set_cooler_size(self, cooler_size: int):
        """
        Setter rozmiaru chłodnicy
        :param cooler_size: rozmiar chłodnicy [mm]
        """
        self.cooler_size = cooler_size
