package med.jsrdev.api.user;

public record GetUserDataList(Integer userId, String title, String body) {
    public GetUserDataList(User user) {
        this(user.userId, user.title, user.body);
    }
}
