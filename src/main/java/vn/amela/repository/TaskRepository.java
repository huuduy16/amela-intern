package vn.amela.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.amela.entity.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

}