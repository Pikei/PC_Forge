import sqlalchemy
from sqlalchemy import Column, ForeignKey

from product.Product import Product


class GraphicsCard(Product):
    """
    Klasa będąca reprezentacją tabeli ``graphics_card`` w bazie danych
    """
    __tablename__ = "graphics_card"
    ean = Column("ean", ForeignKey("product.ean", ondelete="CASCADE", onupdate="CASCADE"), primary_key=True,
                 nullable=False)
    chipset_producer = Column("chipset_producer", sqlalchemy.VARCHAR(255), nullable=False)
    chipset = Column("chipset", sqlalchemy.VARCHAR(255), nullable=False)
    core_frequency = Column("core_frequency", sqlalchemy.Integer, nullable=False)
    max_core_frequency = Column("max_core_frequency", sqlalchemy.Integer, nullable=False)
    stream_processors = Column("stream_processors", sqlalchemy.Integer, nullable=False)
    rop_units = Column("rop_units", sqlalchemy.Integer, nullable=False)
    texturing_units = Column("texturing_units", sqlalchemy.Integer, nullable=False)
    rt_cores = Column("rt_cores", sqlalchemy.Integer, nullable=False)
    tensor_cores = Column("tensor_cores", sqlalchemy.Integer, nullable=False)
    dlss = Column("dlss", sqlalchemy.VARCHAR(255), nullable=False)
    connector = Column("connector", sqlalchemy.VARCHAR(255), nullable=False)
    card_length = Column("card_length", sqlalchemy.Integer, nullable=False)
    resolution = Column("resolution", sqlalchemy.VARCHAR(255), nullable=False)
    recommended_ps = Column("recommended_ps_power", sqlalchemy.Integer, nullable=False)
    lightning = Column("lightning", sqlalchemy.Boolean, nullable=False)
    ram = Column("ram", sqlalchemy.Integer, nullable=False)
    ram_type = Column("ram_type", sqlalchemy.VARCHAR(255), nullable=False)
    data_bus = Column("data_bus", sqlalchemy.Integer, nullable=False)
    memory_freq = Column("memory_frequency", sqlalchemy.Integer, nullable=False)
    cooling_type = Column("cooling_type", sqlalchemy.VARCHAR(255), nullable=False)
    number_of_fans = Column("number_of_fans", sqlalchemy.Integer, nullable=False)

    def __init__(self, name: str, producer: str, category: str, description: str, price: float, producer_code: str,
                 ean: int, chipset_producer: str, chipset: str, core_frequency: int, max_core_frequency: int,
                 stream_processors: int, rop_units: int, texturing_units: int, rt_cores: int, tensor_cores: int,
                 dlss: str, connector: str, card_length: int, resolution: str, recommended_ps: int, lightning: bool,
                 ram: int, ram_type: str, data_bus: int, memory_freq: int, cooling_type: str, number_of_fans: int):
        """
        Konstruktor klasy ``GraphicsCard``
        :param name: nazwa karty graficznej
        :param producer: producent karty graficznej
        :param category: kategoria (``GPU``)
        :param description: opis karty graficznej
        :param price: cena karty graficznej
        :param producer_code: kod producenta
        :param ean: kod EAN (European Article Number)
        :param chipset_producer: producent chipsetu
        :param chipset: chipset karty graficznej
        :param core_frequency: częstotliwość taktowania rdzenia [MHz]
        :param max_core_frequency: maksymalna częstotliwość taktowania rdzenia [MHz]
        :param stream_processors: liczba procesorów strumieniowych
        :param rop_units: liczba jednostek ROP (Render Out Pixel)
        :param texturing_units: liczba jednostek teksturujących
        :param rt_cores: liczba rdzeni RT
        :param tensor_cores: liczba rdzeni Tensor
        :param dlss: rodzaj DLSS
        :param connector: typ złącza karty graficznej do płyty głównej
        :param card_length: długość karty graficznej [mm]
        :param resolution: rozdzielczość generowanego obrazu
        :param recommended_ps: rekomendowana moc zasilacza [W]
        :param lightning: informacja, czy karta graficzna ma podświetlenie
        :param ram: liczba pamięci RAM (wartości dodatnie oznaczają jednostkę [GB], ujemne [MB])
        :param ram_type: typ pamięci RAM
        :param data_bus: Szyna danych
        :param memory_freq: taktowanie pamięci [MHz]
        :param cooling_type: typ chłodzenia
        :param number_of_fans: liczba wiatraków
        """
        super().__init__(name, producer, category, description, price, producer_code, ean)
        self.chipset_producer: str = chipset_producer
        self.chipset: str = chipset
        self.core_frequency: int = core_frequency
        self.max_core_frequency: int = max_core_frequency
        self.stream_processors: int = stream_processors
        self.rop_units: int = rop_units
        self.texturing_units: int = texturing_units
        self.rt_cores: int = rt_cores
        self.tensor_cores: int = tensor_cores
        self.dlss: str = dlss
        self.connector: str = connector
        self.card_length: int = card_length
        self.resolution: str = resolution
        self.recommended_ps: int = recommended_ps
        self.lightning: bool = lightning
        self.ram: int = ram
        self.ram_type: str = ram_type
        self.data_bus: int = data_bus
        self.memory_freq: int = memory_freq
        self.cooling_type: str = cooling_type
        self.number_of_fans: int = number_of_fans

    def print_product_specs(self):
        """
        Metoda wypisująca specyfikację karty graficznej
        """
        super().print_product_specs()
        print("Chipset producer:", self.chipset_producer)
        print("Chipset:", self.chipset)
        print("Core frequency:", self.core_frequency, "MHz")
        print("Max core frequency:", self.max_core_frequency, "MHz")
        print("Stream processors:", self.stream_processors)
        print("ROP (Render Out Pixel) units:", self.rop_units)
        print("Texturing units:", self.texturing_units)
        print("RT cores:", self.rt_cores)
        print("Tensor cores:", self.tensor_cores)
        print("DLSS:", self.dlss)
        print("Connector:", self.connector)
        print("Card length:", self.card_length, "mm")
        print("Resolution:", self.resolution)
        print("Recommended power of power supply:", self.recommended_ps, "W")
        print("Lightning:", self.lightning)
        if self.ram > 0:
            print("RAM :", self.ram, "GB")
        else:
            print("RAM :", ((-1) * self.ram), "MB")
        print("RAM type:", self.ram_type)
        print("Data bus:", self.data_bus, "bit")
        print("Memory frequency:", self.memory_freq, "MHz")
        print("Cooling type:", self.cooling_type)
        print("Number of fans:", self.number_of_fans)
        super().print_end()

    def get_chipset_producer(self):
        """
        Getter producenta chipsetu
        :return: producent chipsetu 
        """
        return self.chipset_producer

    def set_chipset_producer(self, chipset_producer: str):
        """
        Setter producenta chipsetu
        :param chipset_producer: chipset karty graficznej 
        """
        self.chipset_producer = chipset_producer

    def get_chipset(self):
        """
        Getter chipsetu karty graficznej
        :return: chipset karty graficznej 
        """
        return self.chipset

    def set_chipset(self, chipset: str):
        """
        Setter chipsetu karty graficznej
        :param chipset: częstotliwość taktowania rdzenia [MHz] 
        """
        self.chipset = chipset

    def get_core_frequency(self):
        """
        Getter częstotliwości taktowania rdzenia
        :return: częstotliwość taktowania rdzenia [MHz] 
        """
        return self.core_frequency

    def set_core_frequency(self, core_frequency: int):
        """
        Setter częstotliwości taktowania rdzenia
        :param core_frequency: maksymalna częstotliwość taktowania rdzenia [MHz] 
        """
        self.core_frequency = core_frequency

    def get_max_core_frequency(self):
        """
        Getter maksymalnej częstotliwości taktowania rdzenia
        :return: maksymalna częstotliwość taktowania rdzenia [MHz] 
        """
        return self.max_core_frequency

    def set_max_core_frequency(self, max_core_frequency: int):
        """
        Setter maksymalnej częstotliwości taktowania rdzenia
        :param max_core_frequency: liczba procesorów strumieniowych 
        """
        self.max_core_frequency = max_core_frequency

    def get_stream_processors(self):
        """
        Getter liczby procesorów strumieniowych
        :return: liczba procesorów strumieniowych 
        """
        return self.stream_processors

    def set_stream_processors(self, stream_processors: int):
        """
        Setter liczby procesorów strumieniowych
        :param stream_processors: liczba procesorów strumieniowych 
        """
        self.stream_processors = stream_processors

    def get_rop_units(self):
        """
        Getter liczby jednostek ROP
        :return: liczba jednostek ROP (Render Out Pixel) 
        """
        return self.rop_units

    def set_rop_units(self, rop_units: int):
        """
        Setter liczby jednostek ROP
        :param rop_units: liczba jednostek ROP (Render Out Pixel) 
        """
        self.rop_units = rop_units

    def get_texturing_units(self):
        """
        Getter liczby jednostek teksturujących
        :return: liczba jednostek teksturujących 
        """
        return self.texturing_units

    def set_texturing_units(self, texturing_units: int):
        """
        Setter liczby jednostek teksturujących
        :param texturing_units: liczba jednostek teksturujących 
        """
        self.texturing_units = texturing_units

    def get_rt_cores(self):
        """
        Getter liczby rdzeni RT
        :return: liczba rdzeni RT 
        """
        return self.rt_cores

    def set_rt_cores(self, rt_cores: int):
        """
        Setter liczby rdzeni RT
        :param rt_cores: liczba rdzeni RT 
        """
        self.rt_cores = rt_cores

    def get_tensor_cores(self):
        """
        Getter liczby rdzeni Tensor
        :return: liczba rdzeni Tensor 
        """
        return self.tensor_cores

    def set_tensor_cores(self, tensor_cores: int):
        """
        Setter liczby rdzeni Tensor
        :param tensor_cores: liczba rdzeni Tensor
        """
        self.tensor_cores = tensor_cores

    def get_dlss(self):
        """
        Getter rodzaju DLSS
        :return: rodzaj DLSS 
        """
        return self.dlss

    def set_dlss(self, dlss: str):
        """
        Setter rodzaju DLSS
        :param dlss: rodzaj DLSS
        """
        self.dlss = dlss

    def get_connector(self):
        """
        Getter typu złącza karty graficznej
        :return: typ złącza karty graficznej do płyty głównej 
        """
        return self.connector

    def set_connector(self, connector: str):
        """
        Setter typu złącza karty graficznej
        :param connector: typ złącza karty graficznej do płyty głównej
        """
        self.connector = connector

    def get_card_length(self):
        """
        Getter długości karty graficznej
        :return: długość karty graficznej [mm] 
        """
        return self.card_length

    def set_card_length(self, card_length: int):
        """
        Setter długości karty graficznej
        :param card_length: długość karty graficznej [mm]
        """
        self.card_length = card_length

    def get_resolution(self):
        """
        Getter rozdzielczości generowanego obrazu
        :return: rozdzielczość generowanego obrazu 
        """
        return self.resolution

    def set_resolution(self, resolution: str):
        """
        Setter rozdzielczości generowanego obrazu
        :param resolution: rozdzielczość generowanego obrazu
        """
        self.resolution = resolution

    def get_recommended_ps(self):
        """
        Getter rekomendowanej mocy zasilacza
        :return: rekomendowana moc zasilacza [W] 
        """
        return self.recommended_ps

    def set_recommended_ps(self, recommended_ps: int):
        """
        Setter rekomendowanej mocy zasilacza
        :param recommended_ps: rekomendowana moc zasilacza [W] 
        """
        self.recommended_ps = recommended_ps

    def get_lightning(self):
        """
        Getter informacji czy karta graficzna ma podświetlenie
        :return: ``True`` jeśli karta graficzna ma podświetlenie, ``False`` w przeciwnym razie 
        """
        return self.lightning

    def set_lightning(self, lightning: bool):
        """
        Setter informacji, czy karta graficzna ma podświetlenie
        :param lightning: informacja, czy karta graficzna ma podświetlenie (``True`` lub ``False``) 
        """
        self.lightning = lightning

    def get_ram(self):
        """
        Getter liczby pamięci RAM
        :return: liczba pamięci RAM (wartości dodatnie oznaczają jednostkę [GB], ujemne [MB]) 
        """
        return self.ram

    def set_ram(self, ram: int):
        """
        Setter liczby pamięci RAM
        :param ram: liczba pamięci RAM (wartości dodatnie oznaczają jednostkę [GB], ujemne [MB])
        """
        self.ram = ram

    def get_ram_type(self):
        """
        Getter typu pamięci RAM
        :return: typ pamięci RAM 
        """
        return self.ram_type

    def set_ram_type(self, ram_type: str):
        """
        Setter typu pamięci RAM
        :param ram_type: typ pamięci RAM
        """
        self.ram_type = ram_type

    def get_data_bus(self):
        """
        Getter szyny danych
        :return: szyna danych [bit]
        """
        return self.data_bus

    def set_data_bus(self, data_bus: int):
        """
        Setter szyny danych
        :param data_bus: szyna danych [bit]
        """
        self.data_bus = data_bus

    def get_memory_freq(self):
        """
        Getter taktowania pamięci
        :return: taktowanie pamięci [MHz] 
        """
        return self.memory_freq

    def set_memory_freq(self, memory_freq: int):
        """
        Setter taktowania pamięci
        :param memory_freq: taktowanie pamięci [MHz]
        """
        self.memory_freq = memory_freq

    def get_cooling_type(self):
        """
        Getter typu chłodzenia
        :return: typ chłodzenia 
        """
        return self.cooling_type

    def set_cooling_type(self, cooling_type: str):
        """
        Setter typu chłodzenia
        :param cooling_type: typ chłodzenia
        """
        self.cooling_type = cooling_type

    def get_number_of_fans(self):
        """
        Getter liczby wiatraków
        :return: liczba wiatraków 
        """
        return self.number_of_fans

    def set_number_of_fans(self, number_of_fans: int):
        """
        Setter liczby wiatraków
        :param number_of_fans: liczba wiatraków
        """
        self.number_of_fans = number_of_fans
