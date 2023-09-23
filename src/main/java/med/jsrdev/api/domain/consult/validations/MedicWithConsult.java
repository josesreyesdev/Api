package med.jsrdev.api.domain.consult.validations;

import jakarta.validation.ValidationException;
import med.jsrdev.api.domain.consult.AddScheduleConsultData;
import med.jsrdev.api.domain.consult.ConsultRepository;

public class MedicWithConsult {

    private ConsultRepository repository;

    public void validate(AddScheduleConsultData data) {

        if (data.idMedic() == null) {
            return;
        }

        var medicWithConsult = repository.existByIdMedicAndDate(data.idMedic(), data.date());

        if (medicWithConsult) {
            throw new ValidationException("El medico ya tiene una consulta en este horario");
        }
    }
}
