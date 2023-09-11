package med.jsrdev.api.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@SuppressWarnings("all")
@Table(name="users")
@Entity(name = "User")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class User {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        Long id;
        @Column(name="user_id")
        Integer userId;
        String title;
        String body;

        public User(AddUserData user) {
                this.id = user.id();
                this.userId = user.userId();
                this.title = user.title();
                this.body = user.body();
        }
}