package com.arifyusufyilmaz.portfolioTrackingApp.controller;

import com.arifyusufyilmaz.portfolioTrackingApp.dto.UserResponseDto;
import com.arifyusufyilmaz.portfolioTrackingApp.dto.UserSaveDto;
import com.arifyusufyilmaz.portfolioTrackingApp.entity.User;
import com.arifyusufyilmaz.portfolioTrackingApp.repository.UserDao;
import com.arifyusufyilmaz.portfolioTrackingApp.service.abstracts.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;
    private final UserDao userDao;

    public UserController(UserService userService, UserDao userDao) {
        this.userService = userService;
        this.userDao = userDao;
    }

    /*public UserController(UserService userService) {
        this.userService = userService;
    }*/
    @PostMapping("/user/new")
    public UserResponseDto createUser(@RequestBody UserSaveDto userSaveDto){
        return userService.createUser(userSaveDto);
    }
    @GetMapping("/all")
    public List<UserResponseDto> getAllUsers(){
        return userService.getAllUsers();
    }
    // TODO delete, update
}
