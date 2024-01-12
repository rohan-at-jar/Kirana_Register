package com.kirana.Kirana_Register.services.auth;

import com.kirana.Kirana_Register.entities.UserInfo;
import com.kirana.Kirana_Register.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Service class responsible for user registration.
 */
@Component
public class SignUp {

    /**
     * Repository for interacting with user information stored in the database.
     */
    @Autowired
    private UserInfoRepository repository;

    /**
     * Password encoder for securely encoding user passwords.
     */
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Constructor for initializing the SignUp service with dependencies.
     *
     * @param repository      Repository for interacting with user information stored in the database.
     * @param passwordEncoder Password encoder for securely encoding user passwords.
     */
    public SignUp(UserInfoRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Adds a new user to the system after encoding the password.
     *
     * @param userInfo User information to be added.
     * @return A message indicating the success of the user addition process.
     */
    public String addUser(UserInfo userInfo) {
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        repository.save(userInfo);
        return "user added to system ";
    }
}
