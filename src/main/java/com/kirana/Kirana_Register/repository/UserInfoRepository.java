package com.kirana.Kirana_Register.repository;

import com.kirana.Kirana_Register.entities.UserInfo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;


/**
 * Repository interface for managing user information in MongoDB.
 */
public interface UserInfoRepository extends MongoRepository<UserInfo, String> {
    /**
     * Find a user by their username.
     *
     * @param username The username to search for.
     * @return An optional containing the user information if found.
     */
    Optional<UserInfo> findByName(String username);
}