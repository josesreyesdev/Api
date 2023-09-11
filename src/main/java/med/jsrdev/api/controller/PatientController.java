package med.jsrdev.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.jsrdev.api.patient.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {
    @Autowired
    private PatientRepository patientRepository;

    @PostMapping("/register")
    @Transactional
    public void addPatient(@RequestBody @Valid AddPatientData data) {
        patientRepository.save(new Patient(data));
    }

    @PostMapping("/register-patients")
    @Transactional
    public void addPatientList(@RequestBody @Valid List<AddPatientData> patients) {
        patients.forEach(patient -> patientRepository.save(new Patient(patient)));
    }

    @GetMapping
    public Page<GetPatientDataList> getPatientList(@PageableDefault(page = 0, size = 10, sort = {"name"}) Pageable pagination)  {
        return patientRepository.findAll(pagination).map(GetPatientDataList::new);
    }

    @PutMapping
    @Transactional
    public void updatePatient( @RequestBody @Valid UpdatePatientData updatePatient) {
        Patient patient = patientRepository.getReferenceById(updatePatient.id());

        patient.updatePatient(updatePatient);
    }
}
