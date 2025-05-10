package com.pc_forge.backend.view.response.filter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pc_forge.backend.controller.api.constants.RequestParams;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Finalna klasa DTO filtra układów chłodzenia cieczą, dziedzicząca po klasie {@link CoolerFilterResponse}.
 * Zawiera dodatkowe pola specyficzne dla filtrowania układów chłodzenia cieczą.
 * Obiekty tej klasy służą do zwracania dostępnych opcji filtrowania dla układów chłodzenia cieczą
 * w odpowiedzi na odpowiednie żądania HTTP.
 */
@Getter
@Setter
public final class LiquidCoolerFilterResponse extends CoolerFilterResponse {
    /**
     * Lista par zawierających dostępne rozmiary chłodnic i liczbę układów chłodzenia spełniających kryterium.
     */
    @JsonProperty(RequestParams.COOLER_SIZE)
    private List<Object[]> coolerSize;
}
