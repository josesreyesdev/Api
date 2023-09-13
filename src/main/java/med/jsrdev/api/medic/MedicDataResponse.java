package med.jsrdev.api.medic;

import med.jsrdev.api.address.MedicAddressData;

public record MedicDataResponse(
        Long id,
        String name,
        String email,
        String phone,
        Specialty specialty,
        String document,
        MedicAddressData address
) { }
