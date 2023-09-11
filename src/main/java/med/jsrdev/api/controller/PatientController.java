package med.jsrdev.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.jsrdev.api.patient.Patient;
import med.jsrdev.api.patient.PatientDataList;
import med.jsrdev.api.patient.PatientRegistrationData;
import med.jsrdev.api.patient.PatientRepository;
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
    public void addPatient(@RequestBody @Valid PatientRegistrationData data) {
        patientRepository.save(new Patient(data));
    }

    @PostMapping("/register-patients")
    @Transactional
    public void addPatientList(@RequestBody @Valid List<PatientRegistrationData> patients) {
        patients.forEach(patient -> patientRepository.save(new Patient(patient)));
    }

    @GetMapping
    public Page<PatientDataList> getPatientList(@PageableDefault(page = 0, size = 10, sort = {"name"}) Pageable pagination)  {
        return patientRepository.findAll(pagination).map(PatientDataList::new);
    }
}
