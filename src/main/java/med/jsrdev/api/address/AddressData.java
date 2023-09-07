package med.jsrdev.api.address;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AddressData(
        @NotBlank
        String street,
        @NotBlank
        String district,
        @NotBlank
        String city,
        @NotNull
        Integer number,
        @NotBlank
        String complement
) { }
