package coderslab.quiz.controllers;


import coderslab.quiz.entities.Answer;
import coderslab.quiz.entities.Question;
import coderslab.quiz.repositories.AnswerRepository;
import coderslab.quiz.repositories.CategoryRepository;
import coderslab.quiz.repositories.QuestionRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class AnswerController {

    private CategoryRepository categoryRepository;
    private QuestionRepository questionRepository;
    private AnswerRepository answerRepository;

    public AnswerController(CategoryRepository categoryRepository, QuestionRepository questionRepository, AnswerRepository answerRepository) {
        this.categoryRepository = categoryRepository;
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
    }


    @GetMapping("/formAnswer/{id}")
    public String answerForm(Model model, @PathVariable Long id) {
        model.addAttribute("answer", new Answer());
        model.addAttribute("question", questionRepository.findById(id).get());
        return "answerForm";
    }

    @PostMapping("/formAnswer/{question_id}")
    public String postAnswerForm(@Valid @ModelAttribute Answer answer, BindingResult bindingResult, @PathVariable long question_id, Model model) {
        if (bindingResult.hasErrors()) {
            return "answerForm";
        }

        Question question = questionRepository.findById(question_id).get();
        answer.setQuestion(question);
        answerRepository.save(answer);

        model.addAttribute("question", question);
        model.addAttribute("category", categoryRepository.findAll());
        model.addAttribute("answers", answerRepository.findByQuestion(question));
        return "questionForm";
    }

    @GetMapping("/deleteAnswer/{id}")
    public String deleteAnswer(Model model, @PathVariable long id) {

        Answer firstById = answerRepository.findFirstById(id);
        Question question = firstById.getQuestion();
        answerRepository.delete(firstById);

        model.addAttribute("question", question);
        model.addAttribute("category", categoryRepository.findAll());
        model.addAttribute("answers", answerRepository.findByQuestion(question));
        return "questionForm";
    }

}
