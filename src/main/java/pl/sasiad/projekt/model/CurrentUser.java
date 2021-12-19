package pl.sasiad.projekt.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class CurrentUser extends User {

    private final pl.sasiad.projekt.entities.User user;

    public CurrentUser(String username, String password,
                       Collection<? extends GrantedAuthority> authorities,
                       pl.sasiad.projekt.entities.User user) {
        super(username, password, authorities);
        this.user = user;
    }

    public pl.sasiad.projekt.entities.User getUser() {
        return user;
    }
}
