package vn.amela.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.amela.entity.LeaderAndUser;
import vn.amela.entity.LeaderAndUserId;

@Repository
public interface LeaderUserRepository extends JpaRepository<LeaderAndUser, LeaderAndUserId> {
}
