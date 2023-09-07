package med.jsrdev.api.user;

public record User(
        Integer userId,
        Long id,
        String title,
        String body
) { }