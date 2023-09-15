package med.jsrdev.api.domain.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AddUserData(
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
