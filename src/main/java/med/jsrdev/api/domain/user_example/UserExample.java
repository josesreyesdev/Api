package med.jsrdev.api.domain.user_example;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@SuppressWarnings("all")
@Table(name="user_examples")
@Entity(name = "UserExample")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class UserExample {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @Column(name="user_id")
        private Integer userId;
        private String title;
        private String body;
        private Boolean active;


        public UserExample(AddUserExampleData user) {
                this.userId = user.userId();
                this.title = user.title();
                this.body = user.body();
                this.active = true;
        }

        public void updateUser(UpdateUserExampleData updateUser) {
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