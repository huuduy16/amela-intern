package vn.amela.response;

import lombok.Data;
import vn.amela.entity.User;

@Data
public class UserResponseObject {

    private Long id;
    private String username;
    private String email;
    private String description;

    public UserResponseObject(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.description = user.getDescription();
    }
}
