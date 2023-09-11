package med.jsrdev.api.address;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PatientAddressData(
        @NotBlank
        String urbanization,
        @NotBlank
        String district,
        @NotBlank
        String postalCode,
        @NotBlank
        String complement,
        @NotNull
        Integer number,
        @NotBlank
        Integer province,
        @NotBlank
        String city
) { }
