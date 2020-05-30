package coderslab.quiz.controllers;

import coderslab.quiz.entities.User;
import coderslab.quiz.repositories.RoleRepository;
import coderslab.quiz.repositories.UserRepository;
import coderslab.quiz.service.UserServiceImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;


@Controller
public class RegistrationController {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;


    public RegistrationController(UserRepository userRepository,
                                  RoleRepository roleRepository,
                                  BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }


    @GetMapping("/registration")
    public String registrationGet(Model model) {
        model.addAttribute("user", new User());
        return "registrationForm";
    }

    @PostMapping("/registration")
    public String questionForm(@Valid @ModelAttribute User user, BindingResult bindingResult,Model model) {
        if (bindingResult.hasErrors()) {
            return "registrationForm";
        }

        UserServiceImpl userSerImp= new UserServiceImpl(userRepository,
                roleRepository,
                passwordEncoder);

        userSerImp.saveUser(user);

        return "home";
    }

}
