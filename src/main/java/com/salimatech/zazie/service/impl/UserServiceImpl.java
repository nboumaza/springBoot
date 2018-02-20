package com.salimatech.zazie.service.impl;

import com.salimatech.zazie.model.User;
import com.salimatech.zazie.repository.UserRepository;
import com.salimatech.zazie.service.UserService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{


    private final UserRepository userRepository;

    /*@Autowired
    EntityManager entityManager;*/

    //TODO wire in Validation bean

    @Inject
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User add(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User deleteByUserId(String userId) {
        return null;
    }


    @Override
    public User findByUserId(String userId) {
        return null;
    }
}