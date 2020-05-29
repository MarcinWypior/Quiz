package coderslab.quiz.controllers;


import coderslab.quiz.entities.Category;
import coderslab.quiz.entities.Question;
import coderslab.quiz.repositories.CategoryRepository;
import coderslab.quiz.repositories.QuestionRepository;
import coderslab.quiz.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
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


    @GetMapping("/upload")
    public String uploadFile(){
        return "fileUpload";
    }

    @PostMapping("/uploadFileWithAddtionalData")
    public String submit(
            @RequestParam MultipartFile file, @RequestParam String name,
            @RequestParam String email, ModelMap modelMap) {

        modelMap.addAttribute("name", name);
        modelMap.addAttribute("email", email);
        modelMap.addAttribute("file", file);


        Path path1 = Paths.get("/home/marcin/CL/QUIZ/src/main/resources/uploadedFiles",file.getOriginalFilename());

        try {
            InputStream inputStream = new ByteArrayInputStream(file.getBytes());
            Files.copy(
                    inputStream,
                    path1,
                    StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }




        return "fileUpload";
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

        boolean anyMatch = categoryRepository.findAll().stream().anyMatch(e -> e.getCategoryName().equalsIgnoreCase(category.getCategoryName()));

        if(anyMatch) {
            bindingResult.addError( new ObjectError("categoryName","taka kategoria już istnieje") );
            return "categoryForm";
        }else {
            categoryRepository.save(category);
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

    @GetMapping("/formQuestion/{id}")
    public String getQuestionForm(Model model, @PathVariable long id) {
        model.addAttribute("question", questionRepository.findById(id));
        return "questionForm";
    }


    @GetMapping("/questionList")
    public String questionList(Model model){
        model.addAttribute("questions",questionRepository.findAll());
        model.addAttribute("category",categoryRepository.findAll());
        return "questionList";
    }

}
