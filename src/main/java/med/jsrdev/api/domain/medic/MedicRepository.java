package med.jsrdev.api.domain.medic;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface MedicRepository extends JpaRepository<Medic, Long> {
    Page<Medic> findByActiveTrue(Pageable pagination);

    @Query("""
        SELECT m FROM Medic m
        WHERE m.active = true AND
        m.specialty = :specialty AND
        m.id NOT IN(
            SELECT c.medic.id FROM Consults c WHERE c.date = :date
        )
        ORDER BY rand()
        LIMIT 1
        """)
    Medic selectMedicWithSpecialtyInDate(Specialty specialty, LocalDateTime date);

    @Query("""
        SELECT m.active FROM Medic m WHERE m.id = :idMedic
    """)
    Boolean findActiveMedicById(Long idMedic);
}
