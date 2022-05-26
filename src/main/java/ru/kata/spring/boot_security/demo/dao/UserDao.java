package ru.kata.spring.boot_security.demo.dao;



import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserDao {

    public void saveUser(User user);

    public void deleteUser(int id);

    public void updateUser(User user);

    public User getUserById(int id);

    public User getUserByUsername(String username);

    public List<User> getAllUsers();
}
