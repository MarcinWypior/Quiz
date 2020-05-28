package coderslab.quiz.controllers;


import coderslab.quiz.entities.Category;
import coderslab.quiz.entities.Question;
import coderslab.quiz.repositories.CategoryRepository;
import coderslab.quiz.repositories.QuestionRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import coderslab.quiz.repositories.UserRepository;

import javax.validation.Valid;
import java.util.List;

@Controller
public class UserController {
    private UserRepository userRepository;
    private CategoryRepository categoryRepository;
    private QuestionRepository questionRepository;

    public UserController(UserRepository userRepository,CategoryRepository categoryRepository,QuestionRepository questionRepository) {
        this.userRepository = userRepository;
        this.categoryRepository= categoryRepository;
        this.questionRepository =questionRepository;
    }

    @GetMapping("/home")
    public String home(){
        //model.addAttribute("username","Użytkownik");
        return "home";
    }

    @GetMapping("/formCategory")
    public String getForm(Model model) {
        model.addAttribute("category", new Category());
        return "categoryForm";
    }

    @GetMapping("/formCategory/{id}")
    public String getForm(Model model, @PathVariable long id) {
        model.addAttribute("category", categoryRepository.findFirstById(id));
        return "categoryForm";
    }

    @PostMapping("/formCategory")
    public String postForm(@Valid @ModelAttribute Category category, BindingResult bindingResult) {
        if (bindingResult.hasErrors())   {
            return "categoryForm";
        }

        boolean exists = categoryRepository.findAll().stream().anyMatch(e -> e.getCategoryName() == category.getCategoryName());
        if(!exists) {
            categoryRepository.save(category);
        }else {
            bindingResult.addError( new ObjectError("category","taka kategoria już istnieje") );
            return "categoryForm";
        }
        return "redirect:/categoryList";
    }

    @GetMapping("/categoryList")
    public String categoriesList(Model model){
        model.addAttribute("category",categoryRepository.findAll());
        return "categoryList";
    }

    //question


    @GetMapping("/formQuestion")
    public String formQuestion(Model model){
        model.addAttribute("question",new Question());
        model.addAttribute("category",categoryRepository.findAll());
                return "questionForm";
    }

    @PostMapping("/formQuestion")
    public String questionForm(@Valid @ModelAttribute Question question, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "questionForm";
        }

        questionRepository.save(question);

        return "redirect:/questionList";
    }



}
