import sqlalchemy
from sqlalchemy import Column, ForeignKey

from product.Product import Product


class GraphicsCard(Product):
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
                 ean: int,
                 chipset_producer: str, chipset: str, core_frequency: int, max_core_frequency: int,
                 stream_processors: int, rop_units: int, texturing_units: int, rt_cores: int, tensor_cores: int,
                 dlss: str, connector: str, card_length: int, resolution: str, recommended_ps: int, lightning: bool,
                 ram: int, ram_type: str, data_bus: int, memory_freq: int, cooling_type: str, number_of_fans: int):
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
        return self.chipset_producer

    def set_chipset_producer(self, chipset_producer: str):
        self.chipset_producer = chipset_producer

    def get_chipset(self):
        return self.chipset

    def set_chipset(self, chipset: str):
        self.chipset = chipset

    def get_core_frequency(self):
        return self.core_frequency

    def set_core_frequency(self, core_frequency: int):
        self.core_frequency = core_frequency

    def get_max_core_frequency(self):
        return self.max_core_frequency

    def set_max_core_frequency(self, max_core_frequency: int):
        self.max_core_frequency = max_core_frequency

    def get_stream_processors(self):
        return self.stream_processors

    def set_stream_processors(self, stream_processors: int):
        self.stream_processors = stream_processors

    def get_rop_units(self):
        return self.rop_units

    def set_rop_units(self, rop_units: int):
        self.rop_units = rop_units

    def get_texturing_units(self):
        return self.texturing_units

    def set_texturing_units(self, texturing_units: int):
        self.texturing_units = texturing_units

    def get_rt_cores(self):
        return self.rt_cores

    def set_rt_cores(self, rt_cores: int):
        self.rt_cores = rt_cores

    def get_tensor_cores(self):
        return self.tensor_cores

    def set_tensor_cores(self, tensor_cores: int):
        self.tensor_cores = tensor_cores

    def get_dlss(self):
        return self.dlss

    def set_dlss(self, dlss: str):
        self.dlss = dlss

    def get_connector(self):
        return self.connector

    def set_connector(self, connector: str):
        self.connector = connector

    def get_card_length(self):
        return self.card_length

    def set_card_length(self, card_length: int):
        self.card_length = card_length

    def get_resolution(self):
        return self.resolution

    def set_resolution(self, resolution: str):
        self.resolution = resolution

    def get_recommended_ps(self):
        return self.recommended_ps

    def set_recommended_ps(self, recommended_ps: int):
        self.recommended_ps = recommended_ps

    def get_lightning(self):
        return self.lightning

    def set_lightning(self, lightning: bool):
        self.lightning = lightning

    def get_ram(self):
        return self.ram

    def set_ram(self, ram: int):
        self.ram = ram

    def get_ram_type(self):
        return self.ram_type

    def set_ram_type(self, ram_type: str):
        self.ram_type = ram_type

    def get_data_bus(self):
        return self.data_bus

    def set_data_bus(self, data_bus: int):
        self.data_bus = data_bus

    def get_memory_freq(self):
        return self.memory_freq

    def set_memory_freq(self, memory_freq: int):
        self.memory_freq = memory_freq

    def get_cooling_type(self):
        return self.cooling_type

    def set_cooling_type(self, cooling_type: str):
        self.cooling_type = cooling_type

    def get_number_of_fans(self):
        return self.number_of_fans

    def set_number_of_fans(self, number_of_fans: int):
        self.number_of_fans = number_of_fans
