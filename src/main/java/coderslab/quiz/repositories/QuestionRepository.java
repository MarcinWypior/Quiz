package coderslab.quiz.repositories;

import coderslab.quiz.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question,Long> {
    Question findFirstByQuery(String query);
    List<Question> findAllByCategoryCategoryName(String categoryName);
    Question findByQuery(String query);
}
