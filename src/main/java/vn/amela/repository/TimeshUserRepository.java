package vn.amela.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.amela.entity.TimeshUser;
import vn.amela.entity.TimeshUserId;

@Repository
public interface TimeshUserRepository extends JpaRepository<TimeshUser, TimeshUserId> {

}
