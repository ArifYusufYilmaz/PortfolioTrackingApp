package com.arifyusufyilmaz.portfolioTrackingApp.dto;

import com.arifyusufyilmaz.portfolioTrackingApp.entity.Portfolio;
import jakarta.persistence.OneToMany;

import java.util.List;

public class UserResponseDto {
    private Long id;
    private String userFirstName;
    private String userLastName;
    private String userEmail;
    private String userPassword;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
