package coderslab.quiz.repositories;

import coderslab.quiz.entities.Answer;
import coderslab.quiz.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer,Long> {
    Answer findFirstByText(String text);
    Answer findFirstById(Long id);
    List<Answer> findByQuestion(Question question);
}
