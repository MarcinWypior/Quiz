package coderslab.quiz.service;


import coderslab.quiz.entities.Answer;
import coderslab.quiz.entities.Question;
import coderslab.quiz.interfaces.AnswerService;
import coderslab.quiz.repositories.AnswerRepository;
import coderslab.quiz.repositories.CategoryRepository;
import coderslab.quiz.repositories.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnswerServiceImpl implements AnswerService {
    private final CategoryRepository categoryRepository;
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;

    public AnswerServiceImpl(
            CategoryRepository categoryRepository, QuestionRepository questionRepository,AnswerRepository answerRepository
    ) {
        this.categoryRepository = categoryRepository;
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
    }

    @Override
    public boolean doesTrueAnswerExist() {
        return false;
    }

    @Override
    public List<Answer> findByQuestion(Question question) {
        return answerRepository.findByQuestion(question);
    }

    @Override
    public void save(Answer answer) {
        answerRepository.save(answer);
    }

    @Override
    public Answer findByID(Long id) {
        return answerRepository.findFirstById(id);
    }

    @Override
    public void delete(Long id) {
        answerRepository.deleteById(id);
    }


    @Override
    public int countProperAnswers(Question question) {
        return answerRepository.countProperAnswers(question);
    }


    @Override
    public boolean doAnswerExist(Question question, String answerText){

        List<Answer> allAnswers = answerRepository.findByQuestion(question);
        List<Answer> repeatedAnswers = allAnswers.stream().filter(e -> e.getText().trim().equalsIgnoreCase(answerText)).collect(Collectors.toList());

        if(repeatedAnswers.stream().findAny().isPresent()) {
            return true;
        }
        else return false;

    }
}
