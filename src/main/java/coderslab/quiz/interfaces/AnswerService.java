package coderslab.quiz.interfaces;

import coderslab.quiz.entities.Answer;
import coderslab.quiz.entities.Question;

import java.util.List;

public interface AnswerService {
    boolean doesTrueAnswerExist();
    List<Answer> findByQuestion(Question question);
    Answer findByID(Long id);
    void save(Answer answer);
    void delete(Long id);
    Answer findByText(String text);

}
