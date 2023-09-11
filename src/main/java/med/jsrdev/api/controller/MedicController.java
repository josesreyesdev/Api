package med.jsrdev.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.jsrdev.api.medic.*;
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
    public void addMedic(@RequestBody @Valid AddMedicData medic) {
        medicRepository.save(new Medic(medic));
    }

    @PostMapping("/register-medics")
    public void addMedicList(@RequestBody @Valid List<AddMedicData> medicList) {
        medicList.forEach(medic -> medicRepository.save(new Medic(medic)));
    }

    // Get all medics
    @GetMapping
    public Page<GetMedicalDataList> getMedicList(@PageableDefault(size = 4) Pageable pagination)  {
        return medicRepository.findAll(pagination).map(GetMedicalDataList::new);
    }

    // Get all medics active
    @GetMapping("/get-active")
    public Page<GetMedicalDataList> getActiveMedicList(@PageableDefault(size = 4) Pageable pagination)  {
        return medicRepository.findByActiveTrue(pagination).map(GetMedicalDataList::new);
    }

    @PutMapping
    @Transactional
    public void updateMedic(@RequestBody @Valid UpdateMedicData updateMedic) {
        Medic medic = medicRepository.getReferenceById(updateMedic.id());
        medic.updateMedic(updateMedic);
    }

    //Delete en BD => no recomendable, hay que tener un historico
    @DeleteMapping("/delete-in-db/{id}")
    @Transactional
    public void deleteLogicMedic(@PathVariable Long id) {
        Medic medic = medicRepository.getReferenceById(id);
        medicRepository.delete(medic);
    }

    //Delete logic or exclusion logic
    @DeleteMapping("/{id}")
    @Transactional
    public void deactivateMedic(@PathVariable Long id) {
        Medic medic = medicRepository.getReferenceById(id);
        medic.deactiveMedic();
    }
}