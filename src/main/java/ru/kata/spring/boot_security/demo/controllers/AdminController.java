package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.configs.PasswordConfig;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;



@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PasswordConfig passwordConfig;

    @GetMapping()
    public String showUsersList(Principal principal, Model model){
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("roles", roleService.getAllRoles());
        model.addAttribute("user", new User());
        model.addAttribute("currentUser", userService.getUserByUsername(principal.getName()));
        return "users-list";
    }

    @GetMapping("/new")
    public String create(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("roleList", roleService.getAllRoles());
        return "user-create";
    }

    @PostMapping("/new")
    public String saveUser(@ModelAttribute("user") User user, @RequestParam("newRoles") String[] newRoles) {

        for (int i = 0; i < newRoles.length; i++) {
            user.addRole(roleService.getRoleByName(newRoles[i]));
        }
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

    @GetMapping("/edit")
    public String edit(Model model, @RequestParam(value = "id") int id) {
        model.addAttribute("user", userService.getUserById(id));
        return "user-update";
    }

    @PutMapping("update/{id}")
    public String update(@ModelAttribute("user") User user, @RequestParam("userRoles") String[] newRoles) {
        Set<Role> roleSet = new HashSet<>();
        for (int i = 0; i < newRoles.length; i++) {
            roleSet.add(roleService.getRoleByName(newRoles[i]));
        }
        user.setRoles(roleSet);
        userService.updateUser(user);
        return "redirect:/admin";
    }






}
