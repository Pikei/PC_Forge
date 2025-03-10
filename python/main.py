from util import SpecsUtil
if __name__ == "__main__":
    morele_main = "https://www.morele.net/"
    # categories = {"kategoria/dyski-hdd-4/", "kategoria/dyski-ssd-518/", "kategoria/karty-graficzne-12/",
    #               "kategoria/obudowy-33/", "kategoria/pamieci-ram-38/", "kategoria/plyty-glowne-42/",
    #               "kategoria/procesory-45/", "kategoria/zasilacze-komputerowe-61/"}
    morele_cat_url = {"kategoria/procesory-45/"}
    for category in morele_cat_url:
        print(category)
        util = SpecsUtil(morele_main + category)
        links = util.find_products()
        for link in links:
            cpu = util.parse_cpu(link)
