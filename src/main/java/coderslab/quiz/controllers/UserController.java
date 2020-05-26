package coderslab.quiz.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import coderslab.quiz.entities.User;
import coderslab.quiz.repositories.UserRepository;

@Controller
public class UserController {
    private UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/home")
    public String home(){
        //model.addAttribute("username","Użytkownik");
        return "home";
    }

    @GetMapping("/addUser")
    public String addUser(){
        User user=new User();
        user.setNickName("jakis");
        user.setEmail("emil@op.pl");
        userRepository.save(user);
        return "/home";
    }
}
