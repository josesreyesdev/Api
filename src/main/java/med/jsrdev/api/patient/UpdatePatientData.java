package med.jsrdev.api.patient;

import jakarta.validation.constraints.NotNull;
import med.jsrdev.api.address.PatientAddressData;

public record UpdatePatientData(@NotNull Long id, String name, String identityDocument, PatientAddressData address) {

}
