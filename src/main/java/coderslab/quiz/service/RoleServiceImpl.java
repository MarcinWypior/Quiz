package coderslab.quiz.service;

import coderslab.quiz.entities.Role;
import coderslab.quiz.interfaces.RoleService;
import coderslab.quiz.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void save(Role role) {
        this.roleRepository.save(role);
    }

    @Override
    public Role findOneByName(String roleName) {
        return this.roleRepository.findOneByName(roleName);
    }


}
