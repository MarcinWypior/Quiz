package coderslab.quiz.interfaces;

import coderslab.quiz.entities.Answer;
import coderslab.quiz.entities.Category;
import coderslab.quiz.entities.Question;

import java.util.List;

public interface QuestionService {
    boolean doesQuestionExist(String questionQuery);
    Question findById(Long id);
    Question findByQuery(String query);
    void save(Question question);
    void delete(Long id);
    void update(Question question);

    List<Answer> findTrueAnswers(Question question);

    List<Question> findAll();
    List<Question> findAllinCategory(String categoryName);
    List<Question> findAllinCategory(Category category);

    List<Answer> findAnswersForQuestion(Question question);
}
