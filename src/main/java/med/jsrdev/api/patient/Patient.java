package med.jsrdev.api.patient;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.jsrdev.api.address.Address;

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
    private String identityDocument;
    private String phone;

    @Embedded
    private Address address;

    public Patient(PatientRegistrationData patientRegistrationData) {
        this.email = patientRegistrationData.email();
        this.identityDocument = patientRegistrationData.identityDocument();
        this.phone = patientRegistrationData.phone();
        this.address = new Address(patientRegistrationData.address());
    }
}
