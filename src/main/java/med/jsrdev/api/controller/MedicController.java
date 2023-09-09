package med.jsrdev.api.controller;

import jakarta.validation.Valid;
import med.jsrdev.api.medic.Medic;
import med.jsrdev.api.medic.MedicRepository;
import med.jsrdev.api.medic.MedicalDataList;
import med.jsrdev.api.medic.MedicalRegistrationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medics")
public class MedicController {

    @Autowired
    private MedicRepository medicRepository;

    //register medic
    @PostMapping
    public void registerMedic(@RequestBody @Valid MedicalRegistrationData medicalRegistrationData) {
        medicRepository.save(new Medic(medicalRegistrationData));
    }

    @GetMapping
    public Page<MedicalDataList> medicList(Pageable pagination)  {
        return medicRepository.findAll(pagination).map(MedicalDataList::new);
    }
}