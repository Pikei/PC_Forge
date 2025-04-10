import sqlalchemy
from sqlalchemy import Column, ForeignKey

from product.Product import Product, Base


class Drive(Product):
    """
    Klasa będąca reprezentacją tabeli ``drive`` w bazie danych.
    Przechowuje informacje wspólne dla wszystkich typów dysków pamięci masowej
    """
    __tablename__ = "drive"
    ean = Column("ean", ForeignKey("product.ean", ondelete="CASCADE", onupdate="CASCADE"), primary_key=True,
                 nullable=False)
    storage = Column("storage", sqlalchemy.Integer, nullable=False)
    drive_format = Column("drive_format", sqlalchemy.VARCHAR(255), nullable=False)
    interface = Column("drive_interface", sqlalchemy.VARCHAR(255), nullable=False)

    def __init__(self, name: str, producer: str, category: str, description: str, price: float, producer_code: str,
                 ean: int, drive_format: str, storage: int, interface: str):
        """
        Konstruktor klasy ``Drive``
        :param name: nazwa dysku
        :param producer: producent dysku
        :param category: kategoria (``SSD`` lub ``HDD``)
        :param description: opis dysku
        :param price: cena dysku
        :param producer_code: kod producenta
        :param ean: kod EAN (European Article Number)
        :param drive_format: format dysku
        :param storage: pojemność dysku
        :param interface: interfejs dysku
        """
        super().__init__(name, producer, category, description, price, producer_code, ean)
        self.drive_format: str = drive_format
        self.storage: int = storage
        self.interface: str = interface

    def print_product_specs(self):
        """
        Metoda wypisująca specyfikacje dla wszystkich typów dysków
        """
        super().print_product_specs()
        print("Drive format:", self.drive_format)
        print("Storage:", self.storage, "GB")
        print("Interface:", self.interface)

    def get_drive_format(self):
        """
        Getter formatu dysku
        :return: format dysku
        """
        return self.drive_format

    def set_drive_format(self, drive_format: str):
        """
        Setter formatu dysku
        :param drive_format: format dysku
        """
        self.drive_format = drive_format

    def get_storage(self):
        """
        Getter pojemności dysku
        :return: pojemność dysku [GB]
        """
        return self.storage

    def set_storage(self, storage: int):
        """
        Setter pojemności dysku
        :param storage: pojemność dysku [GB]
        """
        self.storage = storage

    def get_interface(self):
        """
        Getter interfejsu dysku
        :return: interfejs dysku
        """
        return self.interface

    def set_interface(self, interface: str):
        """
        Setter interfejsu dysku
        :param interface: interfejs dysku
        """
        self.interface = interface


class HardDiskDrive(Drive):
    """
    Klasa będąca reprezentacją tabeli ``hdd`` w bazie danych
    """
    __tablename__ = "hdd"
    ean = Column("ean", ForeignKey("drive.ean", ondelete="CASCADE", onupdate="CASCADE"), primary_key=True,
                 nullable=False)
    rotational_speed = Column("rotational_speed", sqlalchemy.Integer, nullable=False)

    def __init__(self, name: str, producer: str, category: str, description: str, price: float, producer_code: str,
                 ean: int,
                 drive_format: str, storage: int, interface: str, rotational_speed: int):
        """
        Konstruktor klasy ``HardDiskDrive``
        :param name: nazwa dysku
        :param producer: producent dysku
        :param category: kategoria (``HDD``)
        :param description: opis dysku
        :param price: cena dysku
        :param producer_code: kod producenta
        :param ean: kod EAN (European Article Number)
        :param drive_format: format dysku
        :param storage: pojemność dysku
        :param interface: interfejs dysku
        :param rotational_speed: prędkość obrotowa dysku [obr./min]
        """
        super().__init__(name, producer, category, description, price, producer_code, ean, drive_format, storage,
                         interface)
        self.rotational_speed = rotational_speed

    def print_product_specs(self):
        """
        Metoda wypisująca specyfikację dla dysków HDD
        """
        super().print_product_specs()
        print("Rotational speed:", self.rotational_speed, "rpm")
        super().print_end()

    def get_rotational_speed(self):
        """
        Getter prędkości obrotowej dysku
        :return: prędkość obrotowa dysku [obr./min]
        """
        return self.rotational_speed

    def set_rotational_speed(self, rotational_speed: int):
        """
        Setter prędkości obrotowej dysku
        :param rotational_speed: prędkość obrotowa dysku [obr./min]
        """
        self.rotational_speed = rotational_speed


class SolidStateDrive(Drive):
    """
    Klasa będąca reprezentacją tabeli ``ssd`` w bazie danych
    """
    __tablename__ = "ssd"
    ean = Column("ean", ForeignKey("drive.ean", ondelete="CASCADE", onupdate="CASCADE"), primary_key=True,
                 nullable=False)
    read_speed = Column("read_speed", sqlalchemy.Integer, nullable=False)
    write_speed = Column("write_speed", sqlalchemy.Integer, nullable=False)

    def __init__(self, name: str, producer: str, category: str, description: str, price: float, producer_code: str,
                 ean: int, drive_format: str, storage: int, interface: str, read_speed: int, write_speed: int):
        """
        Konstruktor klasy ``SolidStateDrive``
        :param name: nazwa dysku
        :param producer: producent dysku
        :param category: kategoria (``SSD``)
        :param description: opis dysku
        :param price: cena dysku
        :param producer_code: kod producenta
        :param ean: kod EAN (European Article Number)
        :param drive_format: format dysku
        :param storage: pojemność dysku
        :param interface: interfejs dysku
        :param read_speed: prędkość odczytu [MB/s]
        :param write_speed: prędkość zapisu [MB/s]
        """
        super().__init__(name, producer, category, description, price, producer_code, ean, drive_format, storage,
                         interface)
        self.read_speed: int = read_speed
        self.write_speed: int = write_speed

    def print_product_specs(self):
        """
        Metoda wypisująca specyfikację dla dysków SSD
        :return:
        """
        super().print_product_specs()
        print("Read speed:", self.read_speed), "MB/s"
        print("Write speed:", self.write_speed, "MB/s")
        super().print_end()

    def get_read_speed(self):
        """
        Getter prędkości odczytu
        :return: prędkość odczytu [MB/s]
        """
        return self.read_speed

    def set_read_speed(self, read_speed: int):
        """
        Setter prędkości odczytu
        :param read_speed: prędkość odczytu [MB/s]
        """
        self.read_speed = read_speed

    def get_write_speed(self):
        """
        Getter prędkości zapisu
        :return: prędkość zapisu [MB/s]
        """
        return self.write_speed

    def set_write_speed(self, write_speed: int):
        """
        Setter prędkości zapisu
        :param write_speed: prędkość zapisu [MB/s]
        """
        self.write_speed = write_speed
