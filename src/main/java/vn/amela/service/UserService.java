package vn.amela.service;

import java.util.List;
import org.springframework.stereotype.Service;
import vn.amela.entity.User;
import vn.amela.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepo) {
        this.userRepository = userRepo;
    }

    public void createUser(User user) {
        userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserByUsername(String username) {
        return userRepository.getByUsername(username);
    }

    public User getUserById(Long id) {
        return userRepository.getById(id);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public void updateUser(Long id) {
        userRepository.getById(id);
    }
}