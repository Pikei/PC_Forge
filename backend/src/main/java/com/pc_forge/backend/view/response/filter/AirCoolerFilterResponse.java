package com.pc_forge.backend.view.response.filter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pc_forge.backend.controller.api.constants.RequestParams;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Finalna klasa DTO filtra układów chłodzenia powietrzem, dziedzicząca po klasie {@link CoolerFilterResponse}.
 * Zawiera dodatkowe pola specyficzne dla filtrowania układów chłodzenia powietrzem.
 * Obiekty tej klasy służą do zwracania dostępnych opcji filtrowania dla układów chłodzenia powietrzem
 * w odpowiedzi na odpowiednie żądania HTTP.
 */
@Getter
@Setter
public final class AirCoolerFilterResponse extends CoolerFilterResponse {
    /**
     * Lista par zawierających dostępne materiały podstawy i liczbę układów chłodzenia spełniających kryterium.
     */
    @JsonProperty(RequestParams.BASE_MATERIAL)
    private List<Object[]> baseMaterial;

    /**
     * Minimalna wysokość układu chłodzenia powietrzem w milimetrach.
     */
    @JsonProperty(RequestParams.HEIGHT_MINIMUM)
    private Integer minHeight;

    /**
     * Maksymalna wysokość układu chłodzenia powietrzem w milimetrach.
     */
    @JsonProperty(RequestParams.HEIGHT_MAXIMUM)
    private Integer maxHeight;

    /**
     * Minimalna szerokość układu chłodzenia powietrzem w milimetrach.
     */
    @JsonProperty(RequestParams.WIDTH_MINIMUM)
    private Integer minWidth;

    /**
     * Maksymalna szerokość układu chłodzenia powietrzem w milimetrach.
     */
    @JsonProperty(RequestParams.WIDTH_MAXIMUM)
    private Integer maxWidth;

    /**
     * Minimalna głębokość układu chłodzenia powietrzem w milimetrach.
     */
    @JsonProperty(RequestParams.DEPTH_MINIMUM)
    private Integer minDepth;

    /**
     * Maksymalna głębokość układu chłodzenia powietrzem w milimetrach.
     */
    @JsonProperty(RequestParams.DEPTH_MAXIMUM)
    private Integer maxDepth;

    /**
     * Lista par wskazujących wertykalny sposób montażu i liczbę układów chłodzenia spełniających kryterium.
     */
    @JsonProperty(RequestParams.VERTICAL_INSTALLATION)
    private List<Object[]> verticalInstallation;
}
