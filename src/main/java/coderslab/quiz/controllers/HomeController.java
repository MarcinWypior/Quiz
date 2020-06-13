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
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
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

    @ModelAttribute("categories")
    public List<Category> allCategories() {
        List<Category> categories = categoryService.findAll();
        return categories;
    }

    @GetMapping("/selectCategories")
    public String quiz(Model model){
        SelectedCategories selectedCategories=new SelectedCategories();
        model.addAttribute("selectedCategories",selectedCategories);
        return "selectCategories";
    }

    @PostMapping("/selectCategories")
    public String postForm(@ModelAttribute("selectedCategories") SelectedCategories selectedCategories,Model model) {
        ArrayList<Category> categories = selectedCategories.getCategories();

        //model.addAttribute(questionService.findAllinCategory(categories.get(1).getCategoryName()));

        Category category = categories.get(0);

        model.addAttribute(questionService.findAllinCategory(category.getCategoryName()).get(0));
        return "quiz";
    }

    @PostMapping("/results")
    @ResponseBody
    public String results(){


     return "wyświetlam wyniki";
    }

}
