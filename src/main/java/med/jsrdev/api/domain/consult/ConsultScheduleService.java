package med.jsrdev.api.domain.consult;

import med.jsrdev.api.domain.consult.cancel_validations.ValidadorCancelamientoDeConsulta;
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

    @Autowired
    List<ValidadorCancelamientoDeConsulta> cancelValidators;

    public ConsultDetailData schedule(AddScheduleConsultData data) {

        if (patientRepository.findById(data.idPatient()).isEmpty()) {
            throw new ValidationIntegrity("Patient ID not found");
        }

        if (data.idMedic() != null && !medicRepository.existsById(data.idMedic())) {
            throw new ValidationIntegrity("Medic ID not found");
        }

        // Validations
        validators.forEach( v -> v.validate(data));


        var patient = patientRepository.findById(data.idPatient()).get();
        var medic = selectRandomMedic(data);
        if (medic == null) {
            throw new ValidationIntegrity("There are no Medics available for this schedule and specialty");
        }

        var consult = new Consult(medic, patient, data.date());
        consultRepository.save(consult);

        return new ConsultDetailData(consult);
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

    public void cancel(DatosCancelamientoConsulta data) {

        if (!consultRepository.existsById(data.idConsult())) {
            throw new ValidationIntegrity("Id de la consulta informado, no existe");
        }

        cancelValidators.forEach( v -> v.validate(data));

        var consult = consultRepository.getReferenceById(data.idConsult());
        consult.cancel(data.motivo());
    }
}
