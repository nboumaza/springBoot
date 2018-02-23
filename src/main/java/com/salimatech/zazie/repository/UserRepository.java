package com.salimatech.zazie.repository;

import com.salimatech.zazie.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * User repository interface
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * find user by userId
     * @param userId search value
     * @return user if found
     * @throws com.salimatech.zazie.exceptions.EntityNotFoundException
     */
    Optional<User> findByUserId(String userId);


    /**
     * @param userId userId of the account to delete
     * @return the id of the deleted user account -
     */
    @Transactional
    long deleteByUserId(String userId);
}
