package com.salimatech.zazie.controller;


import com.salimatech.zazie.exceptions.DataIntegrityException;
import com.salimatech.zazie.exceptions.EntityNotFoundException;
import com.salimatech.zazie.model.User;
import com.salimatech.zazie.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 *   User Account REST controller
 *   Please refer to the following swagger api for more detail on the available
 *   operations and corresponding response status
 *   https://app.swaggerhub.com/apis/nboumaza/springboot/1.0.0#/
 */
@RestController
public class UserController {

    private static Logger logger = LogManager.getLogger(UserController.class);

    @Autowired
    private UserRepository userRepository;


    /**
     * @return list of all user accounts
     */
    @GetMapping("/users")
    public List<User> findAll() {
        return userRepository.findAll();
    }


    /**
     * @param userId userId of the account to search for
     * @return user account if such accounts exists
     * @throws EntityNotFoundException if no such account exists
     */
    @GetMapping("/users/{userId}")
    public User findByUserId(@PathVariable("userId") String userId)  throws EntityNotFoundException {

       Optional<User> user = userRepository.findByUserId(userId);

        if (!user.isPresent()) {
            String message = "could not find a user account with userId = "+userId;
            logger.debug(message);
            throw new EntityNotFoundException(message);
        }
        return user.get();
    }


    @DeleteMapping("/users/{userId}")
    public ResponseEntity<Void> deleteByUserId(@PathVariable("userId") String userId) {

        userRepository.deleteByUserId(userId);

        //audit and authorization @Advice could be used here
        logger.debug("deleted user with userId ="+userId);

        return ResponseEntity.noContent().build();

    }

    @PostMapping("/users")
    public User save(@RequestBody User user) {

        User savedUser;

        try {
            savedUser = userRepository.save(user);
        }
        catch (DataIntegrityViolationException e){

            logger.error(e.getStackTrace());
            throw new DataIntegrityException(e.getMessage());
        }

        return savedUser;

    }

}
