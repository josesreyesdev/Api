package med.jsrdev.api.domain.user;

import jakarta.validation.constraints.NotNull;

public record UpdateUserData(@NotNull Long id, String title, String body) {
}