package med.jsrdev.api.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
class ConsultControllerTest {

    // Simular
    @Autowired
    private MockMvc mvc;

    @Test
    @DisplayName("Deberia retornar estado http 404 cuando los datos ingresados sean invalidos")
    @WithMockUser
    void scheduleScenario1() throws Exception {

        //GIVEN AND WHEN
        var response = mvc.perform(post("/consults")).andReturn().getResponse();

        //THEN
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }
}