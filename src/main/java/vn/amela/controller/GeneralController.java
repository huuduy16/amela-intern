package vn.amela.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import vn.amela.request.LoginRequestObject;
import vn.amela.response.LoginResponseObject;
import vn.amela.response.ResponseObject;
import vn.amela.response.Status;
import vn.amela.service.AccountService;

@RestController
public class GeneralController {

    private final AccountService accountService;

    public GeneralController(AccountService accountService) {
        this.accountService = accountService;
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody LoginRequestObject object) {
        ResponseObject responseObject = new ResponseObject();
        LoginResponseObject loginResponseObject = new LoginResponseObject();
        loginResponseObject.setUsername(object.getUsername());

        UserDetails account = accountService.loadUserByUsername(object.getUsername());
        if (account != null) {
            if (new BCryptPasswordEncoder().matches(object.getPassword(), account.getPassword())) {
                //create and return token
                responseObject.setStatus(new Status("000", "Dang nhap thanh cong"));
            } else {
                responseObject.setStatus(new Status("010", "Sai mat khau"));
            }
        } else {
            responseObject.setStatus(new Status("012", "Tai khoan khong ton tai"));
        }
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }
}
