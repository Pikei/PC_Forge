from regex import split
from selenium import webdriver
from selenium.webdriver.common.by import By

from product.GraphicsCard import GraphicsCard
from product.Motherboard import Motherboard
from product.PowerSuppy import PowerSupply
from product.Processor import Processor
from product.Product import Product
from product.ProductCategory import UrlCategory, ProductCategory
from product.RAM import RAM
from utils.CommonUtils import CommonUtils
from utils.ProductValidator import ProductValidator
from utils.WebUtil import WebUtil


class ProductParser:
    """
    Klasa pobierająca i przetwarzająca dane z adresu URL na obiekty klas produktów
    """

    def __init__(self, driver: webdriver.Chrome, web_util: WebUtil):
        """
        Konstruktor klasy ProductParser
        :param driver: obiekt klasy ``WebDriver``
        :param web_util: obiekt klasy ``WebUtil``
        """
        self.url = "https://www.morele.net/"
        self.driver = driver
        self.util = web_util
        CommonUtils.directory_exists("images")

    def get_product_description(self, product_name):
        """
        Pobiera wiersze opisu produktu przekształcając je na kod HTML
        :param product_name: Nazwa produktu będąca alternatywnym nagłówkiem w przypadku jego braku w danym wierszu opisu
        :return: Kod HTML opisu produktu
        """
        description = ""
        self.util.expand_description()

        desc_rows = self.util.get_elements(By.CSS_SELECTOR, "div.panel-description div.row div.text1", timeout=15)
        if len(desc_rows) == 0:
            desc_rows = self.util.get_elements(By.CSS_SELECTOR, "div.auto-description div.desc-items div.row",
                                               timeout=15)
        if len(desc_rows) == 0:
            description += self.util.get_description_row(self.util.get_element(By.CLASS_NAME, "panel-description"),
                                                         product_name)
        else:
            for row in desc_rows:
                description += self.util.get_description_row(row, product_name)
        return description

    def parse_product(self, url: str):
        """
        Pobiera dane wspólne dla wszystkich produktów z podanego adresu URL.
        :param url: Adres URL do produktu
        :return: obiekt odpowiedniej klasy rozszerzającej ``Product`` lub **None** jeśli nie znaleziono
        kluczowych elementów na stronie (tabela specyfikacji, cena, kod producenta)
        """
        print("Parsing:", url)

        if not self.util.load_page(url, By.CLASS_NAME, "product-specification__table"):
            print("FAIL: Could not load page or product does not have specification table")
            return None

        spec_rows = self.util.get_elements(By.CLASS_NAME, "specification__row")
        if spec_rows is None or len(spec_rows) == 0: return None

        name: str = ""
        price: float = 0.0
        producer_code: str = CommonUtils.get_value_from_spec_row(spec_rows, "Kod producenta")
        if producer_code is not None and producer_code != "":
            producer_code = producer_code.replace("\\", "-").replace("/", "-")
        web_price = self.util.get_element(By.CLASS_NAME, "product-price")
        web_name = self.util.get_element(By.CSS_SELECTOR, "h1.prod-name")

        if web_price is not None:
            price: float = CommonUtils.extract_float(web_price.text)

        if web_name is not None:
            name: str = web_name.text

        producer: str = CommonUtils.get_value_from_spec_row(spec_rows, "Producent")

        product = Product(name, producer, "", "", price, producer_code)

        cat_url = self.util.get_elements(By.CSS_SELECTOR, "a.main-breadcrumb")[-1].get_attribute("href")
        if cat_url in UrlCategory.CPU:
            product = self.parse_cpu(product, spec_rows)
        elif cat_url in UrlCategory.RAM:
            product = self.parse_ram(product, spec_rows)
        elif cat_url in UrlCategory.MB:
            product = self.parse_motherboard(product, spec_rows)
        elif cat_url in UrlCategory.GPU:
            product = self.parse_gpu(product, spec_rows)
        elif cat_url in UrlCategory.POWER_SUPPLY:
            product = self.parse_power_supply(product, spec_rows)

        if ProductValidator.validate(product):
            self.util.save_image(product.get_category(), product.get_producer(), product.get_producer_code())
            print("Parsed:", product.get_name())
            return product
        return None

    def parse_cpu(self, product: Product, spec_rows):
        """
        Pobiera i przetwarza dane z adresu URL na obiekt klasy ``Processor``
        :param product: obiekt klasy ``Product``, zawierający uniwersalne dane dla wszystkich typów produktów
        :param spec_rows: wiersze tabeli specyfikacji
        :return: obiekt klasy ``Processor``
        """
        pack = str(CommonUtils.get_value_from_spec_row(spec_rows, "Wersja opakowania"))

        product.set_name(product.get_name()[:product.get_name().find(",")])

        product.set_description(self.get_product_description(product.get_name()))

        product.set_category(str(ProductCategory.CPU))

        line = CommonUtils.get_value_from_spec_row(spec_rows, "Linia")

        model = product.get_name().split().pop(-1)

        spec_value = CommonUtils.get_value_from_spec_row(spec_rows, "Liczba rdzeni")
        num_of_cores = CommonUtils.extract_int(spec_value)

        spec_value = CommonUtils.get_value_from_spec_row(spec_rows, "Liczba wątków")
        num_of_threads = CommonUtils.extract_int(spec_value)

        socket = CommonUtils.get_value_from_spec_row(spec_rows, "Typ gniazda")

        spec_value = CommonUtils.get_value_from_spec_row(spec_rows, "Odblokowany mnożnik")
        unlocked = CommonUtils.translate_to_bool(spec_value)

        spec_value = CommonUtils.get_value_from_spec_row(spec_rows, "Częstotliwość taktowania procesora")
        frequency = CommonUtils.extract_float(spec_value)

        spec_value = CommonUtils.get_value_from_spec_row(spec_rows, "Częstotliwość maksymalna Turbo")

        max_frequency = CommonUtils.extract_float(spec_value)

        integrated_graphics_unit = CommonUtils.get_value_from_spec_row(spec_rows, "Zintegrowany układ graficzny")
        if integrated_graphics_unit == "Nie posiada":
            integrated_graphics_unit = None

        spec_value = CommonUtils.get_value_from_spec_row(spec_rows, "TDP")
        tdp = CommonUtils.extract_int(spec_value)

        spec_value = CommonUtils.get_value_from_spec_row(spec_rows, "Załączone chłodzenie")
        cooler_included = CommonUtils.translate_to_bool(spec_value)

        return Processor(product.get_name(), product.get_producer(), product.get_category(), product.get_description(),
                         product.get_price(), product.get_producer_code(), line, model, num_of_cores, num_of_threads,
                         socket, unlocked, frequency, max_frequency, integrated_graphics_unit, tdp, cooler_included,
                         pack)

    def parse_ram(self, product, spec_rows):
        """
        Pobiera i przetwarza dane z adresu URL na obiekt klasy ``RAM``
        :param product: obiekt klasy ``Product``, zawierający uniwersalne dane dla wszystkich typów produktów
        :param spec_rows: wiersze tabeli specyfikacji
        :return: obiekt klasy ``RAM``
        """
        product.set_category(str(ProductCategory.RAM))
        product.set_name(product.get_name()[:product.get_name().find(",")])
        product.set_description(self.get_product_description(product.get_name()))
        line = CommonUtils.get_value_from_spec_row(spec_rows, "Linia")
        memory_type = CommonUtils.get_value_from_spec_row(spec_rows, "Liczba modułów")

        # Pojemność łączna na stronie wylistowana jest w Gigabajtach [GB].
        # Jednak w bardzo rzadkich przypadkach jest to jednostka Megabajtów [MB]. Aby uniknąć zapisu wszystkich wartości
        # w jednostkach MB, co by oznaczało, że każdą wartość powyżej 1GB należałoby pomnożyć przez 1024,
        # oraz przechowywania danych jako liczb zmiennoprzecinkowych, np. 0.512 [GB]
        # pojemności pamięci wylistowane w jednostkach [MB] zapisywane są jako liczby ujemne
        spec_value = CommonUtils.get_value_from_spec_row(spec_rows, "Pojemność łączna")
        total_capacity = CommonUtils.extract_int(spec_value)
        if spec_value is not None and "MB" in spec_value:
            total_capacity = total_capacity * (-1)

        spec_value = CommonUtils.get_value_from_spec_row(spec_rows, "Liczba modułów")
        number_of_modules = CommonUtils.extract_int(spec_value)

        spec_value = CommonUtils.get_value_from_spec_row(spec_rows, "Częstotliwość pracy [MHz]")
        frequency = CommonUtils.extract_int(spec_value)

        latency = CommonUtils.get_value_from_spec_row(spec_rows, "Opóźnienie")

        spec_value = CommonUtils.get_value_from_spec_row(spec_rows, "Częstotliwość pracy [MHz]")
        lighting = CommonUtils.translate_to_bool(spec_value)

        return RAM(product.get_name(), product.get_producer(), product.get_category(), product.get_description(),
                   product.get_price(), product.get_producer_code(), line, memory_type, total_capacity,
                   number_of_modules, frequency, latency, lighting)

    def parse_motherboard(self, product, spec_rows):
        """
        Pobiera i przetwarza dane z adresu URL na obiekt klasy ``Motherboard``
        :param product: obiekt klasy ``Product``, zawierający uniwersalne dane dla wszystkich typów produktów
        :param spec_rows: wiersze tabeli specyfikacji
        :return: obiekt klasy ``Motherboard``
        """
        product.set_category(str(ProductCategory.MB))
        product.set_description(self.get_product_description(product.get_name()))
        standard = CommonUtils.get_value_from_spec_row(spec_rows, "Standard płyty")
        chipset = CommonUtils.get_value_from_spec_row(spec_rows, "Chipset płyty")
        cpu_socket = CommonUtils.get_value_from_spec_row(spec_rows, "Gniazdo procesora")
        memory_standard = CommonUtils.get_value_from_spec_row(spec_rows, "Standard pamięci")

        spec_value = CommonUtils.get_value_from_spec_row(spec_rows, "Liczba slotów pamięci")
        number_of_memory_slots = CommonUtils.extract_int(spec_value)

        spec_value = CommonUtils.get_value_from_spec_row(spec_rows, "Częstotliwości pracy pamięci")
        str_freq_list = []
        frequencies: list[int] = []
        if spec_value is not None:
            str_freq_list = spec_value.replace("\n", "").split(",")
        if len(str_freq_list) > 0:
            for freq in str_freq_list:
                frequencies.append(CommonUtils.extract_int(freq))

        spec_value = CommonUtils.get_value_from_spec_row(spec_rows, "Maksymalna ilość pamięci")
        max_memory_capacity = CommonUtils.extract_int(spec_value)

        integrated_audio_card = CommonUtils.get_value_from_spec_row(spec_rows, "Chipset dźwiękowy")

        spec_value = CommonUtils.get_value_from_spec_row(spec_rows, "Kanały audio")
        audio_channels = CommonUtils.extract_float(spec_value)

        integrated_network_card = CommonUtils.get_value_from_spec_row(spec_rows, "Zintegrowana karta sieciowa")
        wireless = CommonUtils.get_value_from_spec_row(spec_rows, "Praca bezprzewodowa")
        bluetooth = False
        wifi = False
        if CommonUtils.translate_to_bool(wireless):
            bluetooth = CommonUtils.translate_to_bool(wireless, custom_str_key="Bluetooth")
            wifi = CommonUtils.translate_to_bool(wireless, custom_str_key="Wi-Fi")

        spec_value = CommonUtils.get_value_from_spec_row(spec_rows, "Gniazda rozszerzeń")
        expansion_slots = []
        if spec_value is not None:
            expansion_slots = spec_value.replace("\n", "").split(",")

        spec_value = CommonUtils.get_value_from_spec_row(spec_rows, "Złącza napędów")
        drive_interfaces = []
        if spec_value is not None:
            drive_interfaces = spec_value.replace("\n", "").split(",")

        spec_value = CommonUtils.get_value_from_spec_row(spec_rows, "Panel tylny")
        outside_connectors = []
        if spec_value is not None:
            outside_connectors = spec_value.replace("\n", "").split(",")

        spec_value = CommonUtils.get_value_from_spec_row(spec_rows, "Szerokość [mm]")
        width = CommonUtils.extract_float(spec_value)

        spec_value = CommonUtils.get_value_from_spec_row(spec_rows, "Głębokość [mm]")
        depth = CommonUtils.extract_float(spec_value)

        return Motherboard(product.get_name(), product.get_producer(), product.get_category(),
                           product.get_description(), product.get_price(), product.get_producer_code(),
                           standard, chipset, cpu_socket, memory_standard, number_of_memory_slots, frequencies,
                           max_memory_capacity, integrated_audio_card, audio_channels, integrated_network_card,
                           bluetooth, wifi, expansion_slots, drive_interfaces, outside_connectors, width, depth)

    def parse_gpu(self, product, spec_rows):
        """
        Pobiera i przetwarza dane z adresu URL na obiekt klasy ``GraphicsCard``
        :param product: obiekt klasy ``Product``, zawierający uniwersalne dane dla wszystkich typów produktów
        :param spec_rows: wiersze tabeli specyfikacji
        :return: obiekt klasy ``GraphicsCard``
        """
        product.set_category(str(ProductCategory.GPU))
        temp_name_list = product.get_name().split(" ")
        product.set_name("")
        if len(temp_name_list) > 0:
            for word in temp_name_list:
                if "GB" in word or "MB" in word:
                    break
                else:
                    product.set_name(product.get_name() + " " + word)
        product.set_name(product.get_name().strip())

        product.set_description(self.get_product_description(product.get_name()))

        chipset_producer: str = CommonUtils.get_value_from_spec_row(spec_rows, "Producent chipsetu")
        chipset: str = CommonUtils.get_value_from_spec_row(spec_rows, "Rodzaj chipsetu")

        spec_value = CommonUtils.get_value_from_spec_row(spec_rows, "Taktowanie rdzenia")
        core_frequency: int = CommonUtils.extract_int(spec_value)

        spec_value = CommonUtils.get_value_from_spec_row(spec_rows, "Taktowanie rdzenia w trybie boost")
        max_core_frequency: int = CommonUtils.extract_int(spec_value)

        spec_value = CommonUtils.get_value_from_spec_row(spec_rows, "Procesory strumieniowe")
        stream_processors: int = CommonUtils.extract_int(spec_value)

        spec_value = CommonUtils.get_value_from_spec_row(spec_rows, "Jednostki ROP")
        rop_units: int = CommonUtils.extract_int(spec_value)

        spec_value = CommonUtils.get_value_from_spec_row(spec_rows, "Jednostki teksturujące")
        texturing_units: int = CommonUtils.extract_int(spec_value)

        spec_value = CommonUtils.get_value_from_spec_row(spec_rows, "Rdzenie RT")
        rt_cores: int = CommonUtils.extract_int(spec_value)

        spec_value = CommonUtils.get_value_from_spec_row(spec_rows, "Rdzenie Tensor")
        tensor_cores: int = CommonUtils.extract_int(spec_value)

        dlss: str = CommonUtils.get_value_from_spec_row(spec_rows, "DLSS")
        connector: str = CommonUtils.get_value_from_spec_row(spec_rows, "Typ złącza")

        spec_value = CommonUtils.get_value_from_spec_row(spec_rows, "Długość karty")
        card_length: int = CommonUtils.extract_int(spec_value)

        resolution: str = CommonUtils.get_value_from_spec_row(spec_rows, "Rozdzielczość")

        spec_value = CommonUtils.get_value_from_spec_row(spec_rows, "Rekomendowana moc zasilacza")
        recommended_ps: int = CommonUtils.extract_int(spec_value)

        spec_value = CommonUtils.get_value_from_spec_row(spec_rows, "Podświetlenie LED")
        lightning: bool = CommonUtils.translate_to_bool(spec_value)

        spec_value = CommonUtils.get_value_from_spec_row(spec_rows, "Ilość pamięci RAM")
        ram: int = CommonUtils.extract_int(spec_value)

        ram_type: str = CommonUtils.get_value_from_spec_row(spec_rows, "Rodzaj pamięci RAM")

        spec_value = CommonUtils.get_value_from_spec_row(spec_rows, "Szyna danych")
        data_bus: int = CommonUtils.extract_int(spec_value)

        spec_value = CommonUtils.get_value_from_spec_row(spec_rows, "Taktowanie pamięci")
        memory_freq: int = CommonUtils.extract_int(spec_value)

        cooling_type: str = CommonUtils.get_value_from_spec_row(spec_rows, "Typ chłodzenia")

        spec_value = CommonUtils.get_value_from_spec_row(spec_rows, "Ilość wentylatorów")
        number_of_fans: int = CommonUtils.extract_int(spec_value)

        return GraphicsCard(product.get_name(), product.get_producer(), product.get_category(),
                            product.get_description(), product.get_price(), product.get_producer_code(),
                            chipset_producer, chipset, core_frequency, max_core_frequency,
                            stream_processors, rop_units, texturing_units, rt_cores, tensor_cores,
                            dlss, connector, card_length, resolution, recommended_ps, lightning,
                            ram, ram_type, data_bus, memory_freq, cooling_type, number_of_fans)

    def parse_power_supply(self, product, spec_rows):
        """
       Pobiera i przetwarza dane z adresu URL na obiekt klasy ``GraphicsCard``
       :param product: obiekt klasy ``Product``, zawierający uniwersalne dane dla wszystkich typów produktów
       :param spec_rows: wiersze tabeli specyfikacji
       :return: obiekt klasy ``GraphicsCard``
       """
        product.set_category(str(ProductCategory.POWER_SUPPLY))
        temp_name_list = product.get_name().split(" ")
        product.set_name("")
        if len(temp_name_list) > 0:
            for word in temp_name_list:
                if "W" in word and word[0].isdigit():
                    break
                else:
                    product.set_name(product.get_name() + " " + word)
        product.set_name(product.get_name().strip())

        product.set_description(self.get_product_description(product.get_name()))

        standard: str = CommonUtils.get_value_from_spec_row(spec_rows, "Standard/Format")

        spec_value = CommonUtils.get_value_from_spec_row(spec_rows, "Moc")
        power: int = CommonUtils.extract_int(spec_value)

        efficiency_certificate: str = CommonUtils.get_value_from_spec_row(spec_rows, "Certyfikat sprawności")

        spec_value = CommonUtils.get_value_from_spec_row(spec_rows, "Sprawność")
        efficiency: int = CommonUtils.extract_int(spec_value)

        cooling_type: str = CommonUtils.get_value_from_spec_row(spec_rows, "Typ chłodzenia")

        spec_value = CommonUtils.get_value_from_spec_row(spec_rows, "Średnica wentylatora")
        fan_diameter: int = CommonUtils.extract_int(spec_value)

        spec_value = CommonUtils.get_value_from_spec_row(spec_rows, "Zabezpieczenia").strip().replace("\n", "")
        protections: list[str] = []
        if CommonUtils.translate_to_bool(spec_value):
            protections = spec_value.split(",")

        spec_value = CommonUtils.get_value_from_spec_row(spec_rows, "Modularne okablowanie")
        modular_cabling: bool = CommonUtils.translate_to_bool(spec_value)

        spec_value = CommonUtils.get_value_from_spec_row(spec_rows, "ATX 24-pin (20+4)")
        atx24: int = CommonUtils.extract_int(spec_value)

        spec_value = CommonUtils.get_value_from_spec_row(spec_rows, "PCI-E 16-pin (12+4)")
        pcie16: int = CommonUtils.extract_int(spec_value)

        spec_value = CommonUtils.get_value_from_spec_row(spec_rows, "PCI-E 8-pin (6+2)")
        pcie8: int = CommonUtils.extract_int(spec_value)
        spec_value = CommonUtils.get_value_from_spec_row(spec_rows, "PCI-E 8-pin")
        pcie8 = pcie8 + CommonUtils.extract_int(spec_value)

        spec_value = CommonUtils.get_value_from_spec_row(spec_rows, "PCI-E 6-pin")
        pcie6: int = CommonUtils.extract_int(spec_value)

        spec_value = CommonUtils.get_value_from_spec_row(spec_rows, "CPU 8-pin (4+4)")
        cpu8: int = CommonUtils.extract_int(spec_value)
        spec_value = CommonUtils.get_value_from_spec_row(spec_rows, "CPU 8-pin")
        cpu8 = cpu8 + CommonUtils.extract_int(spec_value)

        spec_value = CommonUtils.get_value_from_spec_row(spec_rows, "CPU 4-pin")
        cpu4: int = CommonUtils.extract_int(spec_value)

        spec_value = CommonUtils.get_value_from_spec_row(spec_rows, "SATA")
        sata: int = CommonUtils.extract_int(spec_value)

        spec_value = CommonUtils.get_value_from_spec_row(spec_rows, "Molex")
        molex: int = CommonUtils.extract_int(spec_value)

        spec_value = CommonUtils.get_value_from_spec_row(spec_rows, "Wysokość [mm]")
        height: int = CommonUtils.extract_int(spec_value)

        spec_value = CommonUtils.get_value_from_spec_row(spec_rows, "Szerokość [mm]")
        width: int = CommonUtils.extract_int(spec_value)

        spec_value = CommonUtils.get_value_from_spec_row(spec_rows, "Głębokość [mm]")
        depth: int = CommonUtils.extract_int(spec_value)

        spec_value = CommonUtils.get_value_from_spec_row(spec_rows, "Podświetlenie")
        lightning: bool = CommonUtils.translate_to_bool(spec_value)

        return PowerSupply(product.get_name(), product.get_producer(), product.get_category(),
                           product.get_description(), product.get_price(), product.get_producer_code(),
                           standard, power, efficiency_certificate, efficiency, cooling_type, fan_diameter, protections,
                           modular_cabling, atx24, pcie16, pcie8, pcie6, cpu8, cpu4, sata, molex, height, width, depth,
                           lightning)
