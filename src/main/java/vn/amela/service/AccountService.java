package vn.amela.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import vn.amela.entity.Account;
import vn.amela.entity.Admin;
import vn.amela.entity.CustomUserDetails;
import vn.amela.entity.User;

@Service
public class AccountService implements UserDetailsService {

    private final AdminService adminService;
    private final UserService userService;

    public AccountService(AdminService adminService, UserService userService) {
        this.adminService = adminService;
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = getAccountByUsername(username);
        if (account != null) {
            return new CustomUserDetails(account);
        }
        throw new UsernameNotFoundException(username);
    }

    public UserDetails loadUserById(Long id) {
        Account account = getAccountById(id);
        if (account != null) {
            return new CustomUserDetails(account);
        }
        return null;
    }

    public Account getAccountByUsername(String username) {
        User user = userService.getUserByUsername(username);
        if (user != null) {
            return new Account(user);
        }
        Admin admin = adminService.getAdminByUsername(username);
        if (admin != null) {
            return new Account(admin);
        }
        return null;
    }

    public Account getAccountById(Long id) {
        User user = userService.getUserById(id);
        if (user != null) {
            return new Account(user);
        }
        Admin admin = adminService.getAdminById(id);
        if (admin != null) {
            return new Account(admin);
        }
        return null;
    }
}
