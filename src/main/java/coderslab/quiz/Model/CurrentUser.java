package coderslab.quiz.Model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class CurrentUser extends User {
    private final coderslab.quiz.entities.User user;

    public CurrentUser(
            String username,
            String password,
            Collection<? extends GrantedAuthority> authorities,
            coderslab.quiz.entities.User user
    ) {
        super(username, password, authorities);
        this.user = user;
    }

    public coderslab.quiz.entities.User getUser() {
        return user;
    }
}