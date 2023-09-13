package med.jsrdev.api.patient;

import med.jsrdev.api.address.PatientAddressData;

public record PatientDataResponse(
        Long id,
        String name,
        String email,
        String identityDocument,
        String phone,
        PatientAddressData address
) {
}
