package med.jsrdev.api.controller;

import jakarta.validation.Valid;
import med.jsrdev.api.medic.Medic;
import med.jsrdev.api.medic.MedicRepository;
import med.jsrdev.api.medic.MedicalDataList;
import med.jsrdev.api.medic.MedicalRegistrationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medics")
public class MedicController {

    @Autowired
    private MedicRepository medicRepository;

    //register medic
    @PostMapping
    public void registerPhysician(@RequestBody @Valid MedicalRegistrationData medicalRegistrationData) {
        medicRepository.save(new Medic(medicalRegistrationData));
    }

    //medic list
    @GetMapping
    public List<MedicalDataList> medicList() {
        return medicRepository.findAll().stream().map(MedicalDataList::new).toList();
    }

    // get medic by id
}