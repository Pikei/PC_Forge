package com.pc_forge.backend.view.body.order;

import com.pc_forge.backend.view.util.NullOrNotBlank;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressBody {

    @NotNull
    @NotBlank
    @Pattern(regexp = "^[A-ZĄĆĘŁŃÓŚŹŻ][a-ząćęłńóśźżA-ZĄĆĘŁŃÓŚŹŻ \\-]*$")
    @Size(min = 3, max = 255)
    private String city;

    @NotNull
    @NotBlank
    @Pattern(regexp = "^[A-ZĄĆĘŁŃÓŚŹŻ0-9][a-ząćęłńóśźżA-ZĄĆĘŁŃÓŚŹŻ0-9 \\-]*$")
    @Size(max = 255)
    private String street;

    @NotNull
    @NotBlank
    @Pattern(regexp = "^[A-Z0-9][a-zA-Z0-9]*$")
    private String houseNumber;

    @NullOrNotBlank
    private String apartmentNumber;

    @NotNull
    @NotBlank
    @Pattern(regexp = "^\\d{2}-\\d{3}$")
    private String postalCode;
}
