package example.drew.homework.persistence.dao;

import example.drew.homework.persistence.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {



}
