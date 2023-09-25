package med.jsrdev.api.domain.consult.validations;

import jakarta.validation.ValidationException;
import med.jsrdev.api.domain.consult.AddScheduleConsultData;
import med.jsrdev.api.domain.patient.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActivePatient implements ValidatedQueries {
    // No permitir agendar citas con pacientes inactivos

    @Autowired
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
