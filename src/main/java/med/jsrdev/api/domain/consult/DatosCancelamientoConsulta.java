package med.jsrdev.api.domain.consult;

import jakarta.validation.constraints.NotNull;

public record DatosCancelamientoConsulta(@NotNull Long idConsult, @NotNull MotivoCancelamiento motivo) {
}
