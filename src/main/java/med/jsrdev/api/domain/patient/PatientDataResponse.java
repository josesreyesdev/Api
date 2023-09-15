package med.jsrdev.api.domain.patient;

import med.jsrdev.api.domain.address.PatientAddressData;

public record PatientDataResponse(
        Long id,
        String name,
        String email,
        String identityDocument,
        String phone,
        PatientAddressData address
) {
}
