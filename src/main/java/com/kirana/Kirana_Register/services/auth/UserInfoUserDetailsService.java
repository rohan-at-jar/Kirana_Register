package com.kirana.Kirana_Register.services.auth;


import com.kirana.Kirana_Register.entities.UserInfo;
import com.kirana.Kirana_Register.repository.UserInfoRepository;
import com.kirana.Kirana_Register.dto.UserInfoUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Service class that implements Spring Security's UserDetailsService interface.
 * It loads user-specific data based on the provided username.
 */
@Component
public class UserInfoUserDetailsService implements UserDetailsService {

    /**
     * Repository for interacting with user information stored in the database.
     */
    @Autowired
    private UserInfoRepository repository;

    /**
     * Loads user-specific data based on the provided username.
     *
     * @param username The username for which user details should be loaded.
     * @return UserDetails object containing user-specific data.
     * @throws UsernameNotFoundException If the user is not found.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserInfo> userInfo = repository.findByName(username);
        return userInfo.map(UserInfoUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("user not found " + username));
    }
}