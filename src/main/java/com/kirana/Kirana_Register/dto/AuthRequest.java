package com.kirana.Kirana_Register.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) representing authentication request details.
 * Contains fields for username and password.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {

    /**
     * Username for authentication.
     */
    private String username ;
    /**
     * Password for authentication.
     */
    private String password;
}