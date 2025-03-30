from enum import StrEnum


class ProductCategory(StrEnum):
    CASE = "CASE"
    COOLER = "COOLER"
    GPU = "GPU"
    SSD = "SSD"
    HDD = "HDD"
    MB = "MB"
    POWER_SUPPLY = "PS"
    CPU = "CPU"
    RAM = "RAM"


class UrlCategory(StrEnum):
    # CPU = "https://www.morele.net/kategoria/procesory-45/"
    # RAM = "https://www.morele.net/kategoria/pamieci-ram-38/"
    # MB = "https://www.morele.net/kategoria/plyty-glowne-42/"
    # GPU = "https://www.morele.net/kategoria/karty-graficzne-12/"
    POWER_SUPPLY = "https://www.morele.net/kategoria/zasilacze-komputerowe-61/,,,,,,,p,0,,,,33766O2213324.1171191.2158469.1678864.1171192.2213239.1171190.2155839.2168092.2161810/1/"

    # CASE = "kategoria/obudowy-33/"
    # # COOLER =
    # SSD = "kategoria/dyski-ssd-518/"
    # HDD = "kategoria/dyski-hdd-4/"
    # CPU = "kategoria/procesory-45/"
