package med.jsrdev.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.jsrdev.api.domain.consult.ConsultDetailData;
import med.jsrdev.api.domain.consult.ScheduleConsultData;
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

    //agendar
    @PostMapping
    @Transactional
    public ResponseEntity<ConsultDetailData> schedule(@RequestBody @Valid ScheduleConsultData data) {

        return ResponseEntity.ok(new ConsultDetailData(null, null, null, null));
    }
}
