import sqlalchemy
from sqlalchemy import Column, ForeignKey

from product.Product import Product, Base


class DriveInterfaces(Base):
    __tablename__ = "drive_interfaces"
    id = Column("interface_id", sqlalchemy.Integer, primary_key=True, autoincrement=True)
    name = Column("interface_name", sqlalchemy.VARCHAR(255), nullable=False)

class Drive(Product):
    __tablename__ = "drive"
    ean = Column("ean", ForeignKey("product.ean", ondelete="CASCADE", onupdate="CASCADE"), primary_key=True,
                 nullable=False)
    storage = Column("storage", sqlalchemy.Integer, nullable=False)
    drive_format = Column("drive_format", sqlalchemy.VARCHAR(255), nullable=False)
    drive_interface_id = Column("drive_interface_id", ForeignKey("drive_interfaces.interface_id"), nullable=False)

    def __init__(self, name: str, producer: str, category: str, description: str, price: float, producer_code: str,
                 ean: int,
                 drive_format: str, storage: int, interface: str):
        super().__init__(name, producer, category, description, price, producer_code, ean)
        self.drive_format: str = drive_format
        self.storage: int = storage
        self.interface: str = interface
        self.drive_interface_id = None

    def print_product_specs(self):
        super().print_product_specs()
        print("Drive format:", self.drive_format)
        print("Storage:", self.storage, "GB")
        print("Interface:", self.interface)

    def get_drive_format(self):
        return self.drive_format

    def set_drive_format(self, drive_format: str):
        self.drive_format = drive_format

    def get_storage(self):
        return self.storage

    def set_storage(self, storage: int):
        self.storage = storage

    def get_interface(self):
        return self.interface

    def set_interface(self, interface: str):
        self.interface = interface

    def get_drive_interface_id(self):
        return self.drive_interface_id

    def set_drive_interface_id(self, drive_interface_id: int):
        self.drive_interface_id = drive_interface_id


class HardDiskDrive(Drive):
    __tablename__ = "hdd"
    ean = Column("ean", ForeignKey("drive.ean", ondelete="CASCADE", onupdate="CASCADE"), primary_key=True,
                 nullable=False)
    rotational_speed = Column("rotational_speed", sqlalchemy.Integer, nullable=False)


    def __init__(self, name: str, producer: str, category: str, description: str, price: float, producer_code: str,
                 ean: int,
                 drive_format: str, storage: int, interface: str, rotational_speed: int):
        super().__init__(name, producer, category, description, price, producer_code, ean, drive_format, storage,
                         interface)
        self.rotational_speed = rotational_speed

    def print_product_specs(self):
        super().print_product_specs()
        print("Rotational speed:", self.rotational_speed, "rpm")
        super().print_end()

    def get_rotational_speed(self):
        return self.rotational_speed

    def set_rotational_speed(self, rotational_speed: int):
        self.rotational_speed = rotational_speed


class SolidStateDrive(Drive):
    __tablename__ = "ssd"
    ean = Column("ean", ForeignKey("drive.ean", ondelete="CASCADE", onupdate="CASCADE"), primary_key=True,
                 nullable=False)
    read_speed = Column("read_speed", sqlalchemy.Integer, nullable=False)
    write_speed = Column("write_speed", sqlalchemy.Integer, nullable=False)

    def __init__(self, name: str, producer: str, category: str, description: str, price: float, producer_code: str,
                 ean: int,
                 drive_format: str, storage: int, interface: str, read_speed: int, write_speed: int):
        super().__init__(name, producer, category, description, price, producer_code, ean, drive_format, storage,
                         interface)
        self.read_speed: int = read_speed
        self.write_speed: int = write_speed

    def print_product_specs(self):
        super().print_product_specs()
        print("Read speed:", self.read_speed), "MB/s"
        print("Write speed:", self.write_speed, "MB/s")
        super().print_end()

    def get_read_speed(self):
        return self.read_speed

    def set_read_speed(self, read_speed: int):
        self.read_speed = read_speed

    def get_write_speed(self):
        return self.write_speed

    def set_write_speed(self, write_speed: int):
        self.write_speed = write_speed
