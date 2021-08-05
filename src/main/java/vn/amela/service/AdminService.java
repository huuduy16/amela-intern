package vn.amela.service;

import java.util.List;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import vn.amela.entity.Admin;
import vn.amela.entity.CustomUserDetails;
import vn.amela.repository.AdminRepository;

@Service
public class AdminService {

    private final AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public void createAdmin(Admin admin) {
        adminRepository.save(admin);
    }

    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    public Admin getAdminByUsername(String username) {
        return adminRepository.getByUsername(username);
    }

    public Admin getAdminById(Long id) {
        return adminRepository.getById(id);
    }
}
