package com.kirana.Kirana_Register.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Entity class representing user information.
 * This class is annotated with @Document to specify its storage in MongoDB.
 * It is also annotated with @Component to indicate that it is a Spring bean.
 */
@Document(collection= "user")
@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
    /**
     * Unique identifier for the user.
     */
    @Id
    private String id;
    /**
     * User's name.
     */
    private String name;
    /**
     * User's email.
     */
    private String email;
    /**
     * User's password.
     */
    private String password;
    /**
     * User's roles, separated by commas.
     */
    private String roles;
}
