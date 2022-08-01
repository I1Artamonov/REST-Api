package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.kata.spring.boot_security.demo.service.UserService;
import java.security.Principal;


@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String users(){
        return "users";
    }

//    @GetMapping("/user")
//    public String userPage(Model model, Principal principal) {
//        model.addAttribute("user", userService.getUserByUsername(principal.getName()));
//        return "show";
//    }

//    @GetMapping("/user/{id}")
//    public String showUserPageById(Model model, @PathVariable("id") int id) {
//        model.addAttribute("user", userService.getUserById(id));
//        return "user";
//    }

}
