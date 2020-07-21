package coderslab.quiz.controllers;

import coderslab.quiz.entities.Question;
import coderslab.quiz.interfaces.AnswerService;
import coderslab.quiz.interfaces.CategoryService;
import coderslab.quiz.interfaces.QuestionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Controller
public class QuestionController {
    private QuestionService questionService;
    private CategoryService categoryService;
    private AnswerService answerService;

    public QuestionController(QuestionService questionService,
                              CategoryService categoryService,
                              AnswerService answerService) {
        this.questionService = questionService;
        this.categoryService = categoryService;
        this.answerService = answerService;
    }

    @GetMapping("/formQuestion")
    public String formQuestion(Model model) {
        model.addAttribute("question", new Question());
        model.addAttribute("category", categoryService.findAll());
        return "questionForm";
    }

    @PostMapping("/formQuestion")
    public String questionForm(@Valid @ModelAttribute("question") Question question,
                               BindingResult bindingResult,
                               @RequestParam MultipartFile file,
                               ModelMap modelMap, Model model) {
        //TODO
        if(questionService.doesQuestionExist(question.getQuery())&&(question.getId()==null))
        bindingResult.addError(new FieldError("Question","query","takie pytanie już istnieje"));

        System.out.println("taki jest link do obrazka" +question.getPicture());

        if (bindingResult.hasErrors()) {
            return "questionForm";
        }

        if(question.getId()==null)
            questionService.save(question);

        if (question.getPicture()==null)
            question.setPicture(questionService.findById(question.getId()).getPicture());

        modelMap.addAttribute("file", file);
        String[] elemnts = file.getOriginalFilename().split("\\.");
        Path path1 = Paths.get("src/main/webapp/resources/uploaded/pictures/" + Timestamp.valueOf(LocalDateTime.now()) + "." + elemnts[elemnts.length - 1]);

        if (file.getSize()>0) {

            if(question.getPicture() != null) {


                try {
                    Files.delete(Paths.get("src/main/webapp/", questionService.findById(question.getId()).getPicture().substring(6)));
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }


            try {
                InputStream inputStream = new ByteArrayInputStream(file.getBytes());
                Files.copy(
                        inputStream,
                        path1,
                        StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if(file.getSize()>10)
            question.setPicture("../../"+path1.toString().substring(16));
            //System.out.println("aktualna scieżka do obrazka " + question.getPicture());
            questionService.save(question);

        model.addAttribute("category", categoryService.findAll());
        model.addAttribute("answers", answerService.findByQuestion(questionService.findById(question.getId())));
        return "questionForm";



    }

    @GetMapping("/formQuestion/{id}")
    public String getQuestionForm(Model model, @PathVariable long id) {
        model.addAttribute("question", questionService.findById(id));
        model.addAttribute("category", categoryService.findAll());
        model.addAttribute("answers", answerService.findByQuestion(questionService.findById(id)));
        return "questionForm";
    }

    @GetMapping("/deleteQuestion/{id}")
    public String deleteQuestionForm(@PathVariable long id) {
        questionService.delete(id);
        return "redirect:/questionList";
    }

    @GetMapping("/questionList")
    public String questionList(Model model) {
        model.addAttribute("questions", questionService.findAll());
        return "questionList";
    }
}
