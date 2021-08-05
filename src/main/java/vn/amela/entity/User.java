package vn.amela.entity;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

@Entity(name = "user")
@DynamicUpdate
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password_hash")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "description")
    private String description;

    @Transient
    private Set<String> roles;

    public boolean isValidEmail() {
        return true;
    }

    public boolean isValidPassword() {
        return true;
    }

    public boolean isValidUsername() {
        return true;
    }
}
