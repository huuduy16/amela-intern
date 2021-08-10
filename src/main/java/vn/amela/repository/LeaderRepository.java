package vn.amela.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.amela.entity.Leader;
import vn.amela.entity.LeaderId;

@Repository
public interface LeaderRepository extends JpaRepository<Leader, LeaderId> {

    void deleteAllByLeaderId_UserId(Long userId);
}
