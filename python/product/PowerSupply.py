import sqlalchemy
from sqlalchemy import ForeignKey, Column
from sqlalchemy.dialects.postgresql import JSONB

from product.Product import Product


class PowerSupply(Product):
    """
    Klasa będąca reprezentacją tabeli ``power_supply`` w bazie danych
    """
    __tablename__ = "power_supply"
    ean = Column("ean", ForeignKey("product.ean", ondelete="CASCADE", onupdate="CASCADE"), primary_key=True,
                 nullable=False)
    standard = Column("standard", sqlalchemy.VARCHAR(255), nullable=False)
    power = Column("power", sqlalchemy.Integer, nullable=False)
    efficiency_certificate = Column("efficiency_certificate", sqlalchemy.VARCHAR(255), nullable=False)
    efficiency = Column("efficiency", sqlalchemy.Integer, nullable=False)
    cooling_type = Column("cooling_type", sqlalchemy.VARCHAR(255), nullable=False)
    fan_diameter = Column("fan_diameter", sqlalchemy.Integer, nullable=False)
    modular_cabling = Column("modular_cabling", sqlalchemy.Boolean, nullable=False)
    atx24 = Column("atx24", sqlalchemy.Integer, nullable=False)
    pcie16 = Column("pcie16", sqlalchemy.Integer, nullable=False)
    pcie8 = Column("pcie8", sqlalchemy.Integer, nullable=False)
    pcie6 = Column("pcie6", sqlalchemy.Integer, nullable=False)
    cpu8 = Column("cpu8", sqlalchemy.Integer, nullable=False)
    cpu4 = Column("cpu4", sqlalchemy.Integer, nullable=False)
    sata = Column("sata", sqlalchemy.Integer, nullable=False)
    molex = Column("molex", sqlalchemy.Integer, nullable=False)
    height = Column("height", sqlalchemy.Integer, nullable=False)
    width = Column("width", sqlalchemy.Integer, nullable=False)
    depth = Column("depth", sqlalchemy.Integer, nullable=False)
    lightning = Column("lightning", sqlalchemy.Boolean, nullable=False)
    protections = Column("protections", sqlalchemy.ARRAY(JSONB), nullable=False)

    def __init__(self, name: str, producer: str, category: str, description: str, price: float, producer_code: str,
                 ean: int, standard: str, power: int, efficiency_certificate: str, efficiency: int, cooling_type: str,
                 fan_diameter: int, protections: list[str], modular_cabling: bool,
                 atx24: int, pcie16: int, pcie8: int, pcie6: int, cpu8: int, cpu4: int,
                 sata: int, molex: int, height: int, width: int, depth: int, lightning: bool):
        """
        Konstruktor klasy ``PowerSupply``
        :param name: nazwa zasilacza 
        :param producer: producent zasilacza 
        :param category: kategoria (``PS``) 
        :param description: opis zasilacza 
        :param price: cena zasilacza 
        :param producer_code: kod producenta 
        :param ean: kod EAN (European Article Number) 
        :param standard: standard zasilacza
        :param power: moc zasilacza [W]
        :param efficiency_certificate: certyfikat sprawności
        :param efficiency: sprawność zasilacza
        :param cooling_type: typ chłodzenia zasilacza
        :param fan_diameter: średnica wiatraka/wiatraków [mm]
        :param protections: lista zabezpieczeń zasilacza
        :param modular_cabling: informacja, czy zasilacz ma modularne okablowanie
        :param atx24: liczba złącz ATX 24-pin (20+4)
        :param pcie16: liczba złącz PCI-E 16-pin (12+4)
        :param pcie8: liczba złącz PCI-E 8-pin (6+2) oraz PCI-E 8-pin
        :param pcie6: liczba złącz PCI-E 6-pin
        :param cpu8: liczba złącz CPU 8-pin (4+4) oraz CPU 8-pin
        :param cpu4: liczba złącz CPU 4-pin
        :param sata: liczba złącz SATA
        :param molex: liczba złącz Molex
        :param height: wysokość [mm]
        :param width: szerokość [mm]
        :param depth: głębokość [mm]
        :param lightning: informacja, czy zasilacz ma podświetlenie
        """
        super().__init__(name, producer, category, description, price, producer_code, ean)
        self.standard: str = standard
        self.power: int = power
        self.efficiency_certificate: str = efficiency_certificate
        self.efficiency: int = efficiency
        self.cooling_type: str = cooling_type
        self.fan_diameter: int = fan_diameter
        self.protections: list[str] = protections
        self.modular_cabling: bool = modular_cabling
        self.atx24: int = atx24
        self.pcie16: int = pcie16
        self.pcie8: int = pcie8
        self.pcie6: int = pcie6
        self.cpu8: int = cpu8
        self.cpu4: int = cpu4
        self.sata: int = sata
        self.molex: int = molex
        self.height: int = height
        self.width: int = width
        self.depth: int = depth
        self.lightning: bool = lightning

    def print_product_specs(self):
        """
        Metoda wypisująca specyfikację zasilaczy komputerowych
        :return: 
        """
        super().print_product_specs()
        print("Standard:", self.standard)
        print("Power:", self.power)
        print("Efficiency certificate:", self.efficiency_certificate)
        if self.efficiency > 0:
            print("Efficiency:", self.efficiency, "%")
        else:
            print("Efficiency: No data")
        print("Cooling type:", self.cooling_type)
        print("Fan diameter:", self.fan_diameter, "mm")
        print("Protections:", self.protections)
        print("Modular cabling:", self.modular_cabling)
        print("ATX 24pin:", self.atx24)
        print("PCI-E 16pin:", self.pcie16)
        print("PCI-E 8pin:", self.pcie8)
        print("PCI-E 6pin:", self.pcie6)
        print("CPU 8pin:", self.cpu8)
        print("CPU 4pin:", self.cpu4)
        print("SATA:", self.sata)
        print("Molex:", self.molex)
        print("Height:", self.height, "mm")
        print("Width:", self.width, "mm")
        print("Depth:", self.depth, "mm")
        print("Lightning:", self.lightning)
        super().print_end()

    def get_standard(self):
        """
        Getter standardu zasilacza
        :return: standard zasilacza 
        """
        return self.standard

    def set_standard(self, standard: str):
        """
        Setter standardu zasilacza
        :param standard: standard zasilacza 
        """
        self.standard = standard

    def get_power(self):
        """
        Getter mocy zasilacza
        :return: moc zasilacza [W] 
        """
        return self.power

    def set_power(self, power: int):
        """
        Setter mocy zasilacza
        :param power: moc zasilacza [W] 
        """
        self.power = power

    def get_efficiency_certificate(self):
        """
        Getter certyfikatu sprawności
        :return: certyfikat sprawności 
        """
        return self.efficiency_certificate

    def set_efficiency_certificate(self, efficiency_certificate: str):
        """
        Setter certyfikatu sprawności
        :param efficiency_certificate: certyfikat sprawności 
        """
        self.efficiency_certificate = efficiency_certificate

    def get_efficiency(self):
        """
        Getter sprawności zasilacza
        :return: sprawność zasilacza
        """
        return self.efficiency

    def set_efficiency(self, efficiency: int):
        """
        Setter sprawności zasilacza
        :param efficiency: sprawność zasilacza 
        """
        self.efficiency = efficiency

    def get_cooling_type(self):
        """
        Getter typu chłodzenia zasilacza
        :return: typ chłodzenia zasilacza 
        """
        return self.cooling_type

    def set_cooling_type(self, cooling_type: str):
        """
        Setter typu chłodzenia zasilacza
        :param cooling_type: typ chłodzenia zasilacza 
        """
        self.cooling_type = cooling_type

    def get_fan_diameter(self):
        """
        Getter średnicy wiatraka/wiatraków
        :return: średnica wiatraka/wiatraków [mm]
        """
        return self.fan_diameter

    def set_fan_diameter(self, fan_diameter: int):
        """
        Setter średnicy wiatraka/wiatraków
        :param fan_diameter: średnica wiatraka/wiatraków [mm]
        """
        self.fan_diameter = fan_diameter

    def get_protections(self):
        """
        Getter listy zabezpieczeń zasilacza
        :return: lista zabezpieczeń zasilacza 
        """
        return self.protections

    def set_protections(self, protections: list[str]):
        """
        Setter listy zabezpieczeń zasilacza
        :param protections: lista zabezpieczeń zasilacza 
        """
        self.protections = protections

    def get_modular_cabling(self):
        """
        Getter informacji czy zasilacz ma modularne okablowanie 
        :return: ``True`` jeśli zasilacz ma modularne okablowanie, ``False`` w przeciwnym razie 
        """
        return self.modular_cabling

    def set_modular_cabling(self, modular_cabling: bool):
        """
        Setter informacji czy zasilacz ma modularne okablowanie 
        :param modular_cabling: informacja, czy zasilacz ma modularne okablowanie (``True`` lub ``False``) 
        """
        self.modular_cabling = modular_cabling

    def get_atx24(self):
        """
        Getter liczby złącz ATX 24-pin
        :return: liczba złącz ATX 24-pin (20+4) 
        """
        return self.atx24

    def set_atx24(self, atx24: int):
        """
        Setter liczby złączy ATX 24-pin
        :param atx24: liczba złącz ATX 24-pin (20+4) 
        """
        self.atx24 = atx24

    def get_pcie16(self):
        """
        Getter liczby złączy 
        :return: liczba złącz PCI-E 16-pin (12+4) 
        """
        return self.pcie16

    def set_pcie16(self, pcie16: int):
        """
        Setter liczby złącz PCI-E 16-pin (12+4)
        :param pcie16: liczba złącz PCI-E 16-pin (12+4) 
        """
        self.pcie16 = pcie16

    def get_pcie8(self):
        """
        Getter liczby złącz E 8-pin (6+2) oraz PCI-E 8-pin
        :return: liczba złącz PCI-E 8-pin (6+2) oraz PCI-E 8-pin 
        """
        return self.pcie8

    def set_pcie8(self, pcie8: int):
        """
        Setter liczby złącz PCI-E 8-pin (6+2) oraz PCI-E 8-pin
        :param pcie8: liczba złącz PCI-E 8-pin (6+2) oraz PCI-E 8-pin 
        """
        self.pcie8 = pcie8

    def get_pcie6(self):
        """
        Getter liczby złącz E 6-pin
        :return: liczba złącz PCI-E 6-pin 
        """
        return self.pcie6

    def set_pcie6(self, pcie6: int):
        """
        Setter liczby złącz PCI-E 6-pin
        :param pcie6: liczba złącz PCI-E 6-pin 
        """
        self.pcie6 = pcie6

    def get_cpu8(self):
        """
        Getter liczby złącz 8-pin (4+4) oraz CPU 8-pin
        :return: liczba złącz CPU 8-pin (4+4) oraz CPU 8-pin 
        """
        return self.cpu8

    def set_cpu8(self, cpu8: int):
        """
        Setter liczby złącz CPU 8-pin (4+4) oraz CPU 8-pin
        :param cpu8: liczba złącz CPU 8-pin (4+4) oraz CPU 8-pin 
        """
        self.cpu8 = cpu8

    def get_cpu4(self):
        """
        Getter liczby złącz 4-pin
        :return: liczba złącz CPU 4-pin 
        """
        return self.cpu4

    def set_cpu4(self, cpu4: int):
        """
        Setter liczby złącz CPU 4-pin
        :param cpu4: liczba złącz CPU 4-pin 
        """
        self.cpu4 = cpu4

    def get_sata(self):
        """
        Getter liczby złącz
        :return: liczba złącz SATA 
        """
        return self.sata

    def set_sata(self, sata: int):
        """
        Setter liczby złącz SATA
        :param sata: liczba złącz SATA 
        """
        self.sata = sata

    def get_molex(self):
        """
        Getter liczby złącz Molex
        :return: liczba złącz Molex 
        """
        return self.molex

    def set_molex(self, molex: int):
        """
        Setter liczby złącz Molex
        :param molex: liczba złącz Molex 
        """
        self.molex = molex

    def get_height(self):
        """
        Getter wysokości zasilacza
        :return: wysokość [mm] 
        """
        return self.height

    def set_height(self, height: int):
        """
        Setter wysokości zasilacza
        :param height: wysokość [mm] 
        """
        self.height = height

    def get_width(self):
        """
        Getter szerokości zasilacza
        :return: szerokość [mm] 
        """
        return self.width

    def set_width(self, width: int):
        """
        Setter szerokości zasilacza
        :param width: szerokość [mm] 
        """
        self.width = width

    def get_depth(self):
        """
        Getter głębokości zasilacza
        :return: głębokość [mm] 
        """
        return self.depth

    def set_depth(self, depth: int):
        """
        Setter głębokości
        :param depth: głębokość [mm] 
        """
        self.depth = depth

    def get_lightning(self):
        """
        Getter informacji czy zasilacz ma podświetlenie
        :return: ``True`` jeśli zasilacz ma podświetlenie, ``False`` w przeciwnym razie 
        """
        return self.lightning

    def set_lightning(self, lightning: bool):
        """
        Setter informacji czy zasilacz ma podświetlenie
        :param lightning: informacja, czy zasilacz ma podświetlenie (``True`` lub ``False``) 
        """
        self.lightning = lightning
