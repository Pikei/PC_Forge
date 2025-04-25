package com.pc_forge.backend.view.body;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class RegistrationBody {
    @NotNull
    @NotBlank
    @Size(min = 3, max = 64)
    private String username;

    @Email()
    private String email;

    @NotNull
    @NotBlank
    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$")
    private String password;

    @NotNull
    @NotBlank
    @Pattern(regexp = "^[A-Za-zĄĆĘŁŃÓŚŹŻąćęłńóśźż]{3,64}$")
    private String firstName;

    @NotNull
    @NotBlank
    @Pattern(regexp = "^[A-Za-zĄĆĘŁŃÓŚŹŻąćęłńóśźż]{3,64}$")
    private String lastName;

    @Pattern(regexp = "^\\d{9}$")
    private String phoneNumber;
}
