//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//import ru.kata.spring.boot_security.demo.model.Role;
//import ru.kata.spring.boot_security.demo.model.User;
//import ru.kata.spring.boot_security.demo.service.RoleService;
//import ru.kata.spring.boot_security.demo.service.UserService;
//
//import javax.annotation.PostConstruct;
//import java.util.HashSet;
//import java.util.Set;
//
//@Component
//public class Data {
//
//
//    private final RoleService roleService;
//
//    private final UserService userService;
//
//    private final PasswordEncoder passwordEncoder;
//
//    public Data(RoleService roleService, UserService userService, PasswordEncoder passwordEncoder) {
//        this.roleService = roleService;
//        this.userService = userService;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//
//    @PostConstruct
//    void init() {
//
//        Role roleAdmin = new Role("ADMIN");
//        Role roleUser = new Role("USER");
//
//        Set<Role> adminRoleSet = new HashSet<>();
//        Set<Role> userRoleSet = new HashSet<>();
//
//        adminRoleSet.add(roleAdmin);
//        adminRoleSet.add(roleUser);
//        userRoleSet.add(roleUser);
//
//        User admin = new User("Admin", "Admin", "Admin_name", "Admin_last_name", 99, "admin@mail.ru", adminRoleSet);
//        User user = new User("User", "User", "User_name", "User_last_name", 66, "user@mail.ru", userRoleSet);
//
//        roleService.saveRole(roleAdmin);
//        roleService.saveRole(roleUser);
//        userService.saveUser(admin);
//        userService.saveUser(user);
//
//    }
//}
