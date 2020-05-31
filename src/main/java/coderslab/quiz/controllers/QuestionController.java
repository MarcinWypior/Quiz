package coderslab.quiz.controllers;

import coderslab.quiz.entities.Question;
import coderslab.quiz.interfaces.QuestionService;
import coderslab.quiz.repositories.AnswerRepository;
import coderslab.quiz.repositories.CategoryRepository;
import coderslab.quiz.repositories.QuestionRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
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
    private CategoryRepository categoryRepository;
    private QuestionRepository questionRepository;
    private AnswerRepository answerRepository;
    private QuestionService questionService;

    public QuestionController(QuestionService questionService,CategoryRepository categoryRepository, QuestionRepository questionRepository, AnswerRepository answerRepository) {
        this.categoryRepository = categoryRepository;
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
        this.questionService = questionService;
    }

    @GetMapping("/formQuestion")
    public String formQuestion(Model model) {
        model.addAttribute("question", new Question());
        model.addAttribute("category", questionService.findAll());
        return "questionForm";
    }

    @PostMapping("/formQuestion")
    public String questionForm(@Valid @ModelAttribute("question") Question question,
                               BindingResult bindingResult,
                               @RequestParam MultipartFile file,
                               ModelMap modelMap, Model model) {
        //TODO
        //bindingResult.addError(new FieldError("User","email","email jest zajety"));
        if (bindingResult.hasErrors()) {
            return "questionForm";
        }

        modelMap.addAttribute("file", file);
        String[] elemnts = file.getOriginalFilename().split("\\.");
        Path path1 = Paths.get("src/main/resources/static/uploadedFiles/" + Timestamp.valueOf(LocalDateTime.now()) + "." + elemnts[elemnts.length - 1]);

        try {
            InputStream inputStream = new ByteArrayInputStream(file.getBytes());
            Files.copy(
                    inputStream,
                    path1,
                    StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }

        question.setPicture(path1.toString());
        questionRepository.save(question);

        model.addAttribute("category", categoryRepository.findAll());
        model.addAttribute("answers", answerRepository.findByQuestion(questionRepository.getOne(question.getId())));

        return "questionForm";
    }

    @GetMapping("/formQuestion/{id}")
    public String getQuestionForm(Model model, @PathVariable long id) {
        model.addAttribute("question", questionRepository.getOne(id));
        model.addAttribute("category", categoryRepository.findAll());
        model.addAttribute("answers", answerRepository.findByQuestion(questionRepository.getOne(id)));
        return "questionForm";
    }

    @GetMapping("/deleteQuestion/{id}")
    public String deleteQuestionForm(Model model, @PathVariable long id) {
        questionRepository.delete(questionRepository.findById(id).get());
        return "redirect:/questionList";
    }

    @GetMapping("/questionList")
    public String questionList(Model model) {
        model.addAttribute("questions", questionRepository.findAll());
        model.addAttribute("category", categoryRepository.findAll());
        return "questionList";
    }
}
