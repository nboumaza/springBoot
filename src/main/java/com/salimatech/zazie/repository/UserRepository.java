package com.salimatech.zazie.repository;

import com.salimatech.zazie.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * find user by userId
     * @param userId
     * @return user if found
     */
    Optional<User> findByUserId(String userId);

    /**
     * delete user by userId
     * @param userId
     * @return
     */
    User deleteByUserId(String userId);


}
