package med.jsrdev.api.domain.consult.validations;

import jakarta.validation.ValidationException;
import med.jsrdev.api.domain.consult.AddScheduleConsultData;
import med.jsrdev.api.domain.medic.MedicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActiveMedic {
    // No permitir agendar citas con medicos inactivos

    @Autowired
    private MedicRepository repository;

    public void validate(AddScheduleConsultData data) {
        if (data.idMedic() == null) {
            return;
        }

        var activeMedic = repository.findActiveMedicById(data.idMedic());

        if (!activeMedic) {
            throw new ValidationException("No puedo agendar citas con medicos inactivos en el sistema");
        }
    }
}
