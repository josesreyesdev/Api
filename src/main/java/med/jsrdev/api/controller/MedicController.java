package med.jsrdev.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.jsrdev.api.medic.Medic;
import med.jsrdev.api.medic.MedicRepository;
import med.jsrdev.api.medic.MedicalDataList;
import med.jsrdev.api.medic.MedicalRegistrationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medics")
public class MedicController {

    @Autowired
    private MedicRepository medicRepository;

    //register medic
    @PostMapping("/register")
    @Transactional
    public void addMedic(@RequestBody @Valid MedicalRegistrationData medic) {
        medicRepository.save(new Medic(medic));
    }

    @PostMapping("/register-medics")
    @Transactional
    public void addMedicList(@RequestBody @Valid List<MedicalRegistrationData> medicList) {
        medicList.forEach(medic -> medicRepository.save(new Medic(medic)));
    }

    @GetMapping
    public Page<MedicalDataList> getMedicList(@PageableDefault(size = 4) Pageable pagination)  {
        return medicRepository.findAll(pagination).map(MedicalDataList::new);
    }

    @PutMapping
    public void updateMedic() {

    }
}