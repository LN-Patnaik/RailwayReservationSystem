package com.UserComponent.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class User {

    @Id
    private String userId;
    private String username;
    private String password;
    private List<Role> roles;
    private String email;
    private String phoneNumber;

    public User(){}

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}






//@Document(collection = "users")
//public class User {
//
//    @Id
//    private String id;
//    @Indexed(unique = true, direction = IndexDirection.DESCENDING, dropDups = true)
//    private String email;
//    private String password;
//    private String fullname;
//    private boolean enabled;
//    @DBRef
//    private Set<Role> roles= new HashSet<>();
//
//
//    //Getter and Setters
//    public String getId() {
//        return id;
//    }
//    public void setId(String id) {
//        this.id = id;
//    }
//    public String getEmail() {
//        return email;
//    }
//    public void setEmail(String email) {
//        this.email = email;
//    }
//    public String getPassword() {
//        return password;
//    }
//    public void setPassword(String password) {
//        this.password = password;
//    }
//    public String getFullname() {
//        return fullname;
//    }
//    public void setFullname(String fullname) {
//        this.fullname = fullname;
//    }
//    public boolean isEnabled() {
//        return enabled;
//    }
//    public void setEnabled(boolean enabled) {
//        this.enabled = enabled;
//    }
//    public Set<Role> getRoles() {
//        return roles;
//    }
//    public void setRoles(Set<Role> roles) {
//        this.roles = roles;
//    }
//
//    //Default Constructed
//    public User() {
//		super();
//	}
//
//	@Override
//	public String toString() {
//		return "User [id=" + id + ", email=" + email + ", password=" + password + ", fullname=" + fullname
//				+ ", enabled=" + enabled + ", roles=" + roles + "]";
//	}
//}





