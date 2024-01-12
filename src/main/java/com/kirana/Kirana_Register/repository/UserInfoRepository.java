package com.kirana.Kirana_Register.repository;

import com.kirana.Kirana_Register.entities.UserInfo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;


public interface UserInfoRepository extends MongoRepository<UserInfo, String> {
    Optional<UserInfo> findByName(String username);
}