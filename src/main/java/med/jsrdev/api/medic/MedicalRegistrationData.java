package med.jsrdev.api.medic;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.jsrdev.api.address.AddressData;

public record MedicalRegistrationData(
        @NotBlank
        String name,
        @NotBlank
        @Email
        String email,

        @NotBlank
        String phone,

        @NotBlank
        @Pattern(regexp = "\\d{4,6}")
        String document,
        @NotNull
        Specialty specialty,
        @NotNull
        @Valid
        AddressData address
) {

}