package coderslab.quiz.service;


import coderslab.quiz.entities.Answer;
import coderslab.quiz.entities.Question;
import coderslab.quiz.interfaces.AnswerService;
import coderslab.quiz.repositories.AnswerRepository;
import coderslab.quiz.repositories.CategoryRepository;
import coderslab.quiz.repositories.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public Answer findByText(String text) {
        return answerRepository.findFirstByText(text);
    }
}
