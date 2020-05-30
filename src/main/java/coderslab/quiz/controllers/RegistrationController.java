package coderslab.quiz.controllers;

import coderslab.quiz.entities.User;
import coderslab.quiz.repositories.RoleRepository;
import coderslab.quiz.repositories.UserRepository;
import coderslab.quiz.service.UserServiceImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

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
    public String questionForm(@Valid @ModelAttribute User user, BindingResult bindingResult, Model model) {

        boolean nameOccupied = userRepository.findAll().stream().anyMatch(e -> (e.getUsername().equals(user.getUsername())));
        boolean emailOccupied = userRepository.findAll().stream().anyMatch(e -> (e.getEmail().equals(user.getEmail())));

        if (emailOccupied) {
            bindingResult.addError(new FieldError("User","email","email jest zajety"));
            model.addAttribute("user",user);
        }
        if(nameOccupied) {
            bindingResult.addError(new FieldError("User","username","nazwa urzytkownika jest zajeta"));
            model.addAttribute("user",user);
        }
        if (bindingResult.hasErrors()) {
            return "registrationForm";
        }

        UserServiceImpl userSerImp= new UserServiceImpl(userRepository,
                roleRepository,
                passwordEncoder);

        userRepository.findByUsername(user.getUsername());
        //TODO

            userSerImp.saveUser(user);

        return "home";
    }

}
