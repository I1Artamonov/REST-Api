package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.HashSet;
import java.util.Set;

@Controller
public class StartController {

    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;

    @GetMapping("/start")
    void init() {

        Role roleAdmin = new Role("ROLE_ADMIN");
        Role roleUser = new Role("ROLE_USER");

        Set<Role> adminRoleSet = new HashSet<>();
        Set<Role> userRoleSet = new HashSet<>();

        adminRoleSet.add(roleAdmin);
        adminRoleSet.add(roleUser);
        userRoleSet.add(roleUser);

        User admin = new User("Admin", "Admin", "Admin_name", "Admin_last_name", 99, "admin@mail.ru", adminRoleSet);
        User user = new User("User", "User", "User_name", "User_last_name", 66, "user@mail.ru", userRoleSet);

        roleService.saveRole(roleAdmin);
        roleService.saveRole(roleUser);
        userService.saveUser(admin);
        userService.saveUser(user);

    }
}
