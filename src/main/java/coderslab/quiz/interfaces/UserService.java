package coderslab.quiz.interfaces;


import coderslab.quiz.entities.User;

public interface UserService {
    User findByUserName(String name);
    void saveUser(User user);
}
