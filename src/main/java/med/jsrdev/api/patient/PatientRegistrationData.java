package med.jsrdev.api.patient;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.jsrdev.api.address.AddressData;

public record PatientRegistrationData(
        @NotBlank String name,
        @NotBlank @Email String email,
        @NotBlank @Pattern(regexp = "\\d{4,14}") String identityDocument,
        @NotBlank String phone,
        @NotNull @Valid AddressData address
) {
}