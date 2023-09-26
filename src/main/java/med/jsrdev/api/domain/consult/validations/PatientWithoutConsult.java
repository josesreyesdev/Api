package med.jsrdev.api.domain.consult.validations;

import jakarta.validation.ValidationException;
import med.jsrdev.api.domain.consult.AddScheduleConsultData;
import med.jsrdev.api.domain.consult.ConsultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PatientWithoutConsult implements ValidatedQueries {
    // No permitir mas de una consulta en el mismo dia para el mismo paciente

    @Autowired
    private ConsultRepository repository;

    public void validate(AddScheduleConsultData data) {
        var firstSchedule = data.date().withHour(7);
        var lastSchedule = data.date().withHour(18);

        // calcular que el paciente no tenga una consulta en el rango de 7 a 18 hrs.
        var patientWithConsult = repository.existsByPatientIdAndDateBetween(data.idPatient(), firstSchedule, lastSchedule);

        if (patientWithConsult) {
            throw new ValidationException("El Paciente ya tiene una consulta para este dia");
        }
    }
}
