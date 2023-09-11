package med.jsrdev.api.patient;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.jsrdev.api.address.AddressPatient;

@SuppressWarnings("all")
@Table(name="patients")
@Entity(name = "Patient")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    @Column(name = "identity_document")
    private String identityDocument;
    private String phone;
    @Embedded
    private AddressPatient address;

    public Patient(PatientRegistrationData patient) {
        this.name = patient.name();
        this.email = patient.email();
        this.identityDocument = patient.identityDocument();
        this.phone = patient.phone();
        this.address = new AddressPatient(patient.address());
    }
}
