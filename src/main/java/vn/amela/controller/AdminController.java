package vn.amela.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResponseEntity<?> getUsers() {
        List<User> users = userService.getAllUsers();
        ResponseObject responseObject = new ResponseObject();
        responseObject.setData(users);
        responseObject.setStatus(new Status("000", "Truy van thanh cong"));
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @RequestMapping(value = "/create-user", method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@RequestBody User user) {
        ResponseObject responseObject = new ResponseObject();
        responseObject.setData(user);

        if (!user.isValidUsername()) {
            responseObject.setStatus(new Status("001", "Username khong hop le"));
            return new ResponseEntity<>(responseObject, HttpStatus.OK);
        }
        if (!user.isValidEmail()) {
            responseObject.setStatus(new Status("002", "Email khong hop le"));
            return new ResponseEntity<>(responseObject, HttpStatus.OK);
        }
        if (!user.isValidPassword()) {
            responseObject.setStatus(new Status("003", "Password khong hop le"));
            return new ResponseEntity<>(responseObject, HttpStatus.OK);
        }
        if (userService.getUserByUsername(user.getUsername()) != null) {
            responseObject.setStatus(new Status("010", "Tai khoan da ton tai"));
            return new ResponseEntity<>(responseObject, HttpStatus.OK);
        }

        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));

        userService.createUser(user);
        responseObject.setStatus(new Status("000", "Truy van thanh cong"));
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteUserById(@PathVariable("id") Long id) {
        User user = userService.getUserById(id);
        ResponseObject responseObject = new ResponseObject();
        if (user == null) {
            responseObject.setStatus(new Status("010", "Tai khoan khong ton tai"));
        } else {
            userService.deleteUser(id);
            responseObject.setStatus(new Status("000", "Truy van thanh cong"));
        }
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }
}