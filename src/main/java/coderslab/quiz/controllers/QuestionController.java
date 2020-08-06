package coderslab.quiz.controllers;

import coderslab.quiz.entities.Question;
import coderslab.quiz.interfaces.AnswerService;
import coderslab.quiz.interfaces.CategoryService;
import coderslab.quiz.interfaces.QuestionService;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Map;

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
                               ModelMap modelMap, Model model, String pictureLink, Boolean pictureIncluded, String pictureLocation) {

        System.out.println("wartość checkboxa pictureIncluded " + pictureIncluded);
        System.out.println("wartość radiobuttona " + pictureLocation);
        System.out.println("aktualny link do obrazka" + question.getPicture());

        if (questionService.doesQuestionExist(question.getQuery()) && (question.getId() == null))
            bindingResult.addError(new FieldError("Question", "query", question.getQuery(), false, null, null, "takie pytanie już istnieje"));

        if (bindingResult.hasErrors()) {
            model.addAttribute("question", question);
            return "questionForm";
        }

        if (question.getId() == null)
            questionService.save(question);

        if (question.getPicture() == null)
            question.setPicture(questionService.findById(question.getId()).getPicture());

        if(pictureIncluded==null) {
            questionService.deletePicture(question);
            System.out.println("usuwam stary obrazek");
        }
        else if(pictureIncluded) {
            switch (pictureLocation) {
                case "local":
                    questionService.savePicture(question, file);
                    break;
                case "link":
                    questionService.deletePicture(question);
                    question.setPicture(pictureLink);
                    break;
                case "external":
                    System.out.println("TODO zewnętrzny hosting");

                    Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
                            "cloud_name", "marcin1136",
                            "api_key", "737373993878471",
                            "api_secret", "m59_i4tJ2O2pmBuTrGW0W5USEKA"));

                    questionService.deletePicture(question);

                    try {
                        Map uploadResults = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
                        System.out.println("link do wypchniętego obrazka"+uploadResults.get("url"));
                        System.out.println(" \n link do wypchniętego obrazka"+uploadResults.get("public_id"+"\n"));
                        question.setPicture(uploadResults.get("url").toString());
                    } catch (IOException e) {
                        e.printStackTrace();
                        System.out.println("hosting się zjebał");
                    }

                    break;
                default:
                    break;
            }

        }

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
        questionService.deletePicture(questionService.findById(id));
        questionService.delete(id);
        return "redirect:/questionList";
    }

    @GetMapping("/questionList")
    public String questionList(Model model) {
        model.addAttribute("questions", questionService.findAll());
        return "questionList";
    }
}
