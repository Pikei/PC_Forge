from product.Product import Product


class PowerSupply(Product):
    def __init__(self, name: str, producer: str, category: str, description: str, price: float, producer_code: str,
                 standard: str, power: int, efficiency_certificate: str, efficiency: int, cooling_type: str,
                 fan_diameter: int, protections: list[str], modular_cabling: bool,
                 atx24: int, pcie16: int, pcie8: int, pcie6: int, cpu8: int, cpu4: int,
                 sata: int, molex: int, height: int, width: int, depth: int, lightning: bool):
        super().__init__(name, producer, category, description, price, producer_code)
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
        super().print_common()
        print("--- SPECS ---")
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
        return self.standard

    def set_standard(self, standard: str):
        self.standard = standard

    def get_power(self):
        return self.power

    def set_power(self, power: int):
        self.power = power

    def get_efficiency_certificate(self):
        return self.efficiency_certificate

    def set_efficiency_certificate(self, efficiency_certificate: str):
        self.efficiency_certificate = efficiency_certificate

    def get_efficiency(self):
        return self.efficiency

    def set_efficiency(self, efficiency: int):
        self.efficiency = efficiency

    def get_cooling_type(self):
        return self.cooling_type

    def set_cooling_type(self, cooling_type: str):
        self.cooling_type = cooling_type

    def get_fan_diameter(self):
        return self.fan_diameter

    def set_fan_diameter(self, fan_diameter: int):
        self.fan_diameter = fan_diameter

    def get_protections(self):
        return self.protections

    def set_protections(self, protections: list[str]):
        self.protections = protections

    def get_modular_cabling(self):
        return self.modular_cabling

    def set_modular_cabling(self, modular_cabling: bool):
        self.modular_cabling = modular_cabling

    def get_atx24(self):
        return self.atx24

    def set_atx24(self, atx24: int):
        self.atx24 = atx24

    def get_pcie16(self):
        return self.pcie16

    def set_pcie16(self, pcie16: int):
        self.pcie16 = pcie16

    def get_pcie8(self):
        return self.pcie8

    def set_pcie8(self, pcie8: int):
        self.pcie8 = pcie8

    def get_pcie6(self):
        return self.pcie6

    def set_pcie6(self, pcie6: int):
        self.pcie6 = pcie6

    def get_cpu8(self):
        return self.cpu8

    def set_cpu8(self, cpu8: int):
        self.cpu8 = cpu8

    def get_cpu4(self):
        return self.cpu4

    def set_cpu4(self, cpu4: int):
        self.cpu4 = cpu4

    def get_sata(self):
        return self.sata

    def set_sata(self, sata: int):
        self.sata = sata

    def get_molex(self):
        return self.molex

    def set_molex(self, molex: int):
        self.molex = molex

    def get_height(self):
        return self.height

    def set_height(self, height: int):
        self.height = height

    def get_width(self):
        return self.width

    def set_width(self, width: int):
        self.width = width

    def get_depth(self):
        return self.depth

    def set_depth(self, depth: int):
        self.depth = depth

    def get_lightning(self):
        return self.lightning

    def set_lightning(self, lightning: bool):
        self.lightning = lightning
