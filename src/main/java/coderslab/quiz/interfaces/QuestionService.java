package coderslab.quiz.interfaces;

import coderslab.quiz.entities.Question;

import java.util.List;

public interface QuestionService {
    boolean doesQuestionExist(String questionQuery);
    Question findById(Long id);
    void save(Question question);
    void delete(Long id);
    void update(Question question);
    List<Question> findAll();
    List<Question> findAllinCategory(String categoryName);
}
