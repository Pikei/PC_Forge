import sqlalchemy
from sqlalchemy import Column, ForeignKey
from product.Product import Product, Base


class CpuSocket(Base):
    __tablename__ = "cpu_socket"
    id = Column("socket_id", sqlalchemy.Integer, primary_key=True, autoincrement=True)
    name = Column("socket", sqlalchemy.VARCHAR(255), nullable=False)


class Processor(Product):
    """
    Klasa będąca reprezentacją tabeli ``processor`` w bazie danych
    """
    __tablename__ = "processor"
    ean = Column("ean", ForeignKey("product.ean", ondelete="CASCADE", onupdate="CASCADE"), primary_key=True,
                 nullable=False)
    line = Column("line", sqlalchemy.VARCHAR(255), nullable=False)
    model = Column("model", sqlalchemy.VARCHAR(255), nullable=False)
    cores = Column("cores", sqlalchemy.Integer, nullable=False)
    threads = Column("threads", sqlalchemy.Integer, nullable=False)
    socket_id = Column("socket_id", ForeignKey("cpu_socket.socket_id"), nullable=False)
    unlocked = Column("unlocked", sqlalchemy.Boolean, nullable=False)
    frequency = Column("frequency", sqlalchemy.Float, nullable=False)
    max_frequency = Column("max_frequency", sqlalchemy.Float, nullable=False)
    integrated_graphics_unit = Column("integrated_graphics_unit", sqlalchemy.VARCHAR(255), nullable=True)
    tdp = Column("tdp", sqlalchemy.Integer, nullable=False)
    cooler_included = Column("cooler_included", sqlalchemy.Boolean, nullable=False)
    packaging = Column("packaging", sqlalchemy.VARCHAR(255), nullable=False)

    def __init__(self, name: str, producer: str, category: str, description: str, price: float, producer_code: str,
                 ean: int, line: str, model: str, cores: int, threads: int, socket: str, unlocked: bool,
                 frequency: float, max_frequency: float, integrated_graphics_unit: str, tdp: int,
                 cooler_included: bool, packaging: str):
        """
        Konstruktor klasy ``Processor``
        :param name: nazwa procesora
        :param producer: producent procesora
        :param category: kategoria (``CPU``)
        :param description: opis procesora
        :param price: cena procesora
        :param producer_code: kod producenta
        :param ean: kod EAN (European Article Number)
        :param line: linia procesora
        :param model: model procesora
        :param cores: liczba rdzeni
        :param threads: liczba wątków
        :param socket: gniazdo procesora
        :param unlocked: informacja czy procesor ma odblokowany mnożnik
        :param frequency: częstotliwość taktowania procesora
        :param max_frequency: maksymalna częstotliwość taktowania procesora
        :param integrated_graphics_unit: zintegrowany układ graficzny
        :param tdp: TDP (Thermal Design Power) [W]
        :param cooler_included: informacja czy procesor ma załączony układ chłodzenia
        :param packaging: typ opakowania
        """
        super().__init__(name, producer, category, description, price, producer_code, ean)
        self.line: str = line
        self.model: str = model
        self.cores: int = cores
        self.threads: int = threads
        self.socket: str = socket
        self.unlocked: bool = unlocked
        self.frequency: float = frequency
        self.max_frequency: float = max_frequency
        self.integrated_graphics_unit: str = integrated_graphics_unit
        self.tdp: int = tdp
        self.cooler_included: bool = cooler_included
        self.packaging: str = packaging
        self.socket_id = None

    def print_product_specs(self):
        """
        Metoda wypisująca specyfikację procesora
        """
        super().print_product_specs()
        print("Line:", self.line)
        print("Model:", self.model)
        print("Number of cores:", self.cores)
        print("Number of threads:", self.threads)
        print("CPU socket:", self.socket)
        print("Unlocked:", self.unlocked)
        print("Frequency:", self.frequency, "GHz")
        print("Max frequency:", self.max_frequency, "GHz")
        print("Integrated Graphics Unit:", self.integrated_graphics_unit)
        print("TDP:", self.tdp, "W")
        print("Cooler included:", self.cooler_included)
        print("Packaging:", self.packaging)
        super().print_end()

    def get_line(self):
        """
        Getter linii procesora
        :return: linia procesora
        """
        return self.line

    def set_line(self, line: str):
        """
        Setter linii procesora
        :param line: linia procesora
        """
        self.line = line

    def get_model(self):
        """
        Getter modelu procesora
        :return: model procesora
        """
        return self.model

    def set_model(self, model: str):
        """
        Setter modelu procesora
        :param model: model procesora
        """
        self.model = model

    def get_cores(self):
        """
        Getter liczby rdzeni
        :return: liczba rdzeni
        """
        return self.cores

    def set_cores(self, cores: int):
        """
        Setter liczby rdzeni
        :param cores: liczba rdzeni
        """
        self.cores = cores

    def get_threads(self):
        """
        Getter liczby wątków
        :return: liczba wątków
        """
        return self.threads

    def set_threads(self, threads: int):
        """
        Setter liczby wątków
        :param threads: liczba wątków
        """
        self.threads = threads

    def get_socket(self):
        """
        Getter gniazda procesora
        :return: gniazdo procesora
        """
        return self.socket

    def set_socket(self, socket: str):
        """
        Setter gniazda procesora
        :param socket: gniazdo procesora
        """
        self.socket = socket

    def get_unlocked(self):
        """
        Getter informacji czy procesor ma odblokowany mnożnik
        :return: ``True`` jeśli procesor ma odblokowany mnożnik, ``False`` w przeciwnym razie
        """
        return self.unlocked

    def set_unlocked(self, unlocked: bool):
        """
        Setter informacji czy procesor ma odblokowany mnożnik
        :param unlocked: informacja, czy procesor ma odblokowany mnożnik (``True`` lub ``False``)
        """
        self.unlocked = unlocked

    def get_frequency(self):
        """
        Getter częstotliwości taktowania procesora
        :return: częstotliwość taktowania procesora
        """
        return self.frequency

    def set_frequency(self, frequency: float):
        """
        Setter częstotliwości taktowania procesora
        :param frequency: częstotliwość taktowania procesora
        """
        self.frequency = frequency

    def get_max_frequency(self):
        """
        Getter maksymalnej częstotliwości taktowania procesora
        :return: maksymalna częstotliwość taktowania procesora
        """
        return self.max_frequency

    def set_max_frequency(self, max_frequency: float):
        """
        Setter maksymalnej częstotliwości taktowania procesora
        :param max_frequency: maksymalna częstotliwość taktowania procesora
        """
        self.max_frequency = max_frequency

    def get_integrated_graphics_unit(self):
        """
        Getter zintegrowanego układu graficznego
        :return: zintegrowany układ graficzny
        """
        return self.integrated_graphics_unit

    def set_integrated_graphics_unit(self, integrated_graphics_unit: str):
        """
        Setter zintegrowanego układu graficznego
        :param integrated_graphics_unit: zintegrowany układ graficzny
        """
        self.integrated_graphics_unit = integrated_graphics_unit

    def get_tdp(self):
        """
        Getter TDP
        :return: TDP (Thermal Design Power) [W]
        """
        return self.tdp

    def set_tdp(self, tdp: int):
        """
        Setter TDP
        :param tdp: TDP (Thermal Design Power) [W]
        """
        self.tdp = tdp

    def get_cooler_included(self):
        """
        Getter informacji czy procesor ma załączony układ chłodzenia
        :return: ``True`` jeśli procesor ma załączony układ chłodzenia, ``False`` w przeciwnym razie
        """
        return self.cooler_included

    def set_cooler_included(self, cooler_included: bool):
        """
        Setter informacji czy procesor ma załączony układ chłodzenia
        :param cooler_included: informacja, czy procesor ma załączony układ chłodzenia (``True`` lub ``False``)
        """
        self.cooler_included = cooler_included

    def get_packaging(self):
        """
        Getter typu opakowania
        :return: typ opakowania
        """
        return self.packaging

    def set_packaging(self, packaging: str):
        """
        Setter typu opakowania
        :param packaging: typ opakowania
        """
        self.packaging = packaging

    def get_socket_id(self):
        """
        Getter identyfikatora (klucza obcego) gniazda procesora
        :return: identyfikator gniazda procesora
        """
        return self.socket_id

    def set_socket_id(self, socket_id: int):
        """
        Setter identyfikatora (klucza obcego) gniazda procesora
        :param socket_id: identyfikator gniazda procesora
        """
        self.socket_id = socket_id
