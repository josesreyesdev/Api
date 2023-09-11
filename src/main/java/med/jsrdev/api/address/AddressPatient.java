package med.jsrdev.api.address;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@SuppressWarnings("all")
@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AddressPatient {
    private String district;
    private String city;
    private Integer number;
    private String complement;
    private String urbanization;
    @Column(name = "postal_code")
    private String postalCode;
    private Integer province;

    public AddressPatient(PatientAddressData address) {
        this.district = address.district();
        this.city = address.city();
        this.number = address.number();
        this.complement = address.complement();
        this.urbanization = address.urbanization();
        this.postalCode = address.postalCode();
        this.province = address.province();
    }

    public AddressPatient updateAddress(PatientAddressData address) {
        this.district = address.district();
        this.city = address.city();
        this.number = address.number();
        this.complement = address.complement();
        this.urbanization = address.urbanization();
        this.postalCode = address.postalCode();
        this.province = address.province();
        return this;
    }
}
