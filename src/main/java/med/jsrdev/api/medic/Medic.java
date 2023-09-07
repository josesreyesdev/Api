package med.jsrdev.api.medic;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.jsrdev.api.address.Address;

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
    private String document;
    @Enumerated(EnumType.STRING)
    private Specialty specialty;
    @Embedded
    private Address address;

    public Medic(MedicalRegistrationData medicalRegistrationData) {
        this.name = medicalRegistrationData.name();
        this.email = medicalRegistrationData.email();
        this.document = medicalRegistrationData.document();
        this.specialty = medicalRegistrationData.specialty();
        this.address = new Address(medicalRegistrationData.address());
    }
}
