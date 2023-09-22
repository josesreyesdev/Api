package med.jsrdev.api.domain.consult;

import med.jsrdev.api.domain.medic.Medic;
import med.jsrdev.api.domain.medic.MedicRepository;
import med.jsrdev.api.domain.patient.PatientRepository;
import med.jsrdev.api.infra.exceptions.ValidationIntegrity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsultScheduleService {

    @Autowired
    private MedicRepository medicRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private ConsultRepository consultRepository;

    public void schedule(AddScheduleConsultData data) {

        if (patientRepository.findById(data.idPatient()).isPresent()) {
            throw new ValidationIntegrity("Patient ID not found");
        }

        if (data.idMedic() != null && medicRepository.existsById(data.idMedic())) {
            throw new ValidationIntegrity("Medic ID not found");
        }

        var patient = patientRepository.findById(data.idPatient()).get();
        var medic = selectMedic(data);

        var consult = new Consult(null, medic, patient, data.date());
        consultRepository.save(consult);
    }

    private Medic selectMedic(AddScheduleConsultData data) {
        return null;
    }
}
