package coderslab.quiz.controllers;

import coderslab.quiz.Model.SelectedCategories;
import coderslab.quiz.interfaces.AnswerService;
import coderslab.quiz.interfaces.CategoryService;
import coderslab.quiz.interfaces.QuestionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;

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
    public List<String> allCategories() {
        List<String> categoriesString = categoryService.findAll().stream().map(e -> e.getCategoryName()).collect(Collectors.toList());
        return categoriesString;
    }


    @GetMapping("/selectCategories")
    public String quiz(Model model){
        SelectedCategories selectedCategories=new SelectedCategories();
        model.addAttribute("selectedCategories",selectedCategories);
        return "selectCategories";
    }

    @PostMapping("/selectCategories")
    @ResponseBody
    public String postForm(@ModelAttribute("selectedCategories") SelectedCategories selectedCategories) {
        return selectedCategories.getCategories().toString();
    }

}
