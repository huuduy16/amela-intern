package vn.amela.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import vn.amela.entity.Account;
import vn.amela.request.LoginRequestObject;
import vn.amela.response.LoginResponseObject;
import vn.amela.response.ResponseUtil;
import vn.amela.response.Status;
import vn.amela.security.JwtUtil;
import vn.amela.service.AccountService;

@RestController
public class GeneralController {

    private final AccountService accountService;
    private final JwtUtil jwtUtil;

    public GeneralController(AccountService accountService, JwtUtil jwtUtil) {
        this.accountService = accountService;
        this.jwtUtil = jwtUtil;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody LoginRequestObject object) {
        Status status;
        LoginResponseObject loginResponseObject = new LoginResponseObject();
        loginResponseObject.setUsername(object.getUsername());

        Account account = accountService.getAccountByUsername(object.getUsername());
        if (account != null) { //account exist
            if (new BCryptPasswordEncoder().matches(object.getPassword(),
                account.getPassword())) { //password correct
                //create and return token
                loginResponseObject.setToken(jwtUtil.generateToken(account));
                status = new Status("000", "Dang nhap thanh cong");
            } else { //password incorrect
                status = new Status("010", "Sai mat khau");
            }
        } else { //account not exist
            status = new Status("012", "Tai khoan khong ton tai");
        }
        return ResponseUtil.getResponseEntity(loginResponseObject, status);
    }
}
