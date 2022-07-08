package com.endava.tmd.springapp.repository;

import com.endava.tmd.springapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

//    @Query("select u from User u where u.username = :username or u.email = :email")
//    User findUserByUsernameOrEmail(String username, String email);
}
