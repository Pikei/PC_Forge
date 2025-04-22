import sqlalchemy
from sqlalchemy import Column, ForeignKey, Integer, Boolean, Float

from product.Product import Product, Base


class CaseMotherboardCompatibility(Base):
    """
    Klasa będąca reprezentacją tabeli ``case_mb_compatibility`` w bazie danych,
    będąca relacją wiele do wielu. Zawiera informacje o kompatybilności obudów ze standardami płyt głównych.
    """
    __tablename__ = "case_mb_compatibility"
    compatibility_id = Column("compatibility_id", Integer, primary_key=True, autoincrement=True)
    standard_id = Column("standard_id",
                         ForeignKey("motherboard_standard.standard_id", ondelete="CASCADE", onupdate="CASCADE"),
                         primary_key=True, nullable=False)
    ean = Column("ean", ForeignKey("product.ean", ondelete="CASCADE", onupdate="CASCADE"), primary_key=True,
                 nullable=False)


class Case(Product):
    """
    Klasa będąca reprezentacją tabeli ``pc_case`` w bazie danych.
    """
    __tablename__ = "pc_case"
    ean = Column("ean", ForeignKey("product.ean", ondelete="CASCADE", onupdate="CASCADE"), primary_key=True,
                 nullable=False)
    color = Column("color", sqlalchemy.VARCHAR(255), nullable=False)
    lightning = Column("lightning", Boolean, nullable=False)
    height = Column("height", Float, nullable=False)
    width = Column("width", Float, nullable=False)
    depth = Column("depth", Float, nullable=False)
    weight = Column("weight", Float, nullable=False)
    case_type = Column("case_type", sqlalchemy.VARCHAR(255), nullable=False)
    window = Column("has_window", Boolean, nullable=False)
    card_reader = Column("card_reader", Boolean, nullable=False)
    microphone_connector = Column("microphone_connector", Boolean, nullable=False)
    headphones_connector = Column("headphones_connector", Boolean, nullable=False)
    num_of_internal_25_bays = Column("internal_25_bays", Integer, nullable=False)
    num_of_internal_35_bays = Column("internal_35_bays", Integer, nullable=False)
    num_of_external_35_bays = Column("external_35_bays", Integer, nullable=False)
    num_of_external_525_bays = Column("external_525_bays", Integer, nullable=False)
    num_of_extension_slot = Column("extension_slots", Integer, nullable=False)
    max_gpu_length = Column("max_gpu_length", Float, nullable=False)
    max_cpu_cooler_height = Column("max_cpu_cooler_height", Float, nullable=False)
    usb20 = Column("usb_20", Integer, nullable=False)
    usb30 = Column("usb_30", Integer, nullable=False)
    usb31 = Column("usb_31", Integer, nullable=False)
    usb32 = Column("usb_32", Integer, nullable=False)
    usbc = Column("usb_c", Integer, nullable=False)
    front_fans = Column("front_fans", sqlalchemy.VARCHAR(255), nullable=False)
    back_fans = Column("back_fans", sqlalchemy.VARCHAR(255), nullable=False)
    side_fans = Column("side_fans", sqlalchemy.VARCHAR(255), nullable=False)
    bottom_fans = Column("bottom_fans", sqlalchemy.VARCHAR(255), nullable=False)
    top_fans = Column("top_fans", sqlalchemy.VARCHAR(255), nullable=False)
    power_supply = Column("power_supply", Boolean, nullable=False)
    ps_power = Column("ps_power", Integer, nullable=False)

    def __init__(self, name: str, producer: str, category: str, description: str, price: float, producer_code: str,
                 ean: int,
                 color: str, lightning: bool, height: float, width: float, depth: float, weight: float, case_type: str,
                 mb_compatibility: list[str], window: bool, max_gpu_length: float, max_cpu_cooler_height: float,
                 usb20: int, usb30: int, usb31: int, usb32: int, usbc: int, card_reader: bool,
                 headphones_connector: bool, microphone_connector: bool,
                 num_of_internal_25_bays: int, num_of_internal_35_bays: int,
                 num_of_external_35_bays: int, num_of_external_525_bays: int,
                 num_of_extension_slot: int, front_fans: str, back_fans: str, side_fans: str, bottom_fans: str,
                 top_fans: str,
                 power_supply: bool, ps_power: int):
        """
        Konstruktor klasy ``Case``, przechowującej informacje o obudowach komputerowych
        :param name: nazwa obudowy
        :param producer: producent obudowy
        :param category: kategoria (``CASE``)
        :param description: opis obudowy
        :param price: cena obudowy
        :param producer_code: kod producenta
        :param ean: kod EAN (European Article Number)
        :param color: kolor dominujący obudowy
        :param lightning: informacja, czy obudowa posiada oświetlenie
        :param height: wysokość obudowy [mm]
        :param width: szerokość obudowy [mm]
        :param depth: głębokość obudowy [mm]
        :param weight: waga obudowy [kg]
        :param case_type: typ obudowy
        :param mb_compatibility: lista kompatybilnych standardów płyt głównych
        :param window: informacja, czy obudowa posiada okno
        :param max_gpu_length: maksymalna długość karty graficznej [mm]
        :param max_cpu_cooler_height: maksymalna wysokość chłodzenia procesora [mm]
        :param usb20: liczba portów USB 2.0
        :param usb30: liczba portów USB 3.0
        :param usb31: liczba portów USB 3.1
        :param usb32: liczba portów USB 3.2
        :param usbc: liczba portów USB-C
        :param card_reader: informacja, czy obudowa posiada wbudowany czytnik kart
        :param headphones_connector: informacja, czy obudowa posiada wbudowane gniazdo słuchawkowe/głośnikowe
        :param microphone_connector: informacja, czy obudowa posiada wbudowane gniazdo mikrofonowe
        :param num_of_internal_25_bays: liczba wewnętrznych wnęk 2.5''
        :param num_of_internal_35_bays: liczba wewnętrznych wnęk 3.5''
        :param num_of_external_35_bays: liczba zewnętrznych wnęk 3.5''
        :param num_of_external_525_bays: liczba zewnętrznych wnęk 5.25''
        :param num_of_extension_slot: liczba slotów rozszerzeń
        :param front_fans: wiatraki na panelu przednim
        :param back_fans: wiatraki na panelu tylnym
        :param side_fans: wiatraki na panelu bocznym
        :param bottom_fans: wiatraki na panelu dolnym
        :param top_fans: wiatraki na panelu górnym
        :param power_supply: informacja, czy obudowa posiada w zestawie zasilacz
        :param ps_power: moc zasilacza dołączonego do obudowy
        """
        super().__init__(name, producer, category, description, price, producer_code, ean)
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
        """
        Wypisuje specyfikację obudowy
        """
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
        """
        Getter koloru obudowy
        :return: kolor dominujący obudowy
        """
        return self.color

    def set_color(self, color: str):
        """
        Setter koloru obudowy
        :param color: kolor dominujący obudowy
        """
        self.color = color

    def get_lightning(self):
        """
        Getter oświetlenia obudowy
        :return: True, jeśli obudowa posiada oświetlenie, False w przeciwnym razie
        """
        return self.lightning

    def set_lightning(self, lightning: bool):
        """
        Setter oświetlenia obudowy
        :param lightning: informacja, czy obudowa posiada oświetlenie (``True`` lub ``False``)
        """
        self.lightning = lightning

    def get_height(self):
        """
        Getter wysokości obudowy
        :return: wysokość obudowy [mm]
        """
        return self.height

    def set_height(self, height: float):
        """
        Setter wysokości obudowy
        :param height: wysokość obudowy [mm]
        """
        self.height = height

    def get_width(self):
        """
        Getter szerokości obudowy
        :return: szerokość obudowy [mm]
        """
        return self.width

    def set_width(self, width: float):
        """
        Setter szerokości obudowy
        :param width: szerokość obudowy [mm]
        """
        self.width = width

    def get_depth(self):
        """
        Getter głębokości obudowy
        :return: głębokość obudowy [mm]
        """
        return self.depth

    def set_depth(self, depth: float):
        """
        Setter głębokości obudowy
        :param depth: głębokość obudowy [mm]
        """
        self.depth = depth

    def get_weight(self):
        """
        Getter wagi obudowy
        :return: waga obudowy [kg]
        """
        return self.weight

    def set_weight(self, weight: float):
        """
        Setter wagi obudowy
        :param weight: waga obudowy [kg]
        """
        self.weight = weight

    def get_case_type(self):
        """
        Getter typu obudowy
        :return: typ obudowy
        """
        return self.case_type

    def set_case_type(self, case_type: str):
        """
        Setter typu obudowy
        :param case_type: typ obudowy
        """
        self.case_type = case_type

    def get_mb_compatibility(self):
        """
        Getter listy kompatybilnych standardów płyt głównych
        :return: lista kompatybilnych standardów płyt głównych
        """
        return self.mb_compatibility

    def set_mb_compatibility(self, mb_compatibility: list[str]):
        """
        Setter listy kompatybilnych standardów płyt głównych
        :param mb_compatibility: lista kompatybilnych standardów płyt głównych
        """
        self.mb_compatibility = mb_compatibility

    def get_window(self):
        """
        Getter informacji, czy obudowa ma okno
        :return: ``True`` jeśli obudowa posiada okno, ``False`` w przeciwnym razie
        """
        return self.window

    def set_window(self, window: bool):
        """
        Setter informacji, czy obudowa ma okno
        :param window: informacja, czy obudowa posiada okno (``True`` lub ``False``)
        """
        self.window = window

    def get_max_gpu_length(self):
        """
        Getter maksymalnej długości karty graficznej
        :return: maksymalna długość karty graficznej [mm]
        """
        return self.max_gpu_length

    def set_max_gpu_length(self, max_gpu_length: float):
        """
        Setter maksymalnej długości karty graficznej
        :param max_gpu_length: maksymalna długość karty graficznej [mm]
        """
        self.max_gpu_length = max_gpu_length

    def get_max_cpu_cooler_height(self):
        """
        Getter maksymalnej wysokości chłodzenia procesora
        :return: maksymalna wysokość chłodzenia procesora [mm]
        """
        return self.max_cpu_cooler_height

    def set_max_cpu_cooler_height(self, max_cpu_cooler_height: float):
        """
        Setter maksymalnej wysokości chłodzenia procesora
        :param max_cpu_cooler_height: maksymalna wysokość chłodzenia procesora [mm]
        """
        self.max_cpu_cooler_height = max_cpu_cooler_height

    def get_usb20(self):
        """
        Getter liczby portów USB 2.0
        :return: liczba portów USB 2.0
        """
        return self.usb20

    def set_usb20(self, usb20: int):
        """
        Setter liczby portów USB 2.0
        :param usb20: liczba portów USB 2.0
        """
        self.usb20 = usb20

    def get_usb30(self):
        """
        Getter liczby portów USB 3.0
        :return: liczba portów USB 3.0
        """
        return self.usb30

    def set_usb30(self, usb30: int):
        """
        Setter liczby portów USB 3.0
        :param usb30: liczba portów USB 3.0
        """
        self.usb30 = usb30

    def get_usb31(self):
        """
        Getter liczby portów USB 3.1
        :return: liczba portów USB 3.1
        """
        return self.usb31

    def set_usb31(self, usb31: int):
        """
        Setter liczby portów USB 3.1
        :param usb31: liczba portów USB 3.1
        """
        self.usb31 = usb31

    def get_usb32(self):
        """
        Getter liczby portów USB 3.2
        :return: liczba portów USB 3.2
        """
        return self.usb32

    def set_usb32(self, usb32: int):
        """
        Setter liczby portów USB 3.2
        :param usb32: liczba portów USB 3.2
        """
        self.usb32 = usb32

    def get_usbc(self):
        """
        Getter liczby portów USB-C
        :return: liczba portów USB-C
        """
        return self.usbc

    def set_usbc(self, usbc: int):
        """
        Setter liczby portów USB-C
        :param usbc: liczba portów USB-C
        """
        self.usbc = usbc

    def get_card_reader(self):
        """
        Getter informacji czy obudowa posiada wbudowany czytnik kart
        :return: ``True`` jeśli obudowa posiada wbudowany czytnik kart, ``False`` w przeciwnym razie
        """
        return self.card_reader

    def set_card_reader(self, card_reader: bool):
        """"
        Setter informacji, czy obudowa ma wbudowany czytnik kart
        :param card_reader: informacja, czy obudowa posiada wbudowany czytnik kart (``True`` lub ``False``)
        """
        self.card_reader = card_reader

    def get_headphones_connector(self):
        """
        Getter informacji czy obudowa posiada wbudowane złącze słuchawkowe/głośnikowe
        :return: ``True`` jeśli obudowa posiada wbudowane złącze słuchawkowe/głośnikowe, ``False`` w przeciwnym razie
        """
        return self.headphones_connector

    def set_headphones_connector(self, headphones_connector: bool):
        """"
        Setter informacji, czy obudowa ma wbudowane złącze słuchawkowe/głośnikowe
        :param headphones_connector: informacja, czy obudowa posiada wbudowane złącze słuchawkowe/głośnikowe (``True`` lub ``False``)
        """
        self.headphones_connector = headphones_connector

    def get_microphone_connector(self):
        """
        Getter informacji czy obudowa posiada złącze mikrofonowe
        :return: ``True`` jeśli obudowa posiada złącze mikrofonowe, ``False`` w przeciwnym razie
        """
        return self.microphone_connector

    def set_microphone_connector(self, microphone_connector: bool):
        """"
        Setter informacji, czy obudowa ma złącze mikrofonowe
        :param microphone_connector: informacja, czy obudowa posiada złącze mikrofonowe (``True`` lub ``False``)
        """
        self.microphone_connector = microphone_connector

    def get_num_of_internal_25_bays(self):
        """
        Getter liczby wewnętrznych wnęk 2.5''
        :return: Liczba wewnętrznych wnęk 2.5''
        """
        return self.num_of_internal_25_bays

    def set_num_of_internal_25_bays(self, num_of_internal_25_bays: int):
        """
        Setter liczby wewnętrznych wnęk 2.5''
        :param num_of_internal_25_bays: liczba wewnętrznych wnęk 2.5''
        """
        self.num_of_internal_25_bays = num_of_internal_25_bays

    def get_num_of_internal_35_bays(self):
        """
        Getter liczby wewnętrznych wnęk 3.5''
        :return: Liczba wewnętrznych wnęk 3.5''
        """
        return self.num_of_internal_35_bays

    def set_num_of_internal_35_bays(self, num_of_internal_35_bays: int):
        """
        Setter liczby wewnętrznych wnęk 3.5''
        :param num_of_internal_35_bays: liczba wewnętrznych wnęk 3.5''
        """
        self.num_of_internal_35_bays = num_of_internal_35_bays

    def get_num_of_external_35_bays(self):
        """
        Getter liczby zewnętrznych wnęk 3.5''
        :return: Liczba zewnętrznych wnęk 3.5''
        """
        return self.num_of_external_35_bays

    def set_num_of_external_35_bays(self, num_of_external_35_bays: int):
        """
        Setter liczby zewnętrznych wnęk 3.5''
        :param num_of_external_35_bays: liczba zewnętrznych wnęk 3.5''
        """
        self.num_of_external_35_bays = num_of_external_35_bays

    def get_num_of_external_525_bays(self):
        """
        Getter liczby zewnętrznych wnęk 5.25''
        :return: Liczba zewnętrznych wnęk 5.25''
        """
        return self.num_of_external_525_bays

    def set_num_of_external_525_bays(self, num_of_external_525_bays: int):
        """
        Setter liczby zewnętrznych wnęk 5.25''
        :param num_of_external_525_bays: liczba zewnętrznych wnęk 5.25''
        """
        self.num_of_external_525_bays = num_of_external_525_bays

    def get_num_of_extension_slot(self):
        """
        Getter liczby slotów rozszerzeń
        :return: Liczba slotów rozszerzeń
        """
        return self.num_of_extension_slot

    def set_num_of_extension_slot(self, num_of_extension_slot: int):
        """
        Setter liczby slotów rozszerzeń
        :param num_of_extension_slot: Liczba slotów rozszerzeń
        """
        self.num_of_extension_slot = num_of_extension_slot

    def get_front_fans(self):
        """
        Getter informacji o wiatrakach na przednim panelu obudowy
        :return: Ciąg znaków zawierający informację o wiatrakach na przednim panelu obudowy
        """
        return self.front_fans

    def set_front_fans(self, front_fans: str):
        """
        Setter informacji o wiatrakach na przednim panelu obudowy
        :param front_fans: Ciąg znaków zawierający informację o wiatrakach na przednim panelu obudowy
        """
        self.front_fans = front_fans

    def get_back_fans(self):
        """
        Getter informacji o wiatrakach na tylnym panelu obudowy
        :return: Ciąg znaków zawierający informację o wiatrakach na tylnym panelu obudowy
        """
        return self.back_fans

    def set_back_fans(self, back_fans: str):
        """
        Setter informacji o wiatrakach na tylnym panelu obudowy
        :param back_fans: Ciąg znaków zawierający informację o wiatrakach na tylnym panelu obudowy
        """
        self.back_fans = back_fans

    def get_side_fans(self):
        """
        Getter informacji o wiatrakach na bocznym panelu obudowy
        :return: Ciąg znaków zawierający informację o wiatrakach na bocznym panelu obudowy
        """
        return self.side_fans

    def set_side_fans(self, side_fans: str):
        """
        Setter informacji o wiatrakach na bocznym panelu obudowy
        :param side_fans: Ciąg znaków zawierający informację o wiatrakach na bocznym panelu obudowy
        """
        self.side_fans = side_fans

    def get_bottom_fans(self):
        """
        Getter informacji o wiatrakach na dolnym panelu obudowy
        :return: Ciąg znaków zawierający informację o wiatrakach na dolnym panelu obudowy
        """
        return self.bottom_fans

    def set_bottom_fans(self, bottom_fans: str):
        """
        Setter informacji o wiatrakach na dolnym panelu obudowy
        :param bottom_fans: Ciąg znaków zawierający informację o wiatrakach na dolnym panelu obudowy
        """
        self.bottom_fans = bottom_fans

    def get_top_fans(self):
        """
        Getter informacji o wiatrakach na górnym panelu obudowy
        :return: Ciąg znaków zawierający informację o wiatrakach na górnym panelu obudowy
        """
        return self.top_fans

    def set_top_fans(self, top_fans: str):
        """
        Setter informacji o wiatrakach na górnym panelu obudowy
        :param top_fans: Ciąg znaków zawierający informację o wiatrakach na górnym panelu obudowy
        """
        self.top_fans = top_fans

    def get_power_supply(self):
        """
        Getter informacji, czy obudowa posiada załączony zasilacz
        :return: informacja, czy obudowa posiada załączony zasilacz (``True`` lub ``False``)
        """
        return self.power_supply

    def set_power_supply(self, power_supply: bool):
        """
        Setter informacji, czy obudowa posiada zasilacz
        :param power_supply: `True`` jeśli obudowa posiada załączony zasilacz, ``False`` w przeciwnym razie
        """
        self.power_supply = power_supply

    def get_ps_power(self):
        """
        Getter mocy załączonego zasilacza
        :return: moc załączonego zasilacza [W]
        """
        return self.ps_power

    def set_ps_power(self, ps_power: int):
        """
        Setter mocy załączonego zasilacza
        :param ps_power: moc załączonego zasilacza [W]
        """
        self.ps_power = ps_power
