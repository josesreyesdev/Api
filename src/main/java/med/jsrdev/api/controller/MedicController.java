package med.jsrdev.api.controller;

import med.jsrdev.api.medic.MedicalRegistrationData;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medics")
public class MedicController {

    //registrar medico
    @PostMapping
    public void registerPhysician(@RequestBody MedicalRegistrationData medicalRegistrationData) {
        System.out.println("Request Medical llegó correctamente");
        System.out.println(medicalRegistrationData.name());
    }
}