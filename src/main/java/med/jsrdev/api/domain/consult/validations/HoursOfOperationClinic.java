package med.jsrdev.api.domain.consult.validations;

import jakarta.validation.ValidationException;
import med.jsrdev.api.domain.consult.AddScheduleConsultData;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class HoursOfOperationClinic implements ValidatedQueries {
    public void validate(AddScheduleConsultData data) {

        // Horario de atencion de Lunes-Viernes de 07:00 a 19:00 hrs

        var sunday = DayOfWeek.SUNDAY.equals(data.date().getDayOfWeek());
        var timeBeforeOpening = data.date().getHour() < 7;
        var timeAfterOpening = data.date().getHour() > 19;

        if (sunday || timeBeforeOpening || timeAfterOpening) {
            throw new ValidationException("El horario de atencion es de Lunes a Viernes de 07:00 a 19:00 hrs");
        }
    }
}
