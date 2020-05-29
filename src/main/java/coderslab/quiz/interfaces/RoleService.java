package coderslab.quiz.interfaces;

import coderslab.quiz.entities.Role;

public interface RoleService {
    void save(Role role);

    Role findOneByName(String admin);
}
