package com.kirana.Kirana_Register.controller.AuthController;

import com.kirana.Kirana_Register.dto.AuthRequest;
import com.kirana.Kirana_Register.entities.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;
import com.kirana.Kirana_Register.services.auth.SignUp;
import com.kirana.Kirana_Register.services.auth.JwtService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@RestController
public class AuthenticationController {

//    @Autowired
    private final UserInfo userInfo;

//    @Autowired
    private final SignUp signup;

    private final JwtService jwtService;

//    @Autowired
    private final AuthenticationManager authenticationManager;

    public AuthenticationController(UserInfo userInfo, SignUp signup, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userInfo = userInfo;
        this.signup = signup;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }


    @PostMapping("/sign-up")
    public String addNewUser(@RequestBody UserInfo userInfo){
        return signup.addUser(userInfo);
    }

    @PostMapping("/login")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getUsername());
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }
}}
