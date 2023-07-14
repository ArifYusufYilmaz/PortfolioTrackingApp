package com.arifyusufyilmaz.portfolioTrackingApp.converter;

import com.arifyusufyilmaz.portfolioTrackingApp.dto.UserResponseDto;
import com.arifyusufyilmaz.portfolioTrackingApp.dto.UserSaveDto;
import com.arifyusufyilmaz.portfolioTrackingApp.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User mapUserSaveDtoToUser(UserSaveDto userSaveDto);
    UserResponseDto mapUserToUserResponseDto(User user);

    List<UserResponseDto> mapUserListToUserResponseDtoList(List<User> users);
}
