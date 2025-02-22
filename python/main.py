from util import Util

if __name__ == "__main__":
    base_url = "https://www.morele.net"
    categories = ["/kategoria/dyski-hdd-4/", "/kategoria/dyski-ssd-518/", "/kategoria/karty-dzwiekowe-11/",
                  "/kategoria/karty-graficzne-12/", "/kategoria/napedy-optyczne-28/", "/kategoria/obudowy-33/",
                  "/kategoria/pamieci-ram-38/", "/kategoria/plyty-glowne-42/", "/kategoria/procesory-45/",
                  "/kategoria/zasilacze-komputerowe-61/", "/kategoria/chlodzenie-cpu-633/",
                  "/kategoria/chlodzenie-wodne-akcesoria-1472/", "/kategoria/chlodzenie-wodne-zestawy-662/",
                  "/kategoria/pasty-termoprzewodzace-1292/", "/kategoria/termopady-12300/",
                  "/kategoria/wentylatory-komputerowe-60/", "/kategoria/akcesoria-do-konsol-i-gamepadow-74/",
                  "/kategoria/akcesoria-do-streamingu-434/", "/kategoria/biurka-gamingowe-740/",
                  "/kategoria/fotele-gamingowe-747/", "/kategoria/gogle-vr-1436/", "/kategoria/joysticki-709/",
                  "/kategoria/keycaps-12236/", "/kategoria/kierownice-116/", "/kategoria/klawiatury-gamingowe-465/",
                  "/kategoria/mouse-bungee-1344/", "/kategoria/myszki-gamingowe-27/",
                  "/kategoria/oswietlenie-gamingowe-12475/", "/kategoria/pady-10/",
                  "/kategoria/podkladki-gamingowe-467/", "/kategoria/sluchawki-gamingowe-466/",
                  "/kategoria/stojaki-na-sluchawki-12297/", "/kategoria/akcesoria-do-monitorow-225/",
                  "/kategoria/filtry-na-ekrany-210/", "/kategoria/kable-av-194/", "/kategoria/monitory-523/",
                  "/kategoria/srodki-czyszczace-do-elektroniki-209/", "/kategoria/uchwyty-do-monitorow-720/"]
    for category in categories:
        util = Util(base_url, category)
        print("-------", category, "---------")
        util.find_products("a", "productLink", "li", "pagination-lg next")
        for product in util.all_links:
            print(product)
        print(len(util.all_links))
