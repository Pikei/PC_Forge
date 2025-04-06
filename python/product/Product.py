import sqlalchemy
from sqlalchemy import Column
from sqlalchemy.orm import declarative_base

Base = declarative_base()


class Product(Base):
    """
    Klasa będąca reprezentacją tabeli ``product`` w bazie danych. Jest wzorcem dla wszystkich typów produktów
    znajdujących się w bazie danych i przechowuje cechy wspólne.
    """
    __tablename__ = "product"
    ean = Column("ean", sqlalchemy.BIGINT, primary_key=True, autoincrement=False, nullable=False, unique=True)
    name = Column("name", sqlalchemy.VARCHAR(255), nullable=False)
    description = Column("description", sqlalchemy.TEXT, nullable=False)
    price = Column("price", sqlalchemy.FLOAT, nullable=False)
    producer_code = Column("producer_code", sqlalchemy.VARCHAR(255), nullable=False, unique=True)
    producer = Column("producer", sqlalchemy.VARCHAR(255), nullable=False)
    category = Column("category", sqlalchemy.VARCHAR(50), nullable=False)

    def __init__(self, name: str, producer: str, category: str, description: str, price: float, producer_code: str,
                 ean: int):
        """
        Konstruktor klasy ``Product``.
        :param name: Nazwa produktu
        :param producer: Nazwa producenta
        :param category: Kod kategorii produktu (kody kategorii znajdują się w klasie ``ProductCategory``)
        :param description: Opis produktu w kodzie HTML
        :param price: Cena produktu
        :param producer_code: Kod producenta
        :param ean: European Article Number, będący również kluczem głównym produktu w bazie danych
        """
        self.name: str = name
        self.producer: str = producer
        self.category: str = category
        self.description: str = description
        self.price: float = price
        self.producer_code: str = producer_code
        self.ean: int = ean

    def print_end(self):
        """
        Wypisuje opis produktu, wywoływana po wypisaniu specyfikacji
        """
        print("-- DESCRIPTION --")
        print(self.description.replace("<", "\n<").replace("\n</", "</"))
        print("---------------------------------------\n")

    def print_product_specs(self):
        """
        Wypisuje dane wspólne dla wszystkich typów produktów.
        Wywoływana tylko z parametrem uruchomieniowym ``PRINT_SPECS`` lub przy znalezieniu duplikatów produktów.
        """
        print("--------------- ", self.name, " ---------------")
        print("Product type:", self.category)
        print("Producer:", self.producer)
        print("Producer code:", self.producer_code)
        print("EAN:", self.ean)
        print("Price [PLN]:", self.price)
        print("--- SPECS ---")

    # Metody get oraz set (tzw. gettery i settery) w tej klasie, jak również w dziedziczących klasach
    # wynikają z podstawowego założenia enkapsulacji, w ramach której interakcja z obiektem i jego atrybutami
    # powinna odbywać się wyłącznie za pomocą przeznaczonych do tego metod
    def get_name(self):
        """
        Getter nazwy produktu
        :return: Nazwa produktu
        """
        return self.name

    def set_name(self, name: str):
        """
        Setter nazwy produktu
        :param name: Nowa nazwa produktu
        """
        self.name = name

    def get_producer(self):
        """
        Getter nazwy producenta
        :return: Nazwa producenta
        """
        return self.producer

    def set_producer(self, producer: str):
        """
        Setter nazwy producenta
        :param producer: Nowa nazwa producenta
        """
        self.producer = producer

    def get_category(self):
        """
        Getter kodu kategorii produktu
        :return: Kod kategorii produktu
        """
        return self.category

    def set_category(self, category: str):
        """
        Setter kodu kategorii produktu
        :param category: Kod kategorii produktu
        """
        self.category = category

    def get_description(self):
        """
        Getter opisu produktu
        :return: Opis produktu w kodzie HTML
        """
        return self.description

    def set_description(self, description: str):
        """
        Setter opisu produktu
        :param description: Nowy opis produktu
        """
        self.description = description

    def get_price(self):
        """
        Getter ceny produktu
        :return: Cena produktu
        """
        return self.price

    def set_price(self, price: float):
        """
        Setter ceny produktu
        :param price: Nowa cena produktu
        """
        self.price = price

    def get_producer_code(self):
        """
        Getter kodu producenta
        :return: Kod producenta
        """
        return self.producer_code

    def set_producer_code(self, producer_code: str):
        """
        Setter kodu producenta
        :param producer_code: Kod producenta
        """
        self.producer_code = producer_code

    def get_ean(self):
        """
        Getter kodu EAN (European Article Number)
        :return: kod EAN
        """
        return self.ean

    def set_ean(self, ean: int):
        """
        Setter kodu EAN (European Article Number)
        :param ean: kod EAN produktu
        """
        self.ean = ean
