package med.jsrdev.api.domain.medic;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.jsrdev.api.domain.address.AddressMedic;

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

    private Boolean active;

    public Medic(AddMedicData medic) {
        this.active = true;
        this.name = medic.name();
        this.email = medic.email();
        this.phone = medic.phone();
        this.document = medic.document();
        this.specialty = medic.specialty();
        this.address = new AddressMedic(medic.address());
    }

    public void updateMedic(UpdateMedicData updateMedic) {
        if (updateMedic.name() != null) {
            this.name = updateMedic.name();
        }
        if (updateMedic.document() != null) {
            this.document = updateMedic.document();
        }
        if (updateMedic.address() != null) {
            this.address = address.updateAddress(updateMedic.address());
        }
    }

    public void deactiveMedic() {
        this.active = false;
    }
}
