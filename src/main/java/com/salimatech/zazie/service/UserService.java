package com.salimatech.zazie.service;

import com.salimatech.zazie.model.User;

import java.util.List;

public interface UserService {

        /**
         * register new user account
         * @param user
         * @return
         */
        User add(User user);


        /**
         *
         * @return list of all registered users
         */
        List<User> getAll();

        /**
         * delete user by userId
         * @param userId
         * @return
         */
        User deleteByUserId(String userId);

        /**
         * find user by userId
         * @param userId
         * @return user
         */
        User findByUserId(String userId);


}
