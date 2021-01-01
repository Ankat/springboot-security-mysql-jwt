package com.ankat.repositories;

import com.ankat.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository(value = "userRepository")
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsrUsername(String usrUsername);
}
