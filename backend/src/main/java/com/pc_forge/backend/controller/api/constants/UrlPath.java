package com.pc_forge.backend.controller.api.constants;

/**
 * Klasa przechowująca stałe ścieżki URL API.
 */
public class UrlPath {

    /**
     * Ścieżka bazowa filtrowania produktów
     */
    public static final String FILTER = "/filter";

    /**
     * Ścieżka bazowa dla operacji pobrania danych o produktach w danej kategorii
     */
    public static final String CATEGORY = "/category";

    /**
     * Ścieżka dla operacji wyszukiwania produktów.
     */
    public static final String SEARCH = "/search";

    /**
     * Ścieżka bazowa dla ogólnych operacji na produktach.
     */
    public static final String PRODUCT = "/product";

    /**
     * Ścieżka do logowania użytkownika
     */
    public static final String LOGIN = "/login";

    /**
     * Ścieżka do rejestracji nowego użytkownika
     */
    public static final String REGISTER = "/register";

    /**
     * Ścieżka do weryfikacji konta użytkownika
     */
    public static final String VERIFY = "/verify";

    /**
     * Ścieżka do pobierania danych profilu zalogowanego użytkownika
     */
    public static final String PROFILE = "/profile";

    /**
     * Ścieżka do usuwania konta użytkownika
     */
    public static final String DELETE_ACCOUNT = "/delete_account";

    /**
     * Ścieżka do inicjalizacji procesu resetowania hasła
     */
    public static final String PASSWORD_FORGOT = "/password/forgot";

    /**
     * Ścieżka do finalizacji procesu resetowania hasła
     */
    public static final String PASSWORD_RESET = "/password/reset";

    /**
     * Ścieżka bazowa dla operacji na konfiguracjach komputerów użytkownika
     */
    public static final String CONFIGURATIONS = "/configurations";

    /**
     * Ścieżka do pobierania wszystkich zasobów danego typu (np. wszystkich konfiguracji, wszystkich zamówień)
     */
    public static final String ALL = "/all";

    /**
     * Ścieżka do operacji na pojedynczej konfiguracji
     */
    public static final String CONFIG = "/config";

    /**
     * Ścieżka do dodawania produktów z konfiguracji do koszyka.
     */
    public static final String ADD_TO_CART = "/add_to_cart";

    /**
     * Ścieżka do usuwania elementu (np. produktu z koszyka, konfiguracji).
     */
    public static final String REMOVE = "/remove";

    /**
     * Ścieżka do zapisywania konfiguracji
     */
    public static final String SAVE = "/save";

    /**
     * Ścieżka do sprawdzenia, czy konfiguracja już istnieje. Stosowana w celu upewnienia się,
     * czy użytkownik chce nadpisać zapisaną konfigurację, czy nie.
     */
    public static final String CHECK_IF_EXISTS = "/exists";

    /**
     * Ścieżka bazowa dla operacji na zamówieniach
     */
    public static final String ORDER = "/order";

    /**
     * Ścieżka do tworzenia nowego zamówienia
     */
    public static final String NEW = "/new";

    /**
     * Ścieżka używana do anulowania zamówienia lub jako URL zwrotny po anulowanej płatności
     */
    public static final String CANCEL = "/cancel";

    /**
     * Ścieżka bazowa dla operacji związanych z płatnościami
     */
    public static final String PAYMENT = "/payment";

    /**
     * Ścieżka używana po pomyślnym zakończeniu płatności (URL zwrotny)
     */
    public static final String SUCCESS = "/success";

    /**
     * Ścieżka bazowa dla operacji na koszyku
     */
    public static final String SHOPPING_CART = "/cart";

    /**
     * Ścieżka do dodawania produktu do koszyka
     */
    public static final String ADD = "/add";

    /**
     * Ścieżka do czyszczenia zawartości koszyka
     */
    public static final String CLEAR = "/clear";

    public static final String FRONT_ORDER = "/home";

}
