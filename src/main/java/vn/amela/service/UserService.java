package vn.amela.service;

import java.util.List;
import java.util.Optional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import vn.amela.entity.CustomUserDetails;
import vn.amela.entity.User;
import vn.amela.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

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

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new CustomUserDetails(user);
    }

    public UserDetails loadUserById(String id) {
        Optional<User> object = userRepository.findById(Long.valueOf(id));
        if (object.isEmpty()) {
            return null;
        }
        return new CustomUserDetails(object.get());
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

    public void updateUser(Long id){
        userRepository.getById(id);
    }
}