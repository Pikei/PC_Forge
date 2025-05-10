package com.pc_forge.backend.view.response.filter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pc_forge.backend.controller.api.constants.RequestParams;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Finalna klasa DTO filtra dysków SSD, dziedzicząca po klasie {@link DriveFilterResponse}.
 * Zawiera dodatkowe pola specyficzne dla filtrowania dysków SSD.
 * Obiekty tej klasy służą do zwracania dostępnych opcji filtrowania dla dysków SSD
 * w odpowiedzi na odpowiednie żądania HTTP.
 */
@Getter
@Setter
public final class SsdFilterResponse extends DriveFilterResponse {
    /**
     * Lista par zawierających dostępne prędkości odczytu dysku i liczbę dysków SSD spełniających kryterium.
     */
    @JsonProperty(RequestParams.READ_SPEED)
    List<Object[]> readSpeed;

    /**
     * Lista par zawierających dostępne prędkości zapisu dysku i liczbę dysków SSD spełniających kryterium.
     */
    @JsonProperty(RequestParams.WRITE_SPEED)
    List<Object[]> writeSpeed;
}
