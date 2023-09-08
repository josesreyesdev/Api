package med.jsrdev.api.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserRegistrationData(
        @NotNull
        Long id,
        @NotNull
        Integer userId,
        @NotBlank
        String title,
        @NotBlank
        String body
) {
}
