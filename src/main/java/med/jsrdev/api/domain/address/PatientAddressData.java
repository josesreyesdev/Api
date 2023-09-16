package med.jsrdev.api.domain.address;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PatientAddressData(
        @NotBlank(message = "Urbanization is required")
        String urbanization,
        @NotBlank(message = "District is required")
        String district,
        @NotBlank(message = "PostalCode is required")
        String postalCode,
        @NotBlank(message = "Complement is required")
        String complement,
        @NotNull(message = "Number is required")
        Integer number,
        @NotNull(message = "Province is required")
        Integer province,
        @NotBlank(message = "City is required")
        String city
) { }
