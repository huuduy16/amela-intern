package vn.amela.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.amela.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User getByUsername(String username);

    void deleteByUsername(String username);

    boolean existsByUsername(String username);
}
