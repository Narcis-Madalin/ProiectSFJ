package com.endava.tmd.springapp.service;

import com.endava.tmd.springapp.entity.User;
import com.endava.tmd.springapp.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public User getById(Long id){
        return userRepository.findById(id).get();
    }

    public User addUser(User user){
        return userRepository.saveAndFlush(user);
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

    public User updateUser(Long id, User newUser){
        User currentUser = userRepository.findById(id).get();
        BeanUtils.copyProperties(newUser, currentUser, "user_id");
        return userRepository.saveAndFlush(currentUser);
    }

//    public User findUserByUsernameOrEmail(String username, String email){
//        return userRepository.findUserByUsernameOrEmail(username, email);
//    }


}
