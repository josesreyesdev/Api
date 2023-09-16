package med.jsrdev.api.domain.address;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MedicAddressData(
        @NotBlank(message = "Street is required")
        String street,
        @NotBlank(message = "District is required")
        String district,
        @NotBlank(message = "City is required")
        String city,
        @NotNull(message = "Number is required")
        Integer number,
        @NotBlank(message = "Complement is required")
        String complement
) { }
