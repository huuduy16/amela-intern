package vn.amela.service;

import java.util.List;
import org.springframework.stereotype.Service;
import vn.amela.entity.Admin;
import vn.amela.entity.User;
import vn.amela.repository.AdminRepository;
import vn.amela.repository.UserRepository;

@Service
public class AdminService {

    private final AdminRepository adminRepository;
    private final UserRepository userRepository;

    public AdminService(AdminRepository adminRepository,
        UserRepository userRepository) {
        this.adminRepository = adminRepository;
        this.userRepository = userRepository;
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

    public void updateUser(User newUser) {
        User currentUser = userRepository.getById(newUser.getId());
        currentUser.setEmail(newUser.getEmail());
        currentUser.setUsername(newUser.getUsername());
        currentUser.setPassword(newUser.getPassword());
        userRepository.save(currentUser);
    }
}
