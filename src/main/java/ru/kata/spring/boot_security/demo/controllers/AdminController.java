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
    PasswordConfig passwordConfig;

    @GetMapping()
    public String showUsersList(Model model){
        model.addAttribute("usersList", userService.getAllUsers());
        return "admin";
    }

    @GetMapping("/new")
    public String create(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("roleList", roleService.getAllRoles());
        return "create";
    }

    @PostMapping("/new")
    public String saveUser(@ModelAttribute("user") User user, @RequestParam("listRoles") String[] newRoles) {

        for (int i = 0; i < newRoles.length; i++) {
            user.addRole(roleService.getRoleByName(newRoles[i]));
        }
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/edit")
    public String edit(Model model, @RequestParam(value = "id") int id) {
        model.addAttribute("user", userService.getUserById(id));
        return "update";
    }

    @PatchMapping("update/{id}")
    public String update(@ModelAttribute("user") User user, @RequestParam("listRoles") String[] newRoles) {
        Set<Role> roleSet = new HashSet<>();
        for (int i = 0; i < newRoles.length; i++) {
            roleSet.add(roleService.getRoleByName(newRoles[i]));
        }
        user.setRoles(roleSet);
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }




}
