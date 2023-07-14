package com.arifyusufyilmaz.portfolioTrackingApp.service.concretes;

import com.arifyusufyilmaz.portfolioTrackingApp.converter.UserMapper;
import com.arifyusufyilmaz.portfolioTrackingApp.dto.UserResponseDto;
import com.arifyusufyilmaz.portfolioTrackingApp.dto.UserSaveDto;
import com.arifyusufyilmaz.portfolioTrackingApp.entity.User;
import com.arifyusufyilmaz.portfolioTrackingApp.repository.UserDao;
import com.arifyusufyilmaz.portfolioTrackingApp.service.abstracts.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserResponseDto createUser(UserSaveDto userSaveDto) {
        if (userSaveDto == null) {
            //TODO validations
        }
        User user = UserMapper.INSTANCE.mapUserSaveDtoToUser(userSaveDto);
        return UserMapper.INSTANCE.mapUserToUserResponseDto(userDao.save(user));
    }

    @Override
    public List<UserResponseDto> getAllUsers() {

        return UserMapper.INSTANCE.mapUserListToUserResponseDtoList(userDao.findAll());

    }
}
