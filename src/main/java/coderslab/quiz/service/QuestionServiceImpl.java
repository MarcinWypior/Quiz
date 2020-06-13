package coderslab.quiz.service;

import coderslab.quiz.entities.Answer;
import coderslab.quiz.entities.Category;
import coderslab.quiz.entities.Question;
import coderslab.quiz.interfaces.QuestionService;
import coderslab.quiz.repositories.QuestionRepository;
import coderslab.quiz.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {
    private final UserRepository userRepository;
    private final QuestionRepository questionRepository;

    public QuestionServiceImpl(
            UserRepository userRepository,
            QuestionRepository questionRepository
    ) {
        this.questionRepository = questionRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Question> findAll() {
        return questionRepository.findAll();
    }

    @Override
    public List<Question> findAllinCategory(String categoryName)
    {
        return questionRepository.findAllByCategoryCategoryName(categoryName);
    }

    @Override
    public boolean doesQuestionExist(String questionQuery) {
        if(questionRepository.findFirstByQuery(questionQuery)!=null)
        return true;
        else
        return false;
    }

    @Override
    public Question findById(Long id) {
        return questionRepository.findById(id).get();
    }

    @Override
    public void save(Question question) {
        questionRepository.save(question);
    }

    @Override
    public void delete(Long id) {
        questionRepository.deleteById(id);
    }

    @Override
    public void update(Question question) {
    }

    @Override
    public List<Answer> findAnswersForQuestion(Question question) {
        return question.getAnswerList();
    }

    public Question findByQuery(String query){
       return questionRepository.findByQuery(query);
    }

    @Override
    public List<Question> findAllinCategory(Category category) {
        return questionRepository.findAllByCategory(category);
    }
}
