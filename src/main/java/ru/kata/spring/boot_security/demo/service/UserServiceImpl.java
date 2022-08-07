package ru.kata.spring.boot_security.demo.service;


import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.configs.PasswordConfig;
import ru.kata.spring.boot_security.demo.dao.UserDao;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.HashSet;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final PasswordConfig passwordConfig;

    private final RoleServiceImpl roleService;


    public UserServiceImpl(UserDao userDao, PasswordConfig passwordConfig, RoleServiceImpl roleService) {
        this.userDao = userDao;
        this.passwordConfig = passwordConfig;
        this.roleService = roleService;

    }

    @Override
    public void saveUser(User user) {
        user.setPassword(passwordConfig.passwordEncoder().encode(user.getPassword()));
        user.setRoles(roleService.getPersistRolesByRoleSet(user.getRoles()));
        userDao.saveUser(user);
    }

    @Override
    public void deleteUser(int id) {
        User user = getUserById(id);
        user.setRoles(new HashSet<>());
        userDao.updateUser(user);
        userDao.deleteUser(getUserById(id));
    }

    @Override
    public void updateUser(User user) {
        user.setRoles(roleService.getPersistRolesByRoleSet(user.getRoles()));
        if (user.getPassword() == null) {
            user.setPassword(userDao.getUserById(user.getId()).getPassword());
        } else if ((user.getPassword())
                .equals(userDao.getUserById(user.getId()).getPassword())){
            user.setPassword(user.getPassword());
        } else {
            user.setPassword(passwordConfig.passwordEncoder().encode(user.getPassword()));
        }
        userDao.updateUser(user);
    }

    @Override
    public User getUserById(int id) {
        return userDao.getUserById(id);
    }

    @Override
    public User getUserByUsername(String username) {
        return userDao.getUserByUsername(username);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

}
