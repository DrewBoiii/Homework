package example.drew.homework.persistence.dao;

import example.drew.homework.persistence.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {



}
