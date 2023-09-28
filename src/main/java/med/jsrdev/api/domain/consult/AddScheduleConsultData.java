package med.jsrdev.api.domain.consult;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.jsrdev.api.domain.medic.Specialty;

import java.time.LocalDateTime;

public record AddScheduleConsultData(@NotNull Long idPatient, Long idMedic, @NotNull @Future LocalDateTime date, Specialty specialty) {
}
