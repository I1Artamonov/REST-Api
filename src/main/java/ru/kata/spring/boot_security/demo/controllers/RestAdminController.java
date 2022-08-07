package ru.kata.spring.boot_security.demo.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.configs.PasswordConfig;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/admin/users")
public class RestAdminController {
    private final UserService userService;
    private final RoleService roleService;
    private final PasswordConfig passwordConfig;

    @Autowired
    public RestAdminController(UserService userService, RoleService roleService, PasswordConfig passwordConfig) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordConfig = passwordConfig;
    }

    @GetMapping()
    public List<User> userList() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable int id) {
        return userService.getUserById(id);
    }

    //проверить как сохраняются роли
    @PostMapping()
    public List<User> addUser(@RequestBody User user) {
        userService.saveUser(user);
        return userService.getAllUsers();
    }

    //проверить как обновляются роли и пароль
    @PutMapping()
    public User update(@RequestBody User user) {
        System.out.println(user.toString());
        userService.updateUser(user);
        return user;
    }

    @DeleteMapping("/{id}")
    public List<User> deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        return userService.getAllUsers();
    }

    @GetMapping("/roles")
    public List<Role> getRoles() {
        return roleService.getAllRoles();
    }
}
