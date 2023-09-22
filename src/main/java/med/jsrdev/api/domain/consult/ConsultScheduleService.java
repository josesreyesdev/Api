package med.jsrdev.api.domain.consult;

import med.jsrdev.api.domain.medic.MedicRepository;
import med.jsrdev.api.domain.patient.PatientRepository;
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

        var patient = patientRepository.findById(data.idPatient()).get();
        var medic = medicRepository.findById(data.idMedic()).get();

        var consult = new Consult(null, medic, patient, data.date());
        consultRepository.save(consult);
    }
}
