package com.pc_forge.backend.view.response.filter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pc_forge.backend.controller.api.constants.RequestParams;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Finalna klasa DTO filtra dysków HDD, dziedzicząca po klasie {@link DriveFilterResponse}.
 * Zawiera dodatkowe pola specyficzne dla filtrowania dysków HDD.
 * Obiekty tej klasy służą do zwracania dostępnych opcji filtrowania dla dysków HDD
 * w odpowiedzi na odpowiednie żądania HTTP.
 */
@Getter
@Setter
public final class HddFilterResponse extends DriveFilterResponse {
    /**
     * Lista par zawierających dostępne prędkości obrotowe dysku i liczbę dysków HDD spełniających kryterium.
     */
    @JsonProperty(RequestParams.ROTATIONAL_SPEED)
    List<Object[]> rotationalSpeed;
}
