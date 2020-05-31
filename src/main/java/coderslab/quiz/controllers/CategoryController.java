package coderslab.quiz.controllers;


import coderslab.quiz.entities.Category;
import coderslab.quiz.repositories.CategoryRepository;
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
public class CategoryController {
    private CategoryRepository categoryRepository;



    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository=categoryRepository;
    }

    //kategoria
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
        if (bindingResult.hasErrors()) {
            return "categoryForm";
        }

        boolean anyMatch = categoryRepository.findAll().stream().anyMatch(e -> e.getCategoryName().equalsIgnoreCase(category.getCategoryName()));

        if (anyMatch) {
            bindingResult.addError(new FieldError("Category","categoryName","taka kategoria już istnieje"));
            return "categoryForm";
        } else {
            categoryRepository.save(category);
        }
        return "redirect:/categoryList";
    }

    @GetMapping("/deleteCategory/{id}")
    public String deleteCategories(Model model, @PathVariable long id) {
        model.addAttribute("category", categoryRepository.findAll());
        categoryRepository.deleteById(id);
        return "redirect:/categoryList";
    }

    @GetMapping("/categoryList")
    public String categoriesList(Model model) {
        model.addAttribute("category", categoryRepository.findAll());
        return "categoryList";
    }
}
