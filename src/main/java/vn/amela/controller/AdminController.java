package vn.amela.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import vn.amela.entity.User;
import vn.amela.response.ResponseObject;
import vn.amela.response.ResponseUtil;
import vn.amela.response.Status;
import vn.amela.response.UserResponseObject;
import vn.amela.service.AdminService;
import vn.amela.service.UserService;

@RestController
public class AdminController {

    private final AdminService adminService;
    private final UserService userService;

    public AdminController(AdminService adminService, UserService userService) {
        this.adminService = adminService;
        this.userService = userService;
    }

    @RequestMapping(value = "/create-user", method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@RequestBody User user) {
        ResponseObject responseObject = new ResponseObject();
        responseObject.setData(new UserResponseObject(user));

        if (!user.isValidUsername()) {
            responseObject.setStatus(new Status("001", "Username khong hop le"));
            return ResponseUtil.getResponseEntity(responseObject);
        }
        if (!user.isValidEmail()) {
            responseObject.setStatus(new Status("002", "Email khong hop le"));
            return ResponseUtil.getResponseEntity(responseObject);
        }
        if (!user.isValidPassword()) {
            responseObject.setStatus(new Status("003", "Password khong hop le"));
            return ResponseUtil.getResponseEntity(responseObject);
        }
        if (userService.isExist(user.getUsername())) {
            responseObject.setStatus(new Status("010", "Tai khoan da ton tai"));
            return ResponseUtil.getResponseEntity(responseObject);
        }

        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userService.createUser(user);
        responseObject.setStatus(new Status("000", "Truy van thanh cong"));
        return ResponseUtil.getResponseEntity(responseObject);
    }

    @RequestMapping(value = "/set-leader/{leader_id}", method = RequestMethod.POST)
    public ResponseEntity<?> setLeader(@PathVariable("leader_id") Long leaderId) {
        ResponseObject responseObject = new ResponseObject();
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
            .getAuthentication().getPrincipal();
        if (!userDetails.getAuthorities().contains("ROLE_ADMIN")) {
            responseObject.setStatus(new Status("010", "Khong co quyen chinh sua"));
            return ResponseUtil.getResponseEntity(responseObject);
        }
        if (!userService.isExist(userDetails.getUsername())) {
            responseObject.setStatus(new Status("011", "Tai khoan user khong ton tai"));
            return ResponseUtil.getResponseEntity(responseObject);
        }
        if (!userService.isExist(leaderId)) {
            responseObject.setStatus(new Status("012", "Tai khoan leader khong ton tai"));
            return ResponseUtil.getResponseEntity(responseObject);
        }
        User user = userService.getUserByUsername(userDetails.getUsername());
        Long userId = user.getId();
        userService.setLeader(userId, leaderId);
        responseObject.setStatus(new Status("000", "Truy van thanh cong"));
        return ResponseUtil.getResponseEntity(responseObject);
    }

    @RequestMapping(value = "/set-notified-user/{noti_user_id}", method = RequestMethod.POST)
    public ResponseEntity<?> setNotiUser(@PathVariable("noti_user_id") Long notiUserId) {
        ResponseObject responseObject = new ResponseObject();
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
            .getAuthentication().getPrincipal();
        if (!userDetails.getAuthorities().contains("ROLE_ADMIN")) {
            responseObject.setStatus(new Status("010", "Khong co quyen chinh sua"));
            return ResponseUtil.getResponseEntity(responseObject);
        }
        if (!userService.isExist(userDetails.getUsername())) {
            responseObject.setStatus(new Status("011", "Tai khoan user khong ton tai"));
            return ResponseUtil.getResponseEntity(responseObject);
        }
        if (!userService.isExist(notiUserId)) {
            responseObject.setStatus(new Status("012", "Tai khoan notified user khong ton tai"));
            return ResponseUtil.getResponseEntity(responseObject);
        }
        User user = userService.getUserByUsername(userDetails.getUsername());
        Long userId = user.getId();
        userService.setNotifyUser(userId, notiUserId);
        responseObject.setStatus(new Status("000", "Truy van thanh cong"));
        return ResponseUtil.getResponseEntity(responseObject);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
        Status status;
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
            .getAuthentication().getPrincipal();
        if (!userDetails.getAuthorities().contains("ROLE_ADMIN")) {
            status = new Status("010", "Khong co quyen xoa");
            return ResponseUtil.getResponseEntity(null, status);
        }
        if (!userService.isExist(id)) {
            status = new Status("010", "Tai khoan khong ton tai");
        } else {
            userService.deleteUser(id);
            status = new Status("000", "Truy van thanh cong");
        }
        return ResponseUtil.getResponseEntity(null, status);
    }

    @RequestMapping(value = "/delete/{username}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteUser(@PathVariable("username") String username) {
        Status status;
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
            .getAuthentication().getPrincipal();
        if (!userDetails.getAuthorities().contains("ROLE_ADMIN")) {
            status = new Status("010", "Khong co quyen xoa");
            return ResponseUtil.getResponseEntity(null, status);
        }
        if (!userService.isExist(username)) {
            status = new Status("010", "Tai khoan khong ton tai");
        } else {
            userService.deleteUser(username);
            status = new Status("000", "Truy van thanh cong");
        }
        return ResponseUtil.getResponseEntity(null, status);
    }

    @RequestMapping(value = "/update-user", method = RequestMethod.PUT)
    public ResponseEntity<?> updateUser(@RequestBody User newUser) {
        Status status = new Status("000", "Truy van thanh cong");
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
            .getAuthentication().getPrincipal();
        if (userDetails.getAuthorities().contains("ROLE_ADMIN")) {
            adminService.updateUser(newUser);
        } else {
            User requestUser = userService.getUserByUsername(userDetails.getUsername());
            if (requestUser.getId().equals(newUser.getId())) {
                userService.updateUser(newUser);
            } else {
                status = new Status("010", "Khong co quyen chinh sua");
            }
        }
        return ResponseUtil.getResponseEntity(null, status);
    }
}