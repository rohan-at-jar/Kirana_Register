package com.kirana.Kirana_Register.entities;


//import org.springframework.data.annotation.Id;
//import org.springframework.data.mongodb.core.mapping.Document;
//
//@Document(collection = "user")
//public class UserInfo {
//
//    @Id
//    private String _id;
//
//    private String username;
//    private String password;
//    private String role;
//
//    public UserInfo() {
//    }
//
//    @Override
//    public String toString() {
//        return "User{" +
//                "id='" + _id + '\'' +
//                ", username='" + username + '\'' +
//                ", password='" + password + '\'' +
//                ", role='" + role + '\'' +
//                '}';
//    }
//
//    public String getId() {
//        return _id;
//    }
//
//    public void setId(String _id) {
//        this._id = _id;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public String getRole() {
//        return role;
//    }
//
//    public void setRole(String role) {
//        this.role = role;
//    }
//
//// Constructors, getters, and setters
//
//    // Omitted for brevity
//}
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Document(collection= "user")
@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
    @Id
    private String id;
    private String name;
    private String email;
    private String password;
    private String roles;
}
