package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;



@Repository
@Transactional
public class RoleDaoImpl implements RoleDao{

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void saveRole(Role role) {
        entityManager.persist(role);
    }

    @Override
    public void deleteRole(int id) {
        entityManager.remove(getRoleById(id));
    }

    @Override
    public void updateRole(Role role) {
        entityManager.merge(role);
    }

    @Override
    @Transactional(readOnly = true)
    public Role getRoleById(int id) {
        return entityManager.find(Role.class, id);
    }

    @Override
    @Transactional(readOnly = true)
    public Role getRoleByName(String name) {
        return entityManager.createQuery("SELECT r FROM Role r where r.role = :role", Role.class)
                .setParameter("role", name).getSingleResult();
    }

    @Override
    @Transactional(readOnly = true)
    @SuppressWarnings("unchecked")
    public List<Role> getAllRoles() {
        return entityManager.createQuery("select r from Role r").getResultList();
    }
}
