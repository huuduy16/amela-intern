package vn.amela.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.amela.entity.TimeshTask;
import vn.amela.entity.TimeshTaskId;

@Repository
public interface TimeshTaskRepository extends JpaRepository<TimeshTask, TimeshTaskId> {

}
