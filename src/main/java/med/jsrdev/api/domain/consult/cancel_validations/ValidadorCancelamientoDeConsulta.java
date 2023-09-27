package med.jsrdev.api.domain.consult.cancel_validations;

import med.jsrdev.api.domain.consult.DatosCancelamientoConsulta;

public interface ValidadorCancelamientoDeConsulta {
    void validate(DatosCancelamientoConsulta data);
}
