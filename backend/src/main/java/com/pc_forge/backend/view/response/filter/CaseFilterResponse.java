package com.pc_forge.backend.view.response.filter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pc_forge.backend.controller.api.constants.RequestParams;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Finalna klasa DTO filtra obudów komputerowych, dziedzicząca po klasie {@link ProductFilterResponse}.
 * Zawiera dodatkowe pola specyficzne dla filtrowania obudów komputerowych.
 * Obiekty tej klasy służą do zwracania dostępnych opcji filtrowania dla obudów
 * w odpowiedzi na odpowiednie żądania HTTP.
 */
@Getter
@Setter
public final class CaseFilterResponse extends ProductFilterResponse {

    /**
     * Lista par zawierających dostępne kolory obudów i liczbę obudów w danym kolorze.
     */
    @JsonProperty(RequestParams.COLOR)
    private List<Object[]> color;

    /**
     * Lista par zawierających typy obudów (np. ATX, Micro-ATX)
     * i liczbę obudów danego typu.
     */
    @JsonProperty(RequestParams.CASE_TYPE)
    private List<Object[]> caseType;

    /**
     * Lista par zawierających kompatybilne standardy płyt głównych
     * i liczbę obudów obsługujących te standardy.
     */
    @JsonProperty(RequestParams.MOTHERBOARD_STANDARD)
    private List<Object[]> mbStandards;

    /**
     * Minimalna szerokość obudów w milimetrach.
     */
    @JsonProperty(RequestParams.WIDTH_MINIMUM)
    private Double widthMinimum;

    /**
     * Maksymalna szerokość obudów w milimetrach.
     */
    @JsonProperty(RequestParams.WIDTH_MAXIMUM)
    private Double widthMaximum;

    /**
     * Minimalna wysokość obudów w milimetrach.
     */
    @JsonProperty(RequestParams.HEIGHT_MINIMUM)
    private Double heightMinimum;

    /**
     * Maksymalna wysokość obudów w milimetrach.
     */
    @JsonProperty(RequestParams.HEIGHT_MAXIMUM)
    private Double heightMaximum;

    /**
     * Minimalna głębokość obudów w milimetrach.
     */
    @JsonProperty(RequestParams.DEPTH_MINIMUM)
    private Double depthMinimum;

    /**
     * Maksymalna głębokość obudów w milimetrach.
     */
    @JsonProperty(RequestParams.DEPTH_MAXIMUM)
    private Double depthMaximum;

    /**
     * Lista par wskazujących obecność okna w obudowach (tak/nie)
     * i liczbę obudów spełniające kryterium.
     */
    @JsonProperty(RequestParams.HAS_WINDOW)
    private List<Object[]> hasWindow;

    /**
     * Lista par wskazujących obecność zasilacza w obudowach (tak/nie)
     * i liczbę obudów spełniające kryterium.
     */
    @JsonProperty(RequestParams.HAS_POWER_SUPPLY)
    private List<Object[]> hasPowerSupply;

    /**
     * Lista par zawierających moce zasilaczy wbudowanych w obudowy
     * i liczbę obudów spełniające kryterium.
     */
    @JsonProperty(RequestParams.PS_POWER)
    private List<Object[]> psPower;

    /**
     * Lista par zawierających maksymalne długości kart graficznych,
     * jakie mogą być obsługiwane przez obudowy,
     * oraz liczbę obudów spełniające kryterium.
     */
    @JsonProperty(RequestParams.MAX_GPU_LENGTH)
    private List<Object[]> maxGpuLength;

    /**
     * Lista par zawierających maksymalne wysokości chłodzeń CPU oraz liczbę obudów spełniających kryterium.
     */
    @JsonProperty(RequestParams.MAX_CPU_HEIGHT)
    private List<Object[]> maxCpuCoolerHeight;

    /**
     * Lista par zawierających informacje o wentylatorach na panelu przednim oraz liczbę obudów spełniających kryterium.
     */
    @JsonProperty(RequestParams.FRONT_FANS)
    private List<Object[]> frontFans;

    /**
     * Lista par zawierających informacje o wentylatorach na panelu tylnym oraz liczbę obudów spełniających kryterium.
     */
    @JsonProperty(RequestParams.BACK_FANS)
    private List<Object[]> backFans;

    /**
     * Lista par zawierających informacje o wentylatorach na panelu bocznym oraz liczbę obudów spełniających kryterium.
     */
    @JsonProperty(RequestParams.SIDE_FANS)
    private List<Object[]> sideFans;

    /**
     * Lista par zawierających informacje o wentylatorach na panelu dolnym oraz liczbę obudów spełniających kryterium.
     */
    @JsonProperty(RequestParams.BOTTOM_FANS)
    private List<Object[]> bottomFans;

    /**
     * Lista par zawierających informacje o wentylatorach na panelu górnym oraz liczbę obudów spełniających kryterium.
     */
    @JsonProperty(RequestParams.TOP_FANS)
    private List<Object[]> topFans;

    /**
     * Lista par zawierających liczbę portów USB 2.0 w obudowach oraz liczbę obudów spełniających kryterium.
     */
    @JsonProperty(RequestParams.USB_20)
    private List<Object[]> usb20;

    /**
     * Lista par zawierających liczbę portów USB 3.0 oraz liczbę obudów spełniających kryterium.
     */
    @JsonProperty(RequestParams.USB_30)
    private List<Object[]> usb30;

    /**
     * Lista par zawierających liczbę portów USB 3.1 oraz liczbę obudów spełniających kryterium.
     */
    @JsonProperty(RequestParams.USB_31)
    private List<Object[]> usb31;

    /**
     * Lista par zawierających liczbę portów USB 3.2 oraz liczbę obudów spełniających kryterium.
     */
    @JsonProperty(RequestParams.USB_32)
    private List<Object[]> usb32;

    /**
     * Lista par zawierających liczbę portów USB-C w oraz liczbę obudów spełniających kryterium.
     */
    @JsonProperty(RequestParams.USB_C)
    private List<Object[]> usbC;
}
