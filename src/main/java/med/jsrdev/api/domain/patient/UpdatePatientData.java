package med.jsrdev.api.domain.patient;

import jakarta.validation.constraints.NotNull;
import med.jsrdev.api.domain.address.PatientAddressData;

public record UpdatePatientData(@NotNull Long id, String name, String identityDocument, PatientAddressData address) {

}
