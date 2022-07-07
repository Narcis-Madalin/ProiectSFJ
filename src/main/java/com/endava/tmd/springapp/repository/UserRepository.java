package com.endava.tmd.springapp.repository;

import com.endava.tmd.springapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    //User getUserByNameOrEmail(Optional<String> username, Optional<String> email);
}
