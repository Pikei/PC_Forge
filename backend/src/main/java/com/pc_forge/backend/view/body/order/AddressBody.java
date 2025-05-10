package com.pc_forge.backend.view.body.order;

import com.pc_forge.backend.view.util.NullOrNotBlank;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * Klasa będąca reprezentacją DTO (Data Transfer Object) wykorzystywanego podczas składania zamówienia przez użytkownika.
 * Odbierane jest przez kontroler jako Request Body. Zawiera adresowe dane do wysyłki zamówienia, które mogą różnić się
 * pomiędzy różnymi zamówieniami złożonymi przez tego samego użytkownika.
 */
@Getter
@Setter
public class AddressBody {

    /**
     * Nazwa miasta.
     */
    @NotNull
    @NotBlank
    @Pattern(regexp = "^[A-ZĄĆĘŁŃÓŚŹŻ][a-ząćęłńóśźżA-ZĄĆĘŁŃÓŚŹŻ \\-]*$")
    @Size(min = 3, max = 255)
    private String city;

    /**
     * Nazwa ulicy.
     */
    @NotNull
    @NotBlank
    @Pattern(regexp = "^[A-ZĄĆĘŁŃÓŚŹŻ0-9][a-ząćęłńóśźżA-ZĄĆĘŁŃÓŚŹŻ0-9 \\-]*$")
    @Size(max = 255)
    private String street;

    /**
     * Numer domu.
     */
    @NotNull
    @NotBlank
    @Pattern(regexp = "^[A-Z0-9][a-zA-Z0-9]*$")
    private String houseNumber;

    /**
     * Numer mieszkania.
     */
    @NullOrNotBlank
    private String apartmentNumber;

    /**
     * Kod pocztowy.
     */
    @NotNull
    @NotBlank
    @Pattern(regexp = "^\\d{2}-\\d{3}$")
    private String postalCode;
}
