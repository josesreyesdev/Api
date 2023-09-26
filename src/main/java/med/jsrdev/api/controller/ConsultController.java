package med.jsrdev.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.jsrdev.api.domain.consult.ConsultDetailData;
import med.jsrdev.api.domain.consult.ConsultScheduleService;
import med.jsrdev.api.domain.consult.AddScheduleConsultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@RequestMapping("/consults")
public class ConsultController {

    @Autowired
    private ConsultScheduleService service;

    //agendar
    @PostMapping
    @Transactional
    public ResponseEntity<ConsultDetailData> schedule(@RequestBody @Valid AddScheduleConsultData data) {

        var response = service.schedule(data);

        return ResponseEntity.ok(response);
    }
}
