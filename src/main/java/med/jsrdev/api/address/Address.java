package med.jsrdev.api.address;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    private String street;
    private String district;
    private String city;
    private Integer number;
    private String complement;

    public Address(AddressData address) {
        this.street = address.street();
        this.district = address.district();
        this.city = address.city();
        this.number = address.number();
        this.complement = address.complement();
    }
}
