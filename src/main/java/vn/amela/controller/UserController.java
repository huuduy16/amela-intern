package vn.amela.controller;

import java.security.Principal;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import vn.amela.entity.User;
import vn.amela.response.ResponseObject;
import vn.amela.response.Status;
import vn.amela.service.UserService;

@RestController
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    public ResponseEntity<?> getUser(@PathVariable("username") String username) {
        ResponseObject responseObject = new ResponseObject();
        User user = userService.getUserByUsername(username);
        if (user == null) {
            responseObject.setStatus(new Status("010", "User khong ton tai"));
        } else {
            responseObject.setData(user);
            responseObject.setStatus(new Status("000", "Truy van thanh cong"));
        }
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getUser(@PathVariable("id") Long id) {
        ResponseObject responseObject = new ResponseObject();
        User user = userService.getUserById(id);
        if (user == null) {
            responseObject.setStatus(new Status("010", "User khong ton tai"));
        } else {
            responseObject.setData(user);
            responseObject.setStatus(new Status("000", "Truy van thanh cong"));
        }
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @RequestMapping(value = "/update/", method = RequestMethod.POST)
    public ResponseEntity<?> updateUser(Principal principal, @RequestBody User user) {
        
        return null;
    }
}
