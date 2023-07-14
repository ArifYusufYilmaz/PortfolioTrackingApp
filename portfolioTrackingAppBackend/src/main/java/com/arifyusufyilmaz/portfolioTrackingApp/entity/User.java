package com.arifyusufyilmaz.portfolioTrackingApp.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "users")
public class User
{
    @Id
    @GeneratedValue(generator = "User",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "User", sequenceName = "USER_ID_SEQ")
    private Long id;

    private String userFirstName;
    private String userLastName;
    private String userEmail;
    private String userPassword;

    @OneToMany(mappedBy = "user")
    private List<Portfolio> portfolios;

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

    public List<Portfolio> getPortfolios() {
        return portfolios;
    }

    public void setPortfolios(List<Portfolio> portfolios) {
        this.portfolios = portfolios;
    }
}
