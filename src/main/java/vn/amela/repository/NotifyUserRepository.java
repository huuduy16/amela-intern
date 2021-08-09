package vn.amela.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.amela.entity.NotifyUser;
import vn.amela.entity.NotifyUserId;

@Repository
public interface NotifyUserRepository extends JpaRepository<NotifyUser, NotifyUserId> {

}
