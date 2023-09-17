package med.jsrdev.api.domain.user_example;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AddUserExampleData(
        @NotNull(message = "{userId.required}")
        Integer userId,
        @NotBlank(message = "{title.required}")
        String title,
        @NotBlank(message = "{body.required}")
        String body
) {
}
