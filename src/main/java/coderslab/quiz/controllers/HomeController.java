package coderslab.quiz.controllers;

import coderslab.quiz.Model.SelectedCategories;
import coderslab.quiz.entities.Category;
import coderslab.quiz.interfaces.AnswerService;
import coderslab.quiz.interfaces.CategoryService;
import coderslab.quiz.interfaces.QuestionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class HomeController {
    private AnswerService answerService;
    private CategoryService categoryService;
    private QuestionService questionService;


    public HomeController(AnswerService answerService, CategoryService categoryService, QuestionService questionService)
    {
        this.questionService = questionService;
        this.answerService = answerService;
        this.categoryService = categoryService;
    }



    @GetMapping("/")
    public String home() { return "home"; }

    @ModelAttribute("categoriesAvailable")
    public List<Category> allCategories() {
        return categoryService.findAll();
    }


    @GetMapping("/selectCategories")
    public String quiz(Model model){
        SelectedCategories selectedCategories=new SelectedCategories();
        model.addAttribute("selectedCategories",selectedCategories);
        return "selectCategories";
    }

    @PostMapping("/selectCategories")
    public String postForm(@ModelAttribute ("selectedCategories") List<Category> categories) {
        return categories.toString();
    }




}
