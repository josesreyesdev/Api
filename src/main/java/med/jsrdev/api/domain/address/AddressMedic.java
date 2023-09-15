package med.jsrdev.api.domain.address;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AddressMedic {
    private String street;
    private String district;
    private String city;
    private Integer number;
    private String complement;

    public AddressMedic(MedicAddressData address) {
        this.street = address.street();
        this.district = address.district();
        this.city = address.city();
        this.number = address.number();
        this.complement = address.complement();
    }

    public AddressMedic updateAddress(MedicAddressData address) {
        this.street = address.street();
        this.district = address.district();
        this.city = address.city();
        this.number = address.number();
        this.complement = address.complement();
        return this;
    }
}
