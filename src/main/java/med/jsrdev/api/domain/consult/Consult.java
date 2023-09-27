package med.jsrdev.api.domain.consult;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.jsrdev.api.domain.medic.Medic;
import med.jsrdev.api.domain.patient.Patient;

import java.time.LocalDateTime;

@SuppressWarnings("all")
@Table(name = "consults")
@Entity(name = "Consults")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Consult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_medic")
    private Medic medic;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_patient")
    private Patient patient;

    private LocalDateTime date;

    @Column (name = "motivo_cancel")
    @Enumerated(EnumType.STRING)
    private MotivoCancelamiento motivoCancelamiento;

    public Consult(Medic medic, Patient patient, LocalDateTime date) {
        this.medic = medic;
        this.patient = patient;
        this.date = date;
    }

    public void cancel(MotivoCancelamiento motivo) {
        this.motivoCancelamiento = motivo;
    }
}
