package com.salimatech.zazie.repository;

import com.salimatech.zazie.model.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

    private User someUser;


    @Autowired
    private UserRepository userRepository;


    @Before
    public void setUp() {
        someUser = null;
    }

    @Test
    public void findAllUsers() {


        // when
        List<User> users = userRepository.findAll();

        // then
        assertFalse(users.isEmpty());
        assertTrue(users.size() == 4);

    }


    @Test
    public void findByUserId() {
        someUser = new User("user1", "email1@acme.com");
        userRepository.save(someUser);

        // when
        Optional<User> found = userRepository.findByUserId(someUser.getUserId());

        // then
        assertTrue(found.isPresent());

        User retrieved = found.get();
        assertEquals(retrieved.getUserId(), someUser.getUserId());
        assertEquals(retrieved.getEmail(), someUser.getEmail());

    }


    @Test
    public void deleteByUserId() {

        // given
        User someUser = new User("user2", "email2@acme.com");
        userRepository.save(someUser);

        // when
        long deletedId = userRepository.deleteByUserId(someUser.getUserId());

        // then
        Optional<User> found = userRepository.findByUserId(someUser.getUserId());
        assertTrue(!found.isPresent());

    }


}