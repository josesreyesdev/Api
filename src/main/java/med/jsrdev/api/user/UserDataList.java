package med.jsrdev.api.user;

public record UserDataList(Integer userId, String title, String body) {
    public UserDataList(User user) {
        this(user.userId, user.title, user.body);
    }
}
