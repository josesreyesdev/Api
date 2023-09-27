package med.jsrdev.api.domain.consult.cancel_validations;

import jakarta.validation.ValidationException;
import med.jsrdev.api.domain.consult.ConsultRepository;
import med.jsrdev.api.domain.consult.DatosCancelamientoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component("ValidadorCancelamientoDeConsulta")
public class ValidadorHorarioAntecedencia  implements ValidadorCancelamientoDeConsulta{

    @Autowired
    private ConsultRepository repository;

    @Override
    public void validate(DatosCancelamientoConsulta data) {
        var consult = repository.getReferenceById(data.idConsult());
        var now = LocalDateTime.now();
        var differenceInHours = Duration.between(now, consult.getDate()).toHours();

        if (differenceInHours < 24) {
            throw new ValidationException("La consulta solo puede ser cancelada con antecedencia minima de 24 hrs");
        }
    }
}
