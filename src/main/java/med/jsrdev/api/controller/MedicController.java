package med.jsrdev.api.controller;

import med.jsrdev.api.medic.Medic;
import med.jsrdev.api.medic.MedicRepository;
import med.jsrdev.api.medic.MedicalRegistrationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medics")
public class MedicController {

    @Autowired //No es recomendable usarlo para fines de testing
    private MedicRepository medicRepository;

    //registrar medico
    @PostMapping
    public void registerPhysician(@RequestBody MedicalRegistrationData medicalRegistrationData) {
        medicRepository.save(new Medic(medicalRegistrationData));
    }
}