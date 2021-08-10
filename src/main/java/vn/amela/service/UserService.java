package vn.amela.service;

import java.util.List;
import org.springframework.stereotype.Service;
import vn.amela.entity.Leader;
import vn.amela.entity.NotifiedUser;
import vn.amela.entity.User;
import vn.amela.repository.LeaderRepository;
import vn.amela.repository.NotifiedUserRepository;
import vn.amela.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final LeaderRepository leaderRepository;
    private final NotifiedUserRepository notifiedUserRepository;

    public UserService(UserRepository userRepo,
        LeaderRepository leaderRepository,
        NotifiedUserRepository notifiedUserRepository) {
        this.userRepository = userRepo;
        this.leaderRepository = leaderRepository;
        this.notifiedUserRepository = notifiedUserRepository;
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

    public void deleteLeaderOfUser(Long userId) {
        leaderRepository.deleteAllByLeaderId_UserId(userId);
    }

    //both existed
    public void setLeader(Long userId, Long leaderId) {
        deleteLeaderOfUser(userId);
        Leader leader = new Leader(leaderId, userId);
        leaderRepository.save(leader);
    }

    //both exist
    public void setNotifyUser(Long userId, Long notiUserId) {
        NotifiedUser notifiedUser = new NotifiedUser(notiUserId, userId);
        if (!notifiedUserRepository.existsByNotifyUserId(notifiedUser.getNotifiedUserId())) {
            notifiedUserRepository.save(notifiedUser);
        }
    }
}