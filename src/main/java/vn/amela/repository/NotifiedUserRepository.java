package vn.amela.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.amela.entity.NotifiedUser;
import vn.amela.entity.NotifiedUserId;

@Repository
public interface NotifiedUserRepository extends JpaRepository<NotifiedUser, NotifiedUserId> {

    boolean existsByNotifyUserId(NotifiedUserId notifiedUserId);
}
