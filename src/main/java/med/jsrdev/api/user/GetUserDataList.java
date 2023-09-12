package med.jsrdev.api.user;

public record GetUserDataList(Long id, Integer userId, String title, String body) {
    public GetUserDataList(User user) {
        this(user.getId(), user.getUserId(), user.getTitle(), user.getBody());
    }
}
