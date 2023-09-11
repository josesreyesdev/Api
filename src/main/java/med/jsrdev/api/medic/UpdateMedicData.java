package med.jsrdev.api.medic;

import jakarta.validation.constraints.NotNull;
import med.jsrdev.api.address.MedicAddressData;

public record UpdateMedicData(@NotNull Long id, String name, String document, MedicAddressData address) {
}
