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
        private Long id;
        @Column(name="user_id")
        private Integer userId;
        private String title;
        private String body;
        private Boolean active;


        public User(AddUserData user) {
                this.id = user.id();
                this.userId = user.userId();
                this.title = user.title();
                this.body = user.body();
                this.active = true;
        }

        public void updateUser(UpdateUserData updateUser) {
                if (updateUser.title() != null) {
                        this.title = updateUser.title();
                }
                if (updateUser.body() != null) {
                        this.body = updateUser.body();
                }
        }

        public void deactivateUser() {
                this.active = false;
        }
}