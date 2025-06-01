package com.pc_forge.backend.view.response.filter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pc_forge.backend.controller.api.constants.RequestParams;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Finalna klasa DTO filtra pamięci RAM, dziedzicząca po klasie {@link ProductFilterResponse}.
 * Zawiera dodatkowe pola specyficzne dla filtrowania pamięci RAM.
 * Obiekty tej klasy służą do zwracania dostępnych opcji filtrowania dla pamięci RAM
 * w odpowiedzi na odpowiednie żądania HTTP.
 */
@Getter
@Setter
public final class MemoryFilterResponse extends ProductFilterResponse {

    /**
     * Lista par zawierających typy pamięci RAM (np. DDR4, DDR5)
     * i liczbę modułów pamięci spełniających kryterium.
     */
    @JsonProperty(RequestParams.RAM_TYPE)
    private List<Object[]> memoryType;

    /**
     * Lista par zawierających wartości całkowitej pojemności pamięci RAM
     * i liczbę modułów pamięci spełniających kryterium.
     */
    @JsonProperty(RequestParams.RAM_CAPACITY)
    private List<Object[]> totalCapacity;

    /**
     * Lista par zawierających częstotliwości pamięci RAM
     * i liczbę modułów pamięci spełniających kryterium.
     */
    @JsonProperty(RequestParams.RAM_FREQUENCY)
    private List<Object[]> frequency;

    /**
     * Lista par zawierających liczbę modułów pamięci RAM w zestawie
     * i liczbę zestawów pamięci spełniających kryterium.
     */
    @JsonProperty(RequestParams.NUMBER_OF_MODULES)
    private List<Object[]> numberOfModules;

    /**
     * Lista par zawierających opóźnienie zegara pamięci RAM
     * i liczbę modułów pamięci spełniających kryterium.
     */
    @JsonProperty(RequestParams.LATENCY)
    private List<Object[]> latency;

    /**
     * Lista par wskazujących obecność podświetlenia w modułach pamięci RAM (tak/nie)
     * i liczbę modułów pamięci spełniających kryterium.
     */
    @JsonProperty(RequestParams.LIGHTNING)
    private List<Object[]> lightning;
}