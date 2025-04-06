import sqlalchemy
from sqlalchemy import Column, ForeignKey
from product.Product import Product, Base
from sqlalchemy.dialects.postgresql import JSONB


class MB_Standard(Base):
    """
    Klasa będąca reprezentacją tabeli ``motherboard_standard`` w bazie danych.
    Przechowuje informacje o dostępnych standardach płyt głównych.
    """
    __tablename__ = "motherboard_standard"
    id = Column("standard_id", sqlalchemy.Integer, primary_key=True, autoincrement=True)
    name = Column("standard_name", sqlalchemy.VARCHAR(255), nullable=False)

class Motherboard(Product):
    """
    Klasa będąca reprezentacją tabeli ``motherboard`` w bazie danych.
    """
    __tablename__ = "motherboard"
    ean = Column("ean", ForeignKey("product.ean", ondelete="CASCADE", onupdate="CASCADE"), primary_key=True,
                 nullable=False)
    standard_id = Column("standard_id", ForeignKey("motherboard_standard.standard_id"), nullable=False)
    chipset = Column("chipset", sqlalchemy.VARCHAR(255), nullable=False)
    memory_standard = Column("memory_standard", sqlalchemy.VARCHAR(255), nullable=False)
    number_of_memory_slots = Column("memory_slots", sqlalchemy.Integer, nullable=False)
    max_memory_capacity = Column("max_memory_capacity", sqlalchemy.Integer, nullable=False)
    integrated_audio_card = Column("integrated_audio_card", sqlalchemy.VARCHAR(255), nullable=False)
    audio_channels = Column("audio_channels", sqlalchemy.Float, nullable=False)
    integrated_network_card = Column("integrated_network_card", sqlalchemy.VARCHAR(255), nullable=False)
    bluetooth = Column("bluetooth", sqlalchemy.Boolean, nullable=False)
    wifi = Column("wifi", sqlalchemy.Boolean, nullable=False)
    width = Column("width", sqlalchemy.Float, nullable=False)
    depth = Column("depth", sqlalchemy.Float, nullable=False)
    socket_id = Column("socket_id", ForeignKey("cpu_socket.socket_id"), nullable=False)
    expansion_slots = Column("expansion_slots", sqlalchemy.ARRAY(JSONB), nullable=False)
    drive_interfaces = Column("drive_interfaces", sqlalchemy.ARRAY(JSONB), nullable=False)
    outside_connectors = Column("outside_connectors", sqlalchemy.ARRAY(JSONB), nullable=False)
    supported_memory_frequencies = Column("supported_memory_frequencies", sqlalchemy.ARRAY(JSONB), nullable=False)

    def __init__(self, name: str, producer: str, category: str, description: str, price: float, producer_code: str,
                 ean: int,
                 standard: str, chipset: str, cpu_socket: str, memory_standard: str, number_of_memory_slots: int,
                 supported_memory_frequencies: list[int], max_memory_capacity: int, integrated_audio_card: str,
                 audio_channels: float, integrated_network_card: str, bluetooth: bool, wifi: bool,
                 expansion_slots: list[str],
                 drive_interfaces: list[str], outside_connectors: list[str], width: float, depth: float):
        """
        Konstruktor klasy ``Motherboard``
        :param name: nazwa płyty głównej
        :param producer: producent płyty głównej
        :param category: kategoria (``MB``)
        :param description: opis płyty głównej
        :param price: cena płyty głównej
        :param producer_code: kod producenta
        :param ean: kod EAN (European Article Number)
        :param standard: standard płyty głównej
        :param chipset: chipset płyty głównej
        :param cpu_socket: obsługiwany przez płytę główną socket procesora
        :param memory_standard: obsługiwany przez płytę główną standard pamięci RAM
        :param number_of_memory_slots: liczba slotów pamięci RAM znajdującej się na płycie głównej
        :param supported_memory_frequencies: lista obsługiwanych przez płytę główną częstotliwości pamięci RAM [MHz]
        :param max_memory_capacity: maksymalna pojemność pamięci RAM [GB]
        :param integrated_audio_card: zintegrowana karta dźwiękowa
        :param audio_channels: kanały audio
        :param integrated_network_card: zintegrowana karta sieciowa
        :param bluetooth: informacja, czy płyta główna posiada moduł Bluetooth
        :param wifi: informacja, czy płyta główna posiada moduł Wi-Fi
        :param expansion_slots: liczba slotów rozszerzeń
        :param drive_interfaces: lista interfejsów dysków
        :param outside_connectors: lista złącz zewnętrznych płyty głównej
        :param width: szerokość płyty głównej
        :param depth: głębokość płyty głównej
        """
        super().__init__(name, producer, category, description, price, producer_code, ean)
        self.standard: str = standard
        self.chipset: str = chipset
        self.cpu_socket: str = cpu_socket
        self.memory_standard: str = memory_standard
        self.number_of_memory_slots: int = number_of_memory_slots
        self.supported_memory_frequencies: list[int] = supported_memory_frequencies
        self.max_memory_capacity: int = max_memory_capacity
        self.integrated_audio_card: str = integrated_audio_card
        self.audio_channels: float = audio_channels
        self.integrated_network_card: str = integrated_network_card
        self.bluetooth: bool = bluetooth
        self.wifi: bool = wifi
        self.expansion_slots: list[str] = expansion_slots
        self.drive_interfaces: list[str] = drive_interfaces
        self.outside_connectors: list[str] = outside_connectors
        self.width: float = width
        self.depth: float = depth
        self.standard_id = None
        self.socket_id = None

    def print_product_specs(self):
        super().print_product_specs()
        print("Standard:", self.standard)
        print("Chipset:", self.chipset)
        print("CPU socket:", self.cpu_socket)
        print("Memory standard:", self.memory_standard)
        print("Number of memory slots:", self.number_of_memory_slots)
        print("Supported memory frequencies [MHz]:", self.supported_memory_frequencies)
        print("Max memory capacity:", self.max_memory_capacity, "GB")
        print("Integrated audio card:", self.integrated_audio_card)
        print("Audio channels:", self.audio_channels)
        print("Integrated network card:", self.integrated_network_card)
        print("Bluetooth:", self.bluetooth)
        print("Wi-Fi:", self.wifi)
        print("Expansion slots:", self.expansion_slots)
        print("Drive interfaces:", self.drive_interfaces)
        print("Outside connectors:", self.outside_connectors)
        print("Width:", self.width, "mm")
        print("Depth:", self.depth, "mm")
        super().print_end()

    def get_standard(self):
        """
        Getter standardu płyty głównej
        :return: standard płyty głównej
        """
        return self.standard

    def set_standard(self, standard: str):
        """
        Setter standardu płyty głównej
        :param standard: standard płyty głównej
        """
        self.standard = standard

    def get_chipset(self):
        """
        Getter chipsetu płyty głównej
        :return: chipset płyty głównej
        """
        return self.chipset

    def set_chipset(self, chipset: str):
        """
        Setter chipsetu płyty głównej
        :param chipset: chipset płyty głównej
        """
        self.chipset = chipset

    def get_cpu_socket(self):
        """
        Getter gniazda procesora na płycie głównej
        :return: gniazdo procesora na płycie głównej
        """
        return self.cpu_socket

    def set_cpu_socket(self, cpu_socket: str):
        """
        Setter gniazda procesora na płycie głównej
        :param cpu_socket: gniazdo procesora na płycie głównej
        """
        self.cpu_socket = cpu_socket

    def get_memory_standard(self):
        """
        Getter obsługiwanej przez płytę główną standardu pamięci RAM
        :return: obsługiwany przez płytę główną standard pamięci RAM
        """
        return self.memory_standard

    def set_memory_standard(self, memory_standard: str):
        """
        Setter obsługiwanej przez płytę główną standardu pamięci RAM
        :param memory_standard: obsługiwany przez płytę główną standard pamięci RAM
        """
        self.memory_standard = memory_standard

    def get_number_of_memory_slots(self):
        """
        Getter liczby slotów pamięci RAM na płycie głównej
        :return: liczba slotów pamięci RAM na płycie głównej
        """
        return self.number_of_memory_slots

    def set_number_of_memory_slots(self, number_of_memory_slots: int):
        """
        Setter liczby slotów pamięci RAM na płycie głównej
        :param number_of_memory_slots: liczba slotów pamięci RAM na płycie głównej
        """
        self.number_of_memory_slots = number_of_memory_slots

    def get_supported_memory_frequencies(self):
        """
        Getter listy obsługiwanych przez płytę główną częstotliwości pamięci RAM
        :return: lista obsługiwanych przez płytę główną częstotliwości pamięci RAM [MHz]
        """
        return self.supported_memory_frequencies

    def set_supported_memory_frequencies(self, supported_memory_frequencies: list[int]):
        """
        Setter listy obsługiwanych przez płytę główną częstotliwości pamięci RAM
        :param supported_memory_frequencies: lista obsługiwanych przez płytę główną częstotliwości pamięci RAM [MHz]
        """
        self.supported_memory_frequencies = supported_memory_frequencies

    def get_max_memory_capacity(self):
        """
        Getter maksymalnej pojemność pamięci RAM
        :return: maksymalna pojemność pamięci RAM [GB]
        """
        return self.max_memory_capacity

    def set_max_memory_capacity(self, max_memory_capacity: int):
        """
        Setter maksymalnej pojemność pamięci RAM
        :param max_memory_capacity: maksymalna pojemność pamięci RAM [GB]
        """
        self.max_memory_capacity = max_memory_capacity

    def get_integrated_audio_card(self):
        """
        Getter zintegrowanej karty dźwiękowej
        :return: zintegrowana karta dźwiękowa
        """
        return self.integrated_audio_card

    def set_integrated_audio_card(self, integrated_audio_card: str):
        """
        Setter zintegrowanej karty dźwiękowej
        :param integrated_audio_card: zintegrowana karta dźwiękowa
        """
        self.integrated_audio_card = integrated_audio_card

    def get_audio_channels(self):
        """
        Getter kanałów audio
        :return: kanały audio
        """
        return self.audio_channels

    def set_audio_channels(self, audio_channels: float):
        """
        Setter kanałów audio
        :param audio_channels: kanały audio
        """
        self.audio_channels = audio_channels

    def get_integrated_network_card(self):
        """
        Getter zintegrowanej karty sieciowej
        :return: zintegrowana karta sieciowa
        """
        return self.integrated_network_card

    def set_integrated_network_card(self, integrated_network_card: str):
        """
        Setter zintegrowanej karty sieciowej
        :param integrated_network_card: zintegrowana karta sieciowa
        """
        self.integrated_network_card = integrated_network_card

    def get_bluetooth(self):
        """
        Getter informacji czy płyta główna posiada moduł Bluetooth
        :return: ``True`` jeśli płyta główna posiada moduł Bluetooth, ``False`` w przeciwnym razie
        """
        return self.bluetooth

    def set_bluetooth(self, bluetooth: bool):
        """"
        Setter informacji, czy płyta główna ma moduł Bluetooth
        :param bluetooth: informacja, czy płyta główna posiada moduł Bluetooth (``True`` lub ``False``)
        """
        self.bluetooth = bluetooth

    def get_wifi(self):
        """
        Getter informacji czy płyta główna posiada moduł Wi-Fi
        :return: ``True`` jeśli płyta główna posiada moduł Wi-Fi, ``False`` w przeciwnym razie
        """
        return self.wifi

    def set_wifi(self, wifi: bool):
        """"
        Setter informacji, czy płyta główna ma moduł Wi-Fi
        :param wifi: informacja, czy płyta główna posiada moduł Wi-Fi (``True`` lub ``False``)
        """
        self.wifi = wifi

    def get_expansion_slots(self):
        """
        Getter listy slotów rozszerzeń
        :return: lista slotów rozszerzeń
        """
        return self.expansion_slots

    def set_expansion_slots(self, expansion_slots: list[str]):
        """
        Setter listy slotów rozszerzeń
        :param expansion_slots: lista slotów rozszerzeń
        """
        self.expansion_slots = expansion_slots

    def get_drive_interfaces(self):
        """
        Getter listy interfejsów dysków
        :return: lista interfejsów dysków
        """
        return self.drive_interfaces

    def set_drive_interfaces(self, drive_interfaces: list[str]):
        """
        Setter listy interfejsów dysków
        :param drive_interfaces: lista interfejsów dysków
        """
        self.drive_interfaces = drive_interfaces

    def get_outside_connectors(self):
        """
        Getter listy złącz zewnętrznych płyty głównej
        :return: lista złącz zewnętrznych płyty głównej
        """
        return self.outside_connectors

    def set_outside_connectors(self, outside_connectors: list[str]):
        """
        Setter listy złącz zewnętrznych płyty głównej
        :param outside_connectors: lista złącz zewnętrznych płyty głównej
        """
        self.outside_connectors = outside_connectors

    def get_width(self):
        """
        Getter szerokości płyty głównej
        :return: szerokość płyty głównej [mm]
        """
        return self.width

    def set_width(self, width: float):
        """
        Setter szerokości płyty głównej
        :param width: szerokość płyty głównej [mm]
        """
        self.width = width

    def get_depth(self):
        """
        Getter głębokości płyty głównej
        :return: głębokość płyty głównej [mm]
        """
        return self.depth

    def set_depth(self, depth: float):
        """
        Setter głębokości płyty głównej
        :param depth: głębokość płyty głównej [mm]
        """
        self.depth = depth

    def get_standard_id(self):
        """
        Getter identyfikatora (klucza głównego) standardu płyty głównej
        :return: identyfikator (klucz główny) standardu płyty głównej
        """
        return self.standard_id

    def set_standard_id(self, standard_id: int):
        """
        Setter identyfikatora (klucza głównego) standardu płyty głównej
        :param standard_id: identyfikator (klucz główny) standardu płyty głównej
        """
        self.standard_id = standard_id

    def get_socket_id(self):
        """
        Getter identyfikatora (klucza głównego) gniazda procesora na płycie głównej
        :return: identyfikator (klucz główny) gniazda procesora na płycie głównej
        """
        return self.socket_id

    def set_socket_id(self, socket_id: int):
        """
        Setter identyfikatora (klucza głównego) gniazda procesora na płycie głównej
        :param socket_id: identyfikator (klucz główny) gniazda procesora na płycie głównej
        """
        self.socket_id = socket_id
