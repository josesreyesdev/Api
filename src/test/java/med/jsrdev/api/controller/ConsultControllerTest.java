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

@SpringBootTest // Permite utilizar conponentes dentro del contexto de spring
@AutoConfigureMockMvc // Conf. los componentes necesarios para la simulación de una peticion para ese controlador,
@AutoConfigureJsonTesters
class ConsultControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<AddScheduleConsultData> addScheduleConsultDataJacksonTester;

    @Autowired
    private JacksonTester<ConsultDetailData> consultDetailDataJacksonTester;

    @MockBean
    private ConsultScheduleService consultScheduleService;

    @Test
    @DisplayName("Deberia retornar estado http 400, cuando datos ingresados sean invalidos")
    @WithMockUser //agregar dependencia
    void scheduleScenario1() throws Exception {

        //Given  // When
        var response = mvc.perform(post("/consults")).andReturn().getResponse();

        //Then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deberia retornar estado http 200, cuando datos ingresados sean validos")
    @WithMockUser //agregar dependencia
    void scheduleScenario2() throws Exception {

        //Given
        var date = LocalDateTime.now().plusHours(1);
        var specialty = Specialty.CARDIOLOGIA;
        var data = new ConsultDetailData(null, 2L, 5L, date);

        // When
        when(consultScheduleService.schedule(any())).thenReturn( data);

        var response = mvc.perform(post("/consults")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                        addScheduleConsultDataJacksonTester.write(
                                new AddScheduleConsultData(2L, 5L, date, specialty)
                        ).getJson()
                )
        ).andReturn().getResponse();

        //Then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        var jsonEsperado = consultDetailDataJacksonTester.write(data).getJson();

        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }
}