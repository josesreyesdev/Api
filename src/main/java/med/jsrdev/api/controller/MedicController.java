package med.jsrdev.api.controller;

import med.jsrdev.api.medic.MedicalRegistrationData;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/medics")
public class MedicController {

    //registrar medico
    @PostMapping
    public void registerPhysician(@RequestBody MedicalRegistrationData medicalRegistrationData) {
        System.out.println("Request llega correctamente");
        System.out.println(medicalRegistrationData.name());
    }
}
