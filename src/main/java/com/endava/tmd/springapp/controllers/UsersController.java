package com.endava.tmd.springapp.controllers;

import com.endava.tmd.springapp.entity.User;
import com.endava.tmd.springapp.repository.UserRepository;
import com.endava.tmd.springapp.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("users")
public class UsersController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public List<User> getAll(){
        return userService.getAll();
    }

    @RequestMapping(params = "user_id", method = RequestMethod.GET)
    public Object getUser(@RequestParam("user_id") Long id){
        if(userService.getById(id) == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else {
            return userService.getById(id);
        }
    }

    @PostMapping
    public User addUser(@RequestBody final User user){
        return userService.addUser(user);
    }

    @RequestMapping(value = "user_id", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    }

    @RequestMapping(value = "user_id", method = RequestMethod.PUT)
    public User updateUser(@PathVariable Long id, @RequestBody User newUser){
        return userService.updateUser(id, newUser);
    }

//    @RequestMapping(value = "/NameOrEmail", method = RequestMethod.GET)
//    public User getUserByNameOrEmail(@RequestParam ("username") Optional<String> username, @RequestParam ("email") Optional<String> email){
//        return userService.getUserByNameOrEmail(username, email);
//    }

//    @Autowired
//    private UserRepository userRepository;
//
//    @GetMapping
//    public List<User> usersList(){
//        return userRepository.findAll();
//    }
//
//    @GetMapping
//    @RequestMapping("{id}")
//    public User getUser(@PathVariable Long id){
//        return userRepository.findById(id).get();
//    }
//
//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public User create(@RequestBody final User user){
//        return userRepository.saveAndFlush(user);
//    }


}
