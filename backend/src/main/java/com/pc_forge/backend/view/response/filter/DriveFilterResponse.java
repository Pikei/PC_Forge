package com.pc_forge.backend.view.response.filter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pc_forge.backend.controller.api.constants.RequestParams;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Klasa abstrakcyjna DTO filtra dysku. Rozszerza klasę {@link ProductFilterResponse}.
 * Zawiera informacje o dostępnych opcjach filtrowania wspólnych dla wszystkich typów dysków twardych.
 * Klasy z niej dziedziczące dodają kolejne opcje filtrowania. Obiekty tych klas są następnie zwracane
 * jako odpowiedź na żądanie pobrania dostępnych opcji filtrowania.
 */
@Getter
@Setter
public abstract class DriveFilterResponse extends ProductFilterResponse {
    @JsonProperty(RequestParams.FORMAT)
    List<Object[]> format;

    @JsonProperty(RequestParams.INTERFACE)
    List<Object[]> driveInterface;

    @JsonProperty(RequestParams.STORAGE)
    List<Object[]> storage;
}
