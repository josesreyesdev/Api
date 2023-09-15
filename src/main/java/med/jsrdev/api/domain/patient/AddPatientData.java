package med.jsrdev.api.domain.patient;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.jsrdev.api.domain.address.PatientAddressData;

public record AddPatientData(
        @NotBlank
        String name,
        @NotBlank
        @Email
        String email,
        @NotBlank
        @Pattern(regexp = "\\d{4,14}")
        String identityDocument,
        @NotBlank
        String phone,
        @NotNull
        @Valid
        PatientAddressData address
) {
}
