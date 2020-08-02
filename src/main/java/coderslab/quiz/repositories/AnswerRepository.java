package coderslab.quiz.repositories;

import coderslab.quiz.entities.Answer;
import coderslab.quiz.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer,Long> {
    Answer findFirstByText(String text);
    Answer findFirstById(Long id);
    List<Answer> findByQuestion(Question question);

    @Query("select count(a) from Answer a WHERE a.question = :question and a.proper = true")
    int countProperAnswers(@Param("question") Question question);


//    @Query("SELECT b FROM Book b WHERE b.publisher = :publisher")
//    List<Book> findAllByPublisherUsingQuery(@Param("publisher") Publisher publisher);

}
