import sqlalchemy
from sqlalchemy import Column, ForeignKey, Integer

from product.Product import Product


class RAM(Product):
    """
    Klasa będąca reprezentacją tabeli ``ram`` w bazie danych
    """
    __tablename__ = "ram"
    ean = Column("ean", ForeignKey("product.ean", ondelete="CASCADE", onupdate="CASCADE"), primary_key=True,
                 nullable=False)
    line = Column("line", sqlalchemy.VARCHAR(255), nullable=False)
    memory_type = Column("memory_type", sqlalchemy.VARCHAR(255), nullable=False)
    total_capacity = Column("total_capacity", Integer, nullable=False)
    number_of_modules = Column("number_of_modules", Integer, nullable=False)
    latency = Column("latency", Integer, nullable=False)
    lighting = Column("lighting", Integer, nullable=False)
    frequency = Column("frequency", Integer, nullable=False)

    def __init__(self, name: str, producer: str, category: str, description: str, price: float, producer_code: str,
                 ean: int, line: str, memory_type: str, total_capacity: int, number_of_modules: int,
                 frequency: int, latency: str, lighting: bool):
        """
        Konstruktor klasy ``RAM``
        :param name: nazwa pamięci RAM
        :param producer: producent pamięci RAM
        :param category: kategoria (``RAM``)
        :param description: opis pamięci RAM
        :param price: cena pamięci RAM
        :param producer_code: kod producenta
        :param ean: kod EAN (European Article Number)
        :param line: linia pamięci RAM
        :param memory_type: typ pamięci RAM
        :param total_capacity: łączna pojemność (wartości dodatnie oznaczają jednostkę [GB], ujemne [MB])
        :param number_of_modules: liczba modułów pamięci
        :param frequency: częstotliwość taktowania pamięci
        :param latency: opóźnienie zegara
        :param lighting: informacja, czy pamięć RAM ma podświetlenie
        """
        super().__init__(name, producer, category, description, price, producer_code, ean)
        self.line = line
        self.memory_type = memory_type
        self.total_capacity = total_capacity
        self.number_of_modules = number_of_modules
        self.frequency = frequency
        self.latency = latency
        self.lighting = lighting

    def print_product_specs(self):
        """
        Metoda wypisująca specyfikację pamięci RAM
        """
        super().print_product_specs()
        print("Line:", self.line)
        print("Memory type:", self.memory_type)
        if self.total_capacity > 0:
            print("Total capacity:", self.total_capacity, "GB")
        else:
            print("Total capacity:", ((-1) * self.total_capacity), "MB")
        print("Number of modules:", self.number_of_modules)
        print("Frequency:", self.frequency, "MHz")
        print("Latency:", self.latency)
        print("Lighting:", self.lighting)
        super().print_end()

    def get_line(self):
        """
        Getter linii pamięci RAM
        :return: linia pamięci RAM
        """
        return self.line

    def set_line(self, line: str):
        """
        Setter linii pamięci RAM
        :param line: linia pamięci RAM
        """
        self.line = line

    def get_memory_type(self):
        """
        Getter typu pamięci RAM
        :return: typ pamięci RAM
        """
        return self.memory_type

    def set_memory_type(self, memory_type: str):
        """
        Setter typu pamięci RAM
        :param memory_type: typ pamięci RAM
        """
        self.memory_type = memory_type

    def get_total_capacity(self):
        """
        Getter łącznej pojemności
        :return: łączna pojemność (wartości dodatnie oznaczają jednostkę [GB], ujemne [MB])
        """
        return self.total_capacity

    def set_total_capacity(self, total_capacity: int):
        """
        Setter łącznej pojemności
        :param total_capacity: łączna pojemność (wartości dodatnie oznaczają jednostkę [GB], ujemne [MB])
        """
        self.total_capacity = total_capacity

    def get_number_of_modules(self):
        """
        Getter liczby modułów pamięci
        :return: liczba modułów pamięci
        """
        return self.number_of_modules

    def set_number_of_modules(self, number_of_modules: int):
        """
        Setter liczby modułów pamięci
        :param number_of_modules: liczba modułów pamięci
        """
        self.number_of_modules = number_of_modules

    def get_frequency(self):
        """
        Getter częstotliwości taktowania pamięci
        :return: częstotliwość taktowania pamięci
        """
        return self.frequency

    def set_frequency(self, frequency: int):
        """
        Setter częstotliwości taktowania pamięci
        :param frequency: częstotliwość taktowania pamięci
        """
        self.frequency = frequency

    def get_latency(self):
        """
        Getter opóźnienia zegara
        :return: opóźnienie zegara
        """
        return self.latency

    def set_latency(self, latency: str):
        """
        Setter opóźnienia zegara
        :param latency: opóźnienie zegara
        """
        self.latency = latency

    def get_lighting(self):
        """
        Getter informacji czy pamięć RAM ma podświetlenie
        :return: ``True`` jeśli pamięć RAM ma podświetlenie, ``False`` w przeciwnym razie
        """
        return self.lighting

    def set_lighting(self, lighting: bool):
        """
        Setter informacji czy pamięć RAM ma podświetlenie
        :param lighting: informacja, czy pamięć RAM ma podświetlenie (``True`` lub ``False``)
        """
        self.lighting = lighting
