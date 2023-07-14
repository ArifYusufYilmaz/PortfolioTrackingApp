package com.arifyusufyilmaz.portfolioTrackingApp.repository;

import com.arifyusufyilmaz.portfolioTrackingApp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long> {
}
