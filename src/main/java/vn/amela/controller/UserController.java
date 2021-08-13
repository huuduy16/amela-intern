package vn.amela.controller;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import vn.amela.entity.Timesh;
import vn.amela.entity.User;
import vn.amela.response.ResponseObject;
import vn.amela.response.ResponseUtil;
import vn.amela.response.Status;
import vn.amela.response.UserResponseObject;
import vn.amela.service.UserService;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResponseEntity<?> getUsers() {
        List<UserResponseObject> users = userService.getAllUsers().stream()
            .map(UserResponseObject::new).collect(Collectors.toList());
        ResponseObject responseObject = new ResponseObject();
        responseObject.setData(users);
        return ResponseUtil.getResponseEntity(users, new Status("000", "Truy van thanh cong"));
    }

    @RequestMapping(value = "user/{username}", method = RequestMethod.GET)
    public ResponseEntity<?> getUser(@PathVariable("username") String username) {
        ResponseObject responseObject = new ResponseObject();
        User user = userService.getUserByUsername(username);
        if (user == null) {
            responseObject.setStatus(new Status("010", "User khong ton tai"));
        } else {
            responseObject.setData(new UserResponseObject(user));
            responseObject.setStatus(new Status("000", "Truy van thanh cong"));
        }
        return ResponseUtil.getResponseEntity(responseObject);
    }

    @RequestMapping(value = "user/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getUser(@PathVariable("id") Long id) {
        ResponseObject responseObject = new ResponseObject();
        User user = userService.getUserById(id);
        if (user == null) {
            responseObject.setStatus(new Status("010", "User khong ton tai"));
        } else {
            responseObject.setData(new UserResponseObject(user));
            responseObject.setStatus(new Status("000", "Truy van thanh cong"));
        }
        return ResponseUtil.getResponseEntity(responseObject);
    }

    @RequestMapping(value = "create-timesheet", method = RequestMethod.POST)
    public ResponseEntity<?> createTimesheet(@RequestBody Timesh timesh) {

        return null;
    }
}
