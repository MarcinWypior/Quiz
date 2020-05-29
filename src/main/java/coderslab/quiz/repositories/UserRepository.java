package coderslab.quiz.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import coderslab.quiz.entities.User;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);
}
