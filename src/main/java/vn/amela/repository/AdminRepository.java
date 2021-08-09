package vn.amela.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.amela.entity.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

    Admin getByUsername(String username);

    boolean existsByUsername(String username);
}
