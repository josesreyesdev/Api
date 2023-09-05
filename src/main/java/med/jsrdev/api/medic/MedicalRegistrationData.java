package med.jsrdev.api.medic;

import med.jsrdev.api.address.AddressData;

public record MedicalRegistrationData(
        String name, String email, String document, Specialty specialty, AddressData address
) { }