package com.salimatech.zazie.controller;


import com.salimatech.zazie.exceptions.UserNotFoundException;
import com.salimatech.zazie.model.User;
import com.salimatech.zazie.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;


    @GetMapping("/users")
    public List<User> findAll() {
        return userRepository.findAll();
    }

    /*
      User save(User user);
        List<User> getAllUsers();
        User deleteUser(User user);
        User findByUserId(String userId);
     */


    @GetMapping("/users/{userId}")
    public User findByUserId(@RequestParam(value="userId") String userId) {

       Optional<User> user = userRepository.findByUserId(userId);

        if (!user.isPresent())
            throw new UserNotFoundException(userId);
        return user.get();
    }


    @DeleteMapping("/users/{userId}")
    public void deleteUser(@PathVariable String userId) {
        userRepository.deleteByUserId(userId);
    }


    @PostMapping("/users")
    public ResponseEntity<Object> add(@RequestBody User user) {


        User savedUser = userRepository.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedUser.getId()).toUri();

        return ResponseEntity.created(location).build();

    }


    /**
     * validates that a user account to be created does not already exists
     * @param userId
     * @throws  UserNotFoundException
     */
    /*private void validateUser(String userId) {
        this.userRepository.findByUserId(userId).orElseThrow(
                () -> new UserNotFoundException(userId));
    }*/
}
