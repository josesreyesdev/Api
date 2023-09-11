package med.jsrdev.api.medic;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.jsrdev.api.address.AddressMedic;

@SuppressWarnings("all")
@Table(name="medics")
@Entity(name = "Medic")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Medic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String document;
    @Enumerated(EnumType.STRING)
    private Specialty specialty;
    @Embedded
    private AddressMedic address;

    public Medic(MedicalRegistrationData medic) {
        this.name = medic.name();
        this.email = medic.email();
        this.phone = medic.phone();
        this.document = medic.document();
        this.specialty = medic.specialty();
        this.address = new AddressMedic(medic.address());
    }
}
