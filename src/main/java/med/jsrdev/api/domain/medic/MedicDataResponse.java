package med.jsrdev.api.domain.medic;

import med.jsrdev.api.domain.address.MedicAddressData;

public record MedicDataResponse(
        Long id,
        String name,
        String email,
        String phone,
        Specialty specialty,
        String document,
        MedicAddressData address
) { }
