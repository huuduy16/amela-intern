package vn.amela.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.amela.entity.Timesheet;

@Repository
public interface TimesheetRepository extends JpaRepository<Timesheet, Long> {

}
