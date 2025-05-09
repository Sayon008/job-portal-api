package org.vibe.jobportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vibe.jobportal.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    //    Find User By Name
    Optional<User> findByEmail(String email);

    User save(User user);
}

