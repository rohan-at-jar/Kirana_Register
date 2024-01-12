package com.kirana.Kirana_Register.dto;

import com.kirana.Kirana_Register.entities.UserInfo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Custom implementation of UserDetails for UserInfo entity.
 * Converts UserInfo into a UserDetails object with username, password, and authorities.
 */
public class UserInfoUserDetails implements UserDetails {
    /**
     * User's name (username).
     */
    private String name;

    /**
     * User's password.
     */
    private String password;
    /**
     * List of authorities (roles) granted to the user.
     */
    private List<GrantedAuthority> authorities;

    /**
     * Constructs a UserInfoUserDetails object based on the provided UserInfo entity.
     *
     * @param userInfo The UserInfo entity from which UserDetails information is extracted.
     */
    public UserInfoUserDetails(UserInfo userInfo) {
        name = userInfo.getName();
        password = userInfo.getPassword();
        authorities = Arrays.stream(userInfo.getRoles().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    /**
     * Returns the authorities granted to the user.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    /**
     * Returns the user's password.
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * Returns the username (name) of the user.
     */
    @Override
    public String getUsername() {
        return name;
    }

    /**
     * Indicates whether the user's account has not expired.
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Indicates whether the user is not locked.
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Indicates whether the user's credentials (password) are not expired.
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Indicates whether the user is enabled (active).
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}