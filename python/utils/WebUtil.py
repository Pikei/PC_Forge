import os
from time import sleep
from selenium import webdriver
from selenium.common import NoSuchElementException, ElementNotInteractableException, TimeoutException, \
    ElementClickInterceptedException
from selenium.webdriver import ActionChains
from selenium.webdriver.common.by import By
from selenium.webdriver.remote.webelement import WebElement
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as ec

from utils.CommonUtils import CommonUtils


class WebUtil:
    """
    Klasa zawierająca metody odpowiedzialne za pobieranie i interakcję z elementami na stronie
    """

    def __init__(self, driver: webdriver.Chrome):
        """
        Konstruktor klasy ``WebUtil``
        :param driver: Obiekt klasy WebDriver, odpowiedzialny za ładowanie strony i elementów znajdujących się na niej.
        """
        self.driver = driver
        self.__cookies_accepted = False
        self.all_links = []

    def get_element(self, by: By, selector: str, driver=None, mandatory=True, timeout=10):
        """
        Metoda ta zastępuje ``WebDriver.find_element()``.
        Próbuje zlokalizować element na załadowanej stronie, czekając przez określony czas,
        jeśli poszukiwany element jest wymagany (przykładowo nazwa produktu). Jednocześnie optymalizuje czas wykonania
        podczas poszukiwania elementu, który może się pojawić na stronie opcjonalnie (przykładowo przycisk akceptacji plików cookie),
        dzięki czemu program nie czeka na znalezienie elementu na stronie, jeśli ten nie jest wymagany.
        Rozwiązanie to jest przydatne w przypadku niestabilnego łącza internetowego.
        Po zlokalizowaniu elementu na stronie przewija witrynę tak, aby zlokalizowany element był widoczny,
        co minimalizuje ryzyko ukrycia tego elementu pod innym w razie potrzeby późniejszej interakcji z nim.
        Przy wywołaniu należy jednak pamiętać, że jeśli element nie istnieje na załadowanej stronie,
        to metoda zwróci **None**, co spowoduje wyrzucenie wyjątku ``AttributeError``
        w przypadku próby odwołania się do tego elementu.
        :param by: Strategia lokalizowania elementu, np.: ``By.CLASS_NAME``, ``By.CSS_SELECTOR``, ``By.XPATH``
        :param selector: Selektor wykorzystywany do zlokalizowania elementu dla konkretnej strategii
        :param driver: Jest to argument opcjonalny, przekazywany obiekt klasy ``WebDriver`` lub ``WebElement``.
            Służy do zawężenia obszaru poszukiwań elementu. Jeśli nie podano tego parametru przy wywołaniu metody,
            używana jest główna instancja ``WebDriver`` w tej klasie
        :param mandatory: Parametr określający czy poszukiwany element jest kluczowy i
            zawsze powinien znaleźć się na w pełni załadowanej stronie.
            Określa czy metoda ma oczekiwać na pojawienie się elementu. Domyślna wartość to **True**
        :param timeout: Czas oczekiwania na załadowanie wymaganego elementu (domyślnie 60 sek.)
        :return: Obiekt klasy ``WebElement`` jeśli został on zlokalizowany, **None** w przeciwnym razie
        """
        if driver is None:
            driver = self.driver

        actions = ActionChains(driver)
        if mandatory is True:
            try:
                element = WebDriverWait(driver, timeout, poll_frequency=0.2).until(
                    ec.presence_of_element_located((by, selector)))
                actions.scroll_to_element(element).perform()
                actions.move_to_element(element).perform()
                return element
            except TimeoutException:
                return None

        try:
            element = driver.find_element(by, selector)
            try:
                actions.scroll_to_element(element).perform()
                return element
            except AttributeError:
                return element
        except NoSuchElementException:
            return None

    def get_elements(self, by: By, selector: str, driver=None, mandatory=True, timeout=10):
        """
        Metoda ``WebDriver.find_elements()``.
        Próbuje zlokalizować elementy na załadowanej stronie, czekając przez określony czas,
        jeśli poszukiwane elementy są wymagane (przykładowo wiersze specyfikacji produktu).
        Jednocześnie optymalizuje czas wykonania podczas poszukiwania elementów,
        które mogą się pojawić na stronie opcjonalnie (przykładowo listy w opisie),
        dzięki czemu program nie czeka na znalezienie wszystkich elementów na stronie, jeśli te nie są wymagane.
        Rozwiązanie to jest przydatne w przypadku niestabilnego łącza internetowego.
        :param by: Strategia lokalizowania elementów, np.: ``By.CLASS_NAME``, ``By.CSS_SELECTOR``, ``By.XPATH``
        :param selector: Selektor wykorzystywany do zlokalizowania elementów dla konkretnej strategii
        :param driver: Jest to argument opcjonalny, przekazywany obiekt klasy ``WebDriver`` lub ``WebElement``.
            Służy do zawężenia obszaru poszukiwań elementów. Jeśli nie podano tego parametru przy wywołaniu metody,
            używana jest główna instancja ``WebDriver`` w tej klasie
        :param mandatory: Parametr określający czy poszukiwane elementy są kluczowy i
            zawsze powinny znaleźć się na w pełni załadowanej stronie.
            Określa czy metoda ma oczekiwać na pojawienie się wszystkich elementów. Domyślna wartość to **True**
        :param timeout: Czas oczekiwania na załadowanie wymaganych elementów (domyślnie 60 sek.)
        :return: Lista obiektów klasy ``WebElement`` jeśli wszystkie zostały zlokalizowane.
            W przeciwnym razie zwracana jest pusta lista
        """
        if driver is None:
            driver = self.driver

        if mandatory is True:
            try:
                elements = WebDriverWait(driver, timeout, poll_frequency=0.2).until(
                    ec.presence_of_all_elements_located((by, selector)))
                return elements
            except TimeoutException:
                return []

        try:
            elements = driver.find_elements(by, selector)
            return elements
        except NoSuchElementException:
            print(f"Optional elements {selector} not found")
            return []

    def load_page(self, url: str, by: By, selector: str):
        """
        Ładuje stronę, oczekując na pobranie kluczowego elementu.
        Wywołuje metodę ``self.accept_cookies()``, jeśli pliki cookie jeszcze nie zostały zaakceptowane w tej sesji
        :param url: Adres URL strony
        :param by: Strategia lokalizowania elementu, np.: ``By.CLASS_NAME``, ``By.CSS_SELECTOR``, ``By.XPATH``
        :param selector: Selektor wykorzystywany do zlokalizowania elementu dla konkretnej strategii
        :return: **True**, jeśli wszystkie instrukcje zostały wykonane,
            **False** jeśli nie udało się zlokalizować kluczowego elementu
        """
        self.driver.get(url)
        if self.get_element(by, selector) is None: return False

        if not self.__cookies_accepted:
            self.accept_cookies()

        return True

    def accept_cookies(self):
        """
        Znajduje przycisk odpowiedzialny za akceptację plików cookie,
        po czym go klika i ustawia flagę ``self.__cookies_accepted`` na **True**
        """
        cookie_btn = self.get_element(By.CSS_SELECTOR,
                                      'button[data-action="cookie-consent#onApproveAll"].btn--save-all', timeout=5)
        if cookie_btn is not None:
            cookie_btn.click()
            self.__cookies_accepted = True
            print("Cookies accepted")
        else:
            return

    def find_products(self, url):
        """
        Ładuje podstronę z listą produktów w danej kategorii, po czym pobiera adresy URL do produktów.
        Jeśli numer aktualnej strony jest mniejszy niż numer ostatniej strony,
        pobiera link do następnej witryny i ładuje ją, wywołując metodę ``load_page()``, po czym powtarza proces.
        :param url: Adres URL do pierwszej podstrony w danej kategorii produktów
        :return: Lista adresów URL do produktów
        """
        if not self.load_page(url, By.CSS_SELECTOR, "a.productLink"):
            return None
        last_page_num = self.get_element(By.CSS_SELECTOR, "div.pagination-btn-nolink-anchor").text
        category_name = self.get_element(By.CSS_SELECTOR, "span.category-name.main").text
        print("Getting product URLs. Current category: \"" + category_name + "\"")
        product_urls = []
        while True:
            current_page_num = self.get_element(By.CSS_SELECTOR, "li.pagination-lg.btn.active").text
            print("Page:", current_page_num + "/" + last_page_num)
            for link in self.get_elements(By.CSS_SELECTOR, "a.productLink"):
                product_urls.append(link.get_attribute("href"))
            if int(current_page_num) < int(last_page_num):
                url = self.get_element(By.CSS_SELECTOR, "li.pagination-lg.next a.pagination-btn").get_attribute("href")
                self.load_page(url, By.CSS_SELECTOR, "a.productLink")
            else:
                break
        return product_urls

    def expand_description(self):
        """
        Metoda ta próbuje znaleźć przycisk do rozwijania opisu na stronie i jeśli ten istnieje, klika w niego,
        po czym czeka sekundę, dając tym samym czas na zakończenie animacji rozwijania opisu.
        """
        try:
            expand_btn = self.get_element(By.CSS_SELECTOR, "toggle-btn.collapsible-description__btn.btn",
                                          mandatory=False)
            if expand_btn is not None:
                expand_btn.click()
                sleep(1)
        except ElementNotInteractableException:
            return

    def get_description_row(self, row: WebElement, product_name: str):
        """
        Pobiera opis ze strony i buduje z niego kod HTML
        :param row: Pole wiersza opisu
        :param product_name: Nazwa produktu, dodawana jako nagłówek, jeśli w danym wierszu nie znaleziono nagłówka
        :return: Ciąg znaków będący kodem HTML opisu produktu
        """
        if row.text == "":
            return ""
        header = self.get_element(By.TAG_NAME, "h3", row, mandatory=False)
        if header is None:
            content = "<h3>" + product_name + "</h3>"
        else:
            content = CommonUtils.parse_to_html(header)

        paragraphs = self.get_elements(By.TAG_NAME, "p", row, False)
        lists = self.get_elements(By.TAG_NAME, "ul", row, False)
        if len(paragraphs) > 0 and len(lists) > 0:
            CommonUtils.add_par_and_list(paragraphs, lists)
        elif len(paragraphs) > 0:
            for p in paragraphs:
                content = content + CommonUtils.parse_to_html(p)
        elif len(lists) > 0:
            for ul in lists:
                content += "<" + ul.tag_name + ">"
                for li in self.get_elements(By.TAG_NAME, "li", ul):
                    content += CommonUtils.parse_to_html(li)
                content += "</" + ul.tag_name + ">"
        return content

    def save_image(self, category: str, producer: str, producer_code: str):
        """
        Klika w galerię zdjęć produktów, co pozwala na wykonanie zrzutu ekranu bez dodatkowych elementów,
        takich jak przycisk porównania, czy dodania do listy. Zrzut ekranu jest następnie zapisywany w katalogu zdjęć,
        pod nazwą taką samą jak kod producenta. Zapis jest w formacie PNG.
        :param category: Kategoria produktu potrzebna do zapisania zdjęcia w odpowiednim folderze
        :param producer: Nazwa producenta produktu potrzebna do zapisania zdjęcia w odpowiednim folderze
        :param producer_code: Kod producenta, będący nazwą, pod jaką ma zostać zapisany zrzut ekranu
        """
        path = os.path.join(os.getcwd(), "images", category)
        CommonUtils.directory_exists(path)
        path = os.path.join(path, producer)
        CommonUtils.directory_exists(path)
        img_box = self.get_element(By.CSS_SELECTOR, "picture img")
        try:
            img_box.click()
        except ElementClickInterceptedException:
            self.driver.execute_script("window.scrollTo(0, 0)")
            img_box.click()
        img = self.get_element(By.CSS_SELECTOR, "img.mobx-img.mobx-media-loaded")
        path = os.path.join(path, f"{producer_code}.png")
        img.screenshot(path)
        self.get_element(By.CSS_SELECTOR, "button.mobx-close").click()
