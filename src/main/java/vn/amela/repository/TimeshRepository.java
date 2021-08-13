package vn.amela.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.amela.entity.Timesh;

@Repository
public interface TimeshRepository extends JpaRepository<Timesh, Long> {

}
