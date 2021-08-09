package vn.amela.service;

import java.util.List;
import org.springframework.stereotype.Service;
import vn.amela.entity.LeaderAndUser;
import vn.amela.entity.NotifyUser;
import vn.amela.entity.User;
import vn.amela.repository.LeaderUserRepository;
import vn.amela.repository.NotifyUserRepository;
import vn.amela.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final LeaderUserRepository leaderUserRepository;
    private final NotifyUserRepository notifyUserRepository;

    public UserService(UserRepository userRepo,
        LeaderUserRepository leaderUserRepository,
        NotifyUserRepository notifyUserRepository) {
        this.userRepository = userRepo;
        this.leaderUserRepository = leaderUserRepository;
        this.notifyUserRepository = notifyUserRepository;
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

    public void deleteUser(String username) {
        userRepository.deleteByUsername(username);
    }

    public void updateUser(User user) {
    }

    public boolean isExist(Long id) {
        return userRepository.existsById(id);
    }

    public boolean isExist(String username) {
        return userRepository.existsByUsername(username);
    }

    //both existed
    public void setLeader(Long userId, Long leaderId) {
        LeaderAndUser leaderAndUser = new LeaderAndUser(leaderId, userId);
        leaderUserRepository.save(leaderAndUser);
    }

    //both exist
    public void setNotifyUser(Long userId, Long notiUserId) {
        NotifyUser notifyUser = new NotifyUser(notiUserId, userId);
        notifyUserRepository.save(notifyUser);
    }
}