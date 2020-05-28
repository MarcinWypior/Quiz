package coderslab.quiz.repositories;

import coderslab.quiz.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    Category findFirstById(Long id);
}
