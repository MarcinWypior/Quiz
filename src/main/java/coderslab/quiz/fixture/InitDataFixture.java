package coderslab.quiz.fixture;


import coderslab.quiz.entities.Role;
import coderslab.quiz.entities.User;
import coderslab.quiz.interfaces.RoleService;
import coderslab.quiz.interfaces.UserService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class InitDataFixture {

    private final UserService userService;
    private final RoleService roleService;

    public InitDataFixture(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    public void initRoles() {
        Role roleAdmin = new Role();
        roleAdmin.setName("ROLE_ADMIN");
        roleService.save(roleAdmin);

        Role roleUser = new Role();
        roleUser.setName("ROLE_USER");
        roleService.save(roleUser);
    }

    public void initUsers() {

        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(roleService.findOneByName("ROLE_ADMIN"));

        User admin = new User();
        admin.setNickName("admin");
        admin.setPassword("admin");
        admin.setRoles(adminRoles);
        userService.saveUser(admin);

        Set<Role> userRoles = new HashSet<>();
        userRoles.add(roleService.findOneByName("ROLE_USER"));

        User user = new User();
        user.setNickName("user");
        user.setPassword("user");
        user.setRoles(userRoles);

        userService.saveUser(user);

        user.getRoles().forEach(e -> System.out.println(e.getName()));
    }
}
