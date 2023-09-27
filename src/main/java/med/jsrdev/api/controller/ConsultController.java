package med.jsrdev.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.jsrdev.api.domain.consult.ConsultDetailData;
import med.jsrdev.api.domain.consult.ConsultScheduleService;
import med.jsrdev.api.domain.consult.AddScheduleConsultData;
import med.jsrdev.api.domain.consult.DatosCancelamientoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@ResponseBody
@RequestMapping("/consults")
@SecurityRequirement(name = "bearer-key")
public class ConsultController {

    @Autowired
    private ConsultScheduleService service;

    //agendar
    @PostMapping
    @Transactional
    public ResponseEntity<ConsultDetailData> schedule(@RequestBody @Valid AddScheduleConsultData data) /*throws ValidationIntegrity */ {

        var response = service.schedule(data);

        return ResponseEntity.ok(response);
    }

    //cancelar
    @DeleteMapping
    @Transactional
    public ResponseEntity<Void> cancel(@RequestBody @Valid DatosCancelamientoConsulta data) {
        service.cancel(data);
        return ResponseEntity.noContent().build();
    }
}
