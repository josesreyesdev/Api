package med.jsrdev.api.domain.user_example;

import jakarta.validation.constraints.NotNull;

public record UpdateUserExampleData(@NotNull Long id, String title, String body) {
}
