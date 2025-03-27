import os
import re
from collections import OrderedDict
from selenium.webdriver.common.by import By
from selenium.webdriver.remote.webelement import WebElement


class CommonUtils:
    """
    Klasa zawierająca statyczne metody, które dostarczają konkretne funkcjonalności
    """
    @staticmethod
    def directory_exists(directory_name: str):
        """
        Tworzy katalog o podanej nazwie w katalogu roboczym, jeśli ten jeszcze nie istnieje
        :param directory_name: Nazwa katalogu, który ma zostać utworzony
        :return: **True** jeśli katalog już istnieje lub właśnie został utworzony, **False* w przeciwnym razie
        """
        try:
            os.mkdir(directory_name)
            print(f"Directory '{directory_name}' created successfully.")
            return True
        except FileExistsError:
            print(f"Directory '{directory_name}' already exists.")
            return True
        except Exception as e:
            print(f"An error occurred: {e}")
            return False

    @staticmethod
    def get_value_from_spec_row(rows, param_name):
        """
        Metoda służąca do pobierania konkretnej wartości z tabeli specyfikacji produktu.
        :param rows: Wiersze tabeli specyfikacji
        :param param_name: Nazwa pożądanego parametru ze specyfikacji
        :return: Wartość w tabeli specyfikacji w wierszu gdzie nazwa parametru jest taka sama jak przekazany parametr **param_name**
        """
        for row in rows:
            if row.find_element(By.CLASS_NAME, "specification__name").text == param_name:
                return row.find_element(By.CLASS_NAME, "specification__value").text
        return ""

    @staticmethod
    def is_digit(c: chr):
        """
        Sprawdza, czy podany znak jest cyfrą
        :param c: Znak
        :return: **True** jeśli przekazany parametr jest cyfrą **False** w przeciwnym razie
        """
        try:
            int(c)
            return True
        except ValueError:
            return False

    @staticmethod
    def extract_float(s: str):
        """
        Pobiera liczbę zmiennoprzecinkową z przekazanego ciągu znaków
        :param s: Ciąg znaków, z którego ma być pobrana liczba zmiennoprzecinkowa.
        :return: Jeśli w przekazanym ciągu znaków znaleziono liczbę zmiennoprzecinkową, zwraca ją.
        W przeciwnym razie zwraca 0,0
        """
        res = ""
        s = s.replace(",", ".")
        point_found = False
        for char in s:
            if CommonUtils.is_digit(char):
                res = res + char
                continue
            elif char == "." and not point_found:
                res = res + char
                point_found = True
                continue
            elif point_found and not CommonUtils.is_digit(char):
                break
        if len(res) > 0:
            return float(res)
        return 0.0

    @staticmethod
    def extract_int(s: str):
        """
        Pobiera liczbę całkowitą z przekazanego ciągu znaków
        :param s: Ciąg znaków, z którego ma być pobrana liczba całkowita.
        :return: Jeśli w przekazanym ciągu znaków znaleziono liczbę całkowitą, zwraca ją w przeciwnym razie zwraca 0
        """
        res = ""
        num_found = False
        for char in s:
            if CommonUtils.is_digit(char):
                num_found = True
                res = res + char
            elif not num_found:
                continue
            else:
                break
        if len(res) > 0:
            return int(res)
        return 0

    @staticmethod
    def parse_to_html(element: WebElement):
        """
        Obudowuje tekst w znaczniki HTML
        :param element: element znajdujący się na stronie
        :return: Tekst znajdujący się w danym elemencie wraz ze znacznikami HTML
        """
        return "<" + element.tag_name + ">" + element.text + "</" + element.tag_name + ">"

    @staticmethod
    def add_par_and_list(paragraphs: list[WebElement], lists: list[WebElement]):
        """
        Metoda służąca do pozyskiwania list i paragrafów w opisie na stronie, w odpowiedniej kolejności.
        Kolejność elementów determinowana jest na podstawie pozycji w osi Y na stronie.
        :param paragraphs: Lista paragrafów w kodzie HTML w danym wierszu opisu
        :param lists: Lista list w kodzie HTML w danym wierszu opisu
        :return: Ciąg znaków w zapisie HTML z elementami zapisanymi w odpowiedniej kolejności.
        """
        res = ""
        position = {}
        for p in paragraphs:
            position[p.location['y']] = p
        for l in lists:
            position[l.location['y']] = l

        position = OrderedDict(sorted(position.items()))

        for line in position.values():
            if line.tag_name == "p":
                res += CommonUtils.parse_to_html(line)
            elif line.tag_name == "ul":
                res += "<" + line.tag_name + ">"
                for li in line.find_elements(By.TAG_NAME, "li"):
                    res += CommonUtils.parse_to_html(li)
                res += "</" + line.tag_name + ">"
        return res

    @staticmethod
    def translate_to_bool(s: str):
        """
        Metoda sprawdzająca, czy w podanym ciągu znaków znajduje się słowo kluczowe "Tak", "Nie" lub "N ignorując przy tym wielkość liter.
        :param s: Ciąg znaków do sprawdzenia, czy zawiera słowo "Tak"
        :return: **True** jeśli w ciągu znaków znajduje się słowo "Tak", **False** w przeciwnym razie
        """
        if re.search(s, "tak", re.IGNORECASE):
            return True
        if re.search(s, "nie", re.IGNORECASE):
            return False
        if re.search(s, "brak", re.IGNORECASE):
            return False
        return True
