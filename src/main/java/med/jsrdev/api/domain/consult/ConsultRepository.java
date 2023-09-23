package med.jsrdev.api.domain.consult;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface ConsultRepository extends JpaRepository<Consult, Long> {
    Boolean existByPatientIdAndDateBetween(Long idPatient, LocalDateTime firstSchedule, LocalDateTime lastSchedule);

    Boolean existByIdMedicAndDate(Long idMedic, LocalDateTime date);
}
