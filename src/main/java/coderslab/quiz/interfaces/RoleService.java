package coderslab.quiz.interfaces;

import pl.coderslab.examplesecurity.entity.Role;

public interface RoleService {
    void save(Role role);

    Role findOneByName(String admin);
}
