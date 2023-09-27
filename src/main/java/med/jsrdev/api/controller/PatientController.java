package med.jsrdev.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.jsrdev.api.domain.address.PatientAddressData;
import med.jsrdev.api.domain.patient.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/patients")
@SecurityRequirement(name = "bearer-key")
public class PatientController {
    @Autowired
    private PatientRepository patientRepository;

    @PostMapping("/register")
    public ResponseEntity<PatientDataResponse> addPatient(
            @RequestBody @Valid AddPatientData data, UriComponentsBuilder uri) {
        Patient patient = patientRepository.save(new Patient(data));

        URI url = uri.path("/patients/register/{id}").buildAndExpand(patient.getId()).toUri();
        return ResponseEntity.created(url).body(responsePatientData(patient));
    }

    // Metodo que retorna los datos ingresados para un registro
    @GetMapping("/register/{id}")
    public ResponseEntity<PatientDataResponse> returnPatientData(@PathVariable Long id) {
        Patient patient = patientRepository.getReferenceById(id);

        return ResponseEntity.ok(responsePatientData(patient));
    }

    //Get all patients
    @GetMapping
    public ResponseEntity<Page<GetPatientDataList>> getPatientList(@PageableDefault(page = 0, size = 10, sort = {"name"}) Pageable pagination)  {
        return ResponseEntity.ok(patientRepository.findAll(pagination).map(GetPatientDataList::new));
    }

    //Get Active Patients
    @GetMapping("/get-active-patients")
    public ResponseEntity<Page<GetPatientDataList>> getActivePatientList(@PageableDefault(page = 0, size = 10, sort = {"name"}) Pageable pagination)  {
        return ResponseEntity.ok(patientRepository.findByActiveTrue(pagination).map(GetPatientDataList::new));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<PatientDataResponse> updatePatient( @RequestBody @Valid UpdatePatientData updatePatient) {
        Patient patient = patientRepository.getReferenceById(updatePatient.id());
        patient.updatePatient(updatePatient);

        return ResponseEntity.ok( responsePatientData(patient));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deletePatient(@RequestBody @Valid@PathVariable Long id) {
        Patient patient = patientRepository.getReferenceById(id);
        patient.deactivePatient();
        return ResponseEntity.noContent().build();
    }

    private PatientDataResponse responsePatientData( Patient patient) {
        return new PatientDataResponse(
                patient.getId(), patient.getName(), patient.getEmail(),
                patient.getIdentityDocument(), patient.getPhone(),
                new PatientAddressData(
                        patient.getAddress().getUrbanization(),
                        patient.getAddress().getDistrict(),
                        patient.getAddress().getPostalCode(),
                        patient.getAddress().getComplement(),
                        patient.getAddress().getNumber(),
                        patient.getAddress().getProvince(),
                        patient.getAddress().getCity()
                )
        );
    }
}
