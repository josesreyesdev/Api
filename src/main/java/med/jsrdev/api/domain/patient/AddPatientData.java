package med.jsrdev.api.domain.patient;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.jsrdev.api.domain.address.PatientAddressData;

public record AddPatientData(
        @NotBlank(message = "{name.required}")
        String name,
        @NotBlank(message = "{email.required}")
        @Email(message = "{email.invalid}")
        String email,
        @NotBlank(message = "{identityDocument.required}")
        @Pattern(regexp = "\\d{4,14}", message = "{identityDocument.invalid}")
        String identityDocument,
        @NotBlank(message = "{phone.required}")
        String phone,
        @NotNull(message = "{address.required}")
        @Valid
        PatientAddressData address
) {
}
