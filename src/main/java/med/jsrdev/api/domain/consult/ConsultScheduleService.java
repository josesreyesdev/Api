package med.jsrdev.api.domain.consult;

import med.jsrdev.api.domain.consult.validations.ValidatedQueries;
import med.jsrdev.api.domain.medic.Medic;
import med.jsrdev.api.domain.medic.MedicRepository;
import med.jsrdev.api.domain.patient.PatientRepository;
import med.jsrdev.api.infra.exceptions.ValidationIntegrity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultScheduleService {

    @Autowired
    private MedicRepository medicRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private ConsultRepository consultRepository;

    @Autowired
    List<ValidatedQueries> validators;

    public void schedule(AddScheduleConsultData data) {

        if (patientRepository.findById(data.idPatient()).isPresent()) {
            throw new ValidationIntegrity("Patient ID not found");
        }

        if (data.idMedic() != null && medicRepository.existsById(data.idMedic())) {
            throw new ValidationIntegrity("Medic ID not found");
        }

        // Validations
        validators.forEach( v -> v.validate(data));


        var patient = patientRepository.findById(data.idPatient()).get();
        var medic = selectRandomMedic(data);

        var consult = new Consult(null, medic, patient, data.date());
        consultRepository.save(consult);
    }

    private Medic selectRandomMedic(AddScheduleConsultData data) {

        // validar que id medico no sea nulo
        if (data.idMedic() != null) {
            return medicRepository.getReferenceById(data.idMedic());
        }

        //valida la especialidad
        if (data.specialty() == null) {
            throw new ValidationIntegrity("Please, select the Medic Specialty");
        }


        return medicRepository.selectMedicWithSpecialtyInDate(data.specialty(), data.date());
    }
}
