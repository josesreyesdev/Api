package med.jsrdev.api.domain.medic;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.jsrdev.api.domain.address.MedicAddressData;

public record AddMedicData(
        //personalizar mensajes de error
        @NotBlank(message = "Name is required")
        String name,
        @NotBlank(message = "Email is required")
        @Email(message = "Email in invalid format")
        String email,

        @NotBlank(message = "Phone is required")
        String phone,
        @NotBlank(message = "Document is required")
        @Pattern(regexp = "\\d{4,6}", message = "Document in invalid format")
        String document,
        @NotNull(message = "Specialty is required")
        Specialty specialty,
        @NotNull (message = "Address is required")
        @Valid
        MedicAddressData address
) { }