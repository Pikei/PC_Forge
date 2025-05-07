package com.pc_forge.backend.controller.api.constants;

/**
 * Klasa przechowująca stałe nazwy dostępnych parametrów w request
 */
public class RequestParams {

    /**
     * Parametr określający kategorię produktu
     */
    public static final String PRODUCT_CATEGORY = "category";

    /**
     * Parametr określający minimalną cenę produktu
     */
    public static final String PRICE_MINIMUM = "min_price";

    /**
     * Parametr określający maksymalną cenę produktu
     */
    public static final String PRICE_MAXIMUM = "max_price";

    /**
     * Parametr określający producenta
     */
    public static final String PRODUCER = "producer";

    /**
     * Parametr określający gniazdo (socket) procesora lub płyty głównej
     */
    public static final String SOCKET = "socket";

    /**
     * Parametr określający model procesora produktu
     */
    public static final String MODEL = "model";

    /**
     * Parametr określający liczbę rdzeni procesora
     */
    public static final String NUMBER_OF_CORES = "num_cores";

    /**
     * Parametr określający częstotliwość procesora/pamięci RAM lub wspieranej częstotliwości przez płytę główną
     */
    public static final String FREQUENCY = "freq";

    /**
     * Parametr określający, czy procesor posiada zintegrowany układ graficzny
     */
    public static final String INTEGRATED_GRAPHICS_UNIT = "igu";

    /**
     * Parametr określający typ opakowania procesora
     */
    public static final String PACKAGING_TYPE = "pack";

    /**
     * Parametr określający, czy rdzenie procesora są odblokowane
     */
    public static final String CORE_UNLOCKED = "unlocked";

    /**
     * Parametr określający, czy chłodzenie jest dołączone do procesora
     */
    public static final String COOLER_INCLUDED = "cooler";

    /**
     * Parametr określający standard płyty głównej (np. ATX, Micro-ATX)
     */
    public static final String MOTHERBOARD_STANDARD = "standard";

    /**
     * Parametr określający chipset płyty głównej lub karty graficznej
     */
    public static final String CHIPSET = "chipset";

    /**
     * Parametr określający typ pamięci RAM (np. DDR4, DDR5)
     */
    public static final String RAM_TYPE = "ram_type";

    /**
     * Parametr określający liczbę gniazd na pamięć RAM na płycie głównej
     */
    public static final String RAM_SLOTS = "ram_slots";

    /**
     * Parametr określający maksymalną pojemność (lub maksymalną pojemność) pamięci RAM
     */
    public static final String RAM_CAPACITY = "ram_cap";

    /**
     * Parametr określający, czy płyta główna posiada moduł Bluetooth
     */
    public static final String BLUETOOTH = "bt";

    /**
     * Parametr określający, czy płyta główna posiada moduł Wi-Fi
     */
    public static final String WIFI = "wifi";

    /**
     * Parametr określający minimalną szerokość
     */
    public static final String WIDTH_MINIMUM = "min_width";

    /**
     * Parametr określający maksymalną szerokość
     */
    public static final String WIDTH_MAXIMUM = "max_width";

    /**
     * Parametr określający minimalną wysokość
     */
    public static final String HEIGHT_MINIMUM = "min_height";

    /**
     * Parametr określający maksymalną wysokość
     */
    public static final String HEIGHT_MAXIMUM = "max_height";

    /**
     * Parametr określający minimalną głębokość
     */
    public static final String DEPTH_MINIMUM = "min_depth";

    /**
     * Parametr określający maksymalną głębokość
     */
    public static final String DEPTH_MAXIMUM = "max_depth";

    /**
     * Parametr określający minimalną długość
     */
    public static final String LENGTH_MINIMUM = "min_length";

    /**
     * Parametr określający maksymalną długość
     */
    public static final String LENGTH_MAXIMUM = "max_length";

    /**
     * Parametr określający liczbę modułów pamięci RAM
     */
    public static final String NUMBER_OF_MODULES = "modules";

    /**
     * Parametr określający opóźnienie pamięci RAM
     */
    public static final String LATENCY = "latency";

    /**
     * Parametr określający, czy produkt posiada podświetlenie
     */
    public static final String LIGHTNING = "light";

    /**
     * Parametr określający producenta chipsetu karty graficznej
     */
    public static final String CHIPSET_PRODUCER = "chipset_prod";

    /**
     * Parametr określający, czy karta graficzna wspiera technologię DLSS
     */
    public static final String DLSS = "dlss";

    /**
     * Parametr określający typ złącza karty graficznej
     */
    public static final String CONNECTOR = "conn";

    /**
     * Parametr określający maksymalną obsługiwaną rozdzielczość
     */
    public static final String RESOLUTION = "res";

    /**
     * Parametr określający typ chłodzenia karty graficznej
     */
    public static final String COOLING_TYPE = "cooling_type";

    /**
     * Parametr określający liczbę wentylatorów
     */
    public static final String NUMBER_OF_FANS = "fans";

    /**
     * Parametr określający średnicę wentylatora
     */
    public static final String FAN_DIAMETER = "fan_diameter";

    /**
     * Parametr określający moc zasilacza
     */
    public static final String PS_POWER = "power";

    /**
     * Parametr określający certyfikat sprawności zasilacza
     */
    public static final String CERTIFICATE = "cert";

    /**
     * Parametr określający procentową sprawność zasilacza
     */
    public static final String EFFICIENCY = "efficiency";

    /**
     * Parametr określający zabezpieczenia zasilacza
     */
    public static final String PROTECTIONS = "protections";

    /**
     * Parametr określający, czy zasilacz posiada modularne okablowanie
     */
    public static final String MODULAR_CABLING = "modular";

    /**
     * Parametr określający kolor obudowy
     */
    public static final String COLOR = "color";

    /**
     * Parametr określający typ obudowy
     */
    public static final String CASE_TYPE = "case_type";

    /**
     * Parametr określający, czy obudowa posiada okno
     */
    public static final String HAS_WINDOW = "window";

    /**
     * Parametr określający, czy obudowa posiada zintegrowany zasilacz
     */
    public static final String HAS_POWER_SUPPLY = "power_supply";

    /**
     * Parametr określający maksymalną długość karty graficznej obsługiwaną przez obudowę
     */
    public static final String MAX_GPU_LENGTH = "max_gpu_length";

    /**
     * Parametr określający maksymalną wysokość chłodzenia CPU obsługiwaną przez obudowę
     */
    public static final String MAX_CPU_HEIGHT = "max_cpu_height";

    /**
     * Parametr określający wentylatory z przodu obudowy
     */
    public static final String FRONT_FANS = "front_fans";

    /**
     * Parametr określający wentylatory z tyłu obudowy
     */
    public static final String BACK_FANS = "back_fans";

    /**
     * Parametr określający wentylatory z boku obudowy
     */
    public static final String SIDE_FANS = "side_fans";

    /**
     * Parametr określający wentylatory na dole obudowy
     */
    public static final String BOTTOM_FANS = "bottom_fans";

    /**
     * Parametr określający wentylatory na górze obudowy
     */
    public static final String TOP_FANS = "top_fans";

    /**
     * Parametr określający liczbę portów USB 2.0
     */
    public static final String USB_20 = "usb20";

    /**
     * Parametr określający liczbę portów USB 3.0
     */
    public static final String USB_30 = "usb30";

    /**
     * Parametr określający liczbę portów USB 3.1
     */
    public static final String USB_31 = "usb31";

    /**
     * Parametr określający liczbę portów USB 3.2
     */
    public static final String USB_32 = "usb32";

    /**
     * Parametr określający liczbę portów USB-C
     */
    public static final String USB_C = "usbC";

    /**
     * Parametr określający format dysku
     */
    public static final String FORMAT = "format";

    /**
     * Parametr określający interfejs dysku
     */
    public static final String INTERFACE = "interface";

    /**
     * Parametr określający pojemność dysku
     */
    public static final String STORAGE = "storage";

    /**
     * Parametr określający prędkość odczytu dysku
     */
    public static final String READ_SPEED = "read_speed";

    /**
     * Parametr określający prędkość zapisu dysku
     */
    public static final String WRITE_SPEED = "write_speed";

    /**
     * Parametr określający prędkość obrotową dysku HDD
     */
    public static final String ROTATIONAL_SPEED = "rotational_speed";

    /**
     * Parametr określający poziom hałasu generowany przez chłodzenie
     */
    public static final String NOISE_LEVEL = "noise_lvl";

    /**
     * Parametr określający materiał podstawy chłodzenia
     */
    public static final String BASE_MATERIAL = "base";

    /**
     * Parametr określający, czy chłodzenie umożliwia montaż pionowy
     */
    public static final String VERTICAL_INSTALLATION = "vert";

    /**
     * Parametr określający rozmiar chłodnicy w chłodzeniu wodnym
     */
    public static final String COOLER_SIZE = "cooler_size";

    /**
     * Parametr określający token JWT
     */
    public static final String TOKEN = "token";

    /**
     * Parametr określający identyfikator zamówienia
     */
    public static final String ORDER_ID = "order_ID";

    /**
     * Parametr określający identyfikator sesji płatności
     */
    public static final String SESSION_ID = "session_id";

}
