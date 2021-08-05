package vn.amela.entity;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails implements UserDetails {

    private final Admin admin;
    private final User user;
    private Collection roles;

    public CustomUserDetails(User user) {
        this.user = user;
        this.roles.addAll(user.getRoles());
        admin = null;
    }

    public CustomUserDetails(Admin admin) {
        this.admin = admin;
        this.roles.addAll(admin.getRoles());
        user = null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public String getPassword() {
        return user.getPassword();
    }

    public String getUsername() {
        return user.getUsername();
    }

    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return true;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isEnabled() {
        return true;
    }
}
