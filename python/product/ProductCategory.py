from enum import StrEnum


class ProductCategory(StrEnum):
    """
    Klasa enumeracyjna kodów produktów.
    :param CASE: kod obudowy komputerowej
    :param AIR_COOLER: kod chłodzenia powietrznego do procesora
    :param LIQUID_COOLER: kod chłodzenia cieczą do procesora
    :param GPU: kod karty graficznej
    :param SSD: kod dysku Solid State Drive
    :param HDD: kod dysku Hard Disk Drive
    :param MB: kod płyty głównej
    :param POWER_SUPPLY: kod zasilacza komputerowego
    :param CPU: kod procesora
    :param RAM: kod pamięci RAM
    """
    CASE = "CASE"
    AIR_COOLER = "AIR_COOLER"
    LIQUID_COOLER = "LIQUID_COOLER"
    GPU = "GPU"
    SSD = "SSD"
    HDD = "HDD"
    MB = "MB"
    POWER_SUPPLY = "PS"
    CPU = "CPU"
    RAM = "RAM"


class UrlCategory(StrEnum):
    """
    Klasa enumeracyjna zawierająca linki do kategorii produktów.
    :param CASE: adres URL do kategorii ``Procesory`` na stronie https://www.morele.net
    :param AIR_COOLER: adres URL do kategorii ``Pamięci RAM`` na stronie https://www.morele.net
    :param LIQUID_COOLER: adres URL do kategorii ``Płyty główne`` na stronie https://www.morele.net
    :param GPU: adres URL do kategorii ``Karty graficzne`` na stronie https://www.morele.net
    :param SSD: adres URL do kategorii ``Zasilacze komputerowe ATX`` na stronie https://www.morele.net
    :param HDD: adres URL do kategorii ``Obudowy`` na stronie https://www.morele.net
    :param MB: adres URL do kategorii ``Chłodzenie CPU`` na stronie https://www.morele.net
    :param POWER_SUPPLY: adres URL do kategorii ``Chłodzenie wodne - zestawy Do procesora`` na stronie https://www.morele.net
    :param CPU: adres URL do kategorii ``Dyski SSD`` na stronie https://www.morele.net
    :param RAM: adres URL do kategorii ``Dyski HDD`` na stronie https://www.morele.net
    """
    CPU = "https://www.morele.net/kategoria/procesory-45/"
    RAM = "https://www.morele.net/kategoria/pamieci-ram-38/"
    MB = "https://www.morele.net/kategoria/plyty-glowne-42/"
    GPU = "https://www.morele.net/kategoria/karty-graficzne-12/"
    POWER_SUPPLY = "https://www.morele.net/kategoria/zasilacze-komputerowe-61/,,,,,,,p,0,,,,33766O2213324.1171191.2158469.1678864.1171192.2213239.1171190.2155839.2168092.2161810/1/"
    CASE = "https://www.morele.net/kategoria/obudowy-33/"
    AIR_COOLER = "https://www.morele.net/kategoria/chlodzenie-cpu-633/"
    LIQUID_COOLER = "https://www.morele.net/kategoria/chlodzenie-wodne-zestawy-662/,,,,,,,,0,,,,54492O1853767/1/"
    SSD = "https://www.morele.net/kategoria/dyski-ssd-518/"
    HDD = "https://www.morele.net/kategoria/dyski-hdd-4/"
