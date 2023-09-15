package med.jsrdev.api.domain.medic;

import jakarta.validation.constraints.NotNull;
import med.jsrdev.api.domain.address.MedicAddressData;

public record UpdateMedicData(@NotNull Long id, String name, String document, MedicAddressData address) {
}
