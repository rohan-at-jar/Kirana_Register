package com.kirana.Kirana_Register.services.auth;

import com.kirana.Kirana_Register.entities.UserInfo;
import com.kirana.Kirana_Register.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class SignUp {

    @Autowired
    private UserInfoRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public SignUp(UserInfoRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public String addUser(UserInfo userInfo) {
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        repository.save(userInfo);
        return "user added to system ";
    }
}
