package med.jsrdev.api.domain.consult.validations;

import jakarta.validation.ValidationException;
import med.jsrdev.api.domain.consult.AddScheduleConsultData;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ScheduleOfAnticipation implements ValidatedQueries {
    public void validate(AddScheduleConsultData data) {

        // Consultas deben programarse con al menos 30 minutos de anticipacion

        var now = LocalDateTime.now();

        var consultHour = data.date();

        var difference30minutes = Duration.between(now, consultHour).toMinutes() < 30;

        if (difference30minutes) {
            throw new ValidationException("la consulta debe tener al menos 30 minutos de anticipación");
        }
    }
}
