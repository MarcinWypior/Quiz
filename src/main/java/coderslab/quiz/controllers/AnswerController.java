package coderslab.quiz.controllers;


import coderslab.quiz.entities.Answer;
import coderslab.quiz.entities.Question;
import coderslab.quiz.interfaces.AnswerService;
import coderslab.quiz.interfaces.CategoryService;
import coderslab.quiz.interfaces.QuestionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class AnswerController {

    private AnswerService answerService;
    private CategoryService categoryService;
    private QuestionService questionService;


    public AnswerController(AnswerService answerService, CategoryService categoryService,QuestionService questionService)
    {
        this.questionService = questionService;
        this.answerService = answerService;
        this.categoryService = categoryService;
    }


    @GetMapping("/formAnswer/{id}")
    public String answerForm(Model model, @PathVariable Long id) {
        model.addAttribute("answer", new Answer());
        model.addAttribute("question", questionService.findById(id));
        return "answerForm";
    }

    @PostMapping("/formAnswer/{question_id}")
    public String postAnswerForm(@Valid @ModelAttribute Answer answer, BindingResult bindingResult, @PathVariable long question_id, Model model) {

        if(answerService.findByText(answer.getText())!=null)
            bindingResult.addError(new FieldError("Answer","text","taka odpowiedz już istnieje"));

        if (bindingResult.hasErrors()) {
            return "answerForm";
        }

        Question question = questionService.findById(question_id);
        answer.setQuestion(question);
        answerService.save(answer);

        model.addAttribute("question", question);
        model.addAttribute("category", categoryService.findAll());
        model.addAttribute("answers", answerService.findByQuestion(question));
        return "questionForm";
    }

    @GetMapping("/deleteAnswer/{id}")
    public String deleteAnswer(Model model, @PathVariable long id) {

//        answerService.delete(id);
//        Answer firstById = answerService.findByID(id);
//        answerRepository.delete(firstById);
        Question question = answerService.findByID(id).getQuestion();
        answerService.delete(id);
        model.addAttribute("question",question);
        model.addAttribute("category", categoryService.findAll());
        model.addAttribute("answers", answerService.findByQuestion(question));
        return "questionForm";
    }
}
