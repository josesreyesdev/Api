package med.jsrdev.api.controller;

import med.jsrdev.api.domain.consult.AddScheduleConsultData;
import med.jsrdev.api.domain.consult.ConsultDetailData;
import med.jsrdev.api.domain.consult.ConsultScheduleService;
import med.jsrdev.api.domain.medic.Specialty;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class ConsultControllerTest {

    // Simular
    @Autowired
    private MockMvc mvc;

    // retorno de json
    @Autowired
    private JacksonTester<AddScheduleConsultData> addScheduleConsultDataJacksonTester;

    // transformar el json a objeto java y viceversa
    @Autowired
    private JacksonTester<ConsultDetailData> consultDetailDataJacksonTester;

    @MockBean
    private ConsultScheduleService consultScheduleService;

    @Test
    @DisplayName("Deberia retornar estado http 404 cuando los datos ingresados sean invalidos")
    @WithMockUser
    void scheduleScenario1() throws Exception {

        //GIVEN AND WHEN
        var response = mvc.perform(post("/consults")).andReturn().getResponse();

        //THEN
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deberia retornar un json estado http 200 cuando los datos ingresados sean validos")
    @WithMockUser
    void scheduleScenario2() throws Exception {

        //GIVEN
        var date = LocalDateTime.now().plusHours(1);
        var specialty = Specialty.CARDIOLOGIA;
        var datosDetalleCosulta = new ConsultDetailData(null, 2L, 2L, date);

        // WHEN

        when(consultScheduleService.schedule(any())).thenReturn(datosDetalleCosulta);

        var response = mvc.perform(post("/consults")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                        addScheduleConsultDataJacksonTester.write(
                                new AddScheduleConsultData(2L, 2L, date, specialty)
                        ).getJson()
                )
        ).andReturn().getResponse();

        //THEN
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        var jsonEsperado = consultDetailDataJacksonTester.write(datosDetalleCosulta).getJson();

        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }
}