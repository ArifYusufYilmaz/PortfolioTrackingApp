package com.arifyusufyilmaz.portfolioTrackingApp.service.abstracts;

import com.arifyusufyilmaz.portfolioTrackingApp.dto.UserResponseDto;
import com.arifyusufyilmaz.portfolioTrackingApp.dto.UserSaveDto;

import java.util.List;

public interface UserService {
    UserResponseDto createUser(UserSaveDto userSaveDto);
    List<UserResponseDto> getAllUsers();
}
