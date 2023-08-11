package com.codestates.server.user.mapper;

import com.codestates.server.user.dto.UserLoginDto;
import com.codestates.server.user.dto.UserPatchDto;
import com.codestates.server.user.dto.UserPostDto;
import com.codestates.server.user.dto.UserResponseDto;
import com.codestates.server.user.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")

public interface UserMapper {

    User userPostDtoToUser(UserPostDto userPostDto);
    User userPatchDtoToUser(UserPatchDto userPatchDto);
    UserLoginDto userToUserLoginDto(User user);
    UserResponseDto userToUserResponseDto(User user);
    List<UserResponseDto> userToUserResponseListDto(List<User> users);  // ✅ 추후 변경 예정
}
