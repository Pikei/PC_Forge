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
    CPU = "https://www.morele.net/kategoria/procesory-45/"

    # CASE = "kategoria/obudowy-33/"
    # # COOLER =
    # GPU = "kategoria/karty-graficzne-12/"
    # SSD = "kategoria/dyski-ssd-518/"
    # HDD = "kategoria/dyski-hdd-4/"
    # MB = "kategoria/plyty-glowne-42/"
    # POWER_SUPPLY = "kategoria/zasilacze-komputerowe-61/"
    # CPU = "kategoria/procesory-45/"
    # RAM = "kategoria/pamieci-ram-38/"
