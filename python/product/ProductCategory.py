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
    MB = "https://www.morele.net/kategoria/plyty-glowne-42/"

    # CASE = "kategoria/obudowy-33/"
    # # COOLER =
    # GPU = "kategoria/karty-graficzne-12/"
    # SSD = "kategoria/dyski-ssd-518/"
    # HDD = "kategoria/dyski-hdd-4/"
    # POWER_SUPPLY = "kategoria/zasilacze-komputerowe-61/"
    # CPU = "kategoria/procesory-45/"
