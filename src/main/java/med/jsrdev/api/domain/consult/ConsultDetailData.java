package med.jsrdev.api.domain.consult;

import java.time.LocalDateTime;

public record ConsultDetailData(Long id, Long idPatient, Long idMedic, LocalDateTime date) {
    public ConsultDetailData(Consult consult) {
        this(consult.getId(), consult.getPatient().getId(), consult.getMedic().getId(), consult.getDate());
    }
}
