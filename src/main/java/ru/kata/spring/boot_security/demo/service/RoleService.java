package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.Role;
import java.util.List;


public interface RoleService {

    public void saveRole(Role role);

    public void deleteRole(int id);

    public void updateRole(Role role);

    public Role getRoleById(int id);

    public Role getRoleByName(String name);

    public List<Role> getAllRoles();

}
