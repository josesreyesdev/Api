package med.jsrdev.api.domain.user_example;

public record GetUserExampleDataList(Long id, Integer userId, String title, String body) {
    public GetUserExampleDataList(UserExample user) {
        this(user.getId(), user.getUserId(), user.getTitle(), user.getBody());
    }
}
