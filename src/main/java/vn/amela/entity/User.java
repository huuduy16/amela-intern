package vn.amela.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

@Entity(name = "user")
@DynamicUpdate
@Data
public class User extends Account {
    @Column(name = "email")
    private String email;

    @Column(name = "description")
    private String description;

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
