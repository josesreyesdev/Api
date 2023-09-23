package med.jsrdev.api.domain.consult.validations;

import jakarta.validation.ValidationException;
import med.jsrdev.api.domain.consult.AddScheduleConsultData;
import med.jsrdev.api.domain.patient.PatientRepository;

public class ActivePatient {
    // No permitir agendar citas con pacientes inactivos

    private PatientRepository repository;

    public void validate(AddScheduleConsultData data) {
        if (data.idPatient() == null) {
            return;
        }

        var activePatient = repository.findActivePatientById(data.idPatient());

        if (!activePatient) {
            throw new ValidationException("No puedo agendar citas con pacientes inactivos");
        }
    }
}
