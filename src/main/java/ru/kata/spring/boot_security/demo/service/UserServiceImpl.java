package ru.kata.spring.boot_security.demo.service;

import org.springframework.stereotype.Service;

import ru.kata.spring.boot_security.demo.configs.PasswordConfig;
import ru.kata.spring.boot_security.demo.dao.UserDao;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final PasswordConfig passwordConfig;

    public UserServiceImpl(UserDao userDao, PasswordConfig passwordConfig) {
        this.userDao = userDao;
        this.passwordConfig = passwordConfig;
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(passwordConfig.passwordEncoder().encode(user.getPassword()));
        userDao.saveUser(user);
    }

    @Override
    public void deleteUser(int id) {
        userDao.deleteUser(id);
    }

    @Override
    public void updateUser(User user) {
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
