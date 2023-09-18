package med.jsrdev.api.domain.user_example;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserExampleRepository extends JpaRepository<UserExample, Long> {
    Page<UserExample> findByActiveTrue(Pageable pagination);
}