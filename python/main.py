from util import Util

if __name__ == "__main__":
    url = "https://www.morele.net/"
    categories = {"kategoria/dyski-hdd-4/", "kategoria/dyski-ssd-518/", "kategoria/karty-graficzne-12/",
                  "kategoria/obudowy-33/", "kategoria/pamieci-ram-38/", "kategoria/plyty-glowne-42/",
                  "kategoria/procesory-45/", "kategoria/zasilacze-komputerowe-61/"}
    for category in categories:
        print(category)
        util = Util(url + category)
        links = util.find_products()
        for link in links:
            print(link)
