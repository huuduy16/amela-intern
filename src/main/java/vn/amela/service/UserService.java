package vn.amela.service;

import java.util.List;
import org.springframework.stereotype.Service;
import vn.amela.entity.Leader;
import vn.amela.entity.NotifiedUser;
import vn.amela.entity.Timesh;
import vn.amela.entity.TimeshUser;
import vn.amela.entity.User;
import vn.amela.repository.LeaderRepository;
import vn.amela.repository.NotifiedUserRepository;
import vn.amela.repository.TimeshUserRepository;
import vn.amela.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final LeaderRepository leaderRepository;
    private final NotifiedUserRepository notifiedUserRepository;
    private final TimeshService timeshService;
    private final TimeshUserRepository timeshUserRepository;

    public UserService(UserRepository userRepo,
        LeaderRepository leaderRepository,
        NotifiedUserRepository notifiedUserRepository, TimeshService timeshService,
        TimeshUserRepository timeshUserRepository) {
        this.userRepository = userRepo;
        this.leaderRepository = leaderRepository;
        this.notifiedUserRepository = notifiedUserRepository;
        this.timeshService = timeshService;
        this.timeshUserRepository = timeshUserRepository;
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

    public void updateUser(User newUser) {
        User currentUser = userRepository.getById(newUser.getId());
        currentUser.setPassword(newUser.getPassword());
        currentUser.setDescription(newUser.getDescription());
        userRepository.save(currentUser);
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

    public void setLeader(Long userId, Long leaderId) { //both existed
        deleteLeaderOfUser(userId);
        Leader leader = new Leader(leaderId, userId);
        leaderRepository.save(leader);
    }

    public void setNotifyUser(Long userId, Long notiUserId) { //both exist
        NotifiedUser notifiedUser = new NotifiedUser(notiUserId, userId);
        if (!notifiedUserRepository.existsByNotifyUserId(notifiedUser.getNotifiedUserId())) {
            notifiedUserRepository.save(notifiedUser);
        }
    }

    public Timesh createTimesheet(Long userId, Timesh timesh) {
        timesh = timeshService.createTimesheet(timesh);
        TimeshUser timeshUser = new TimeshUser(userId, timesh.getId());
        timeshUserRepository.save(timeshUser);
        return timesh;
    }
}