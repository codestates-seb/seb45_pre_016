package com.codestates.server.user.controller;

import com.codestates.server.user.dto.UserPatchDto;
import com.codestates.server.user.dto.UserPostDto;
import com.codestates.server.user.dto.UserResponseDto;
import com.codestates.server.user.entity.User;
import com.codestates.server.user.mapper.UserMapper;
import com.codestates.server.user.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;


@RestController
@RequestMapping("/users")
@Validated
@AllArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    private final UserMapper mapper;


    // 회원 가입 api
    @PostMapping("/signup")
    public ResponseEntity postUser(@Valid
                                   @RequestBody UserPostDto userPostDto) {

        User user = mapper.userPostDtoToUser(userPostDto);
        User createdUser = userService.createUser(user);

        return new ResponseEntity(createdUser, HttpStatus.CREATED);
    }


    // 사용자 정보 조회 (개인 정보 조회)
    @GetMapping("/mypage/{user-id}")
    public ResponseEntity getUser(@PathVariable("user-id") @Positive long userId) {

        User user = userService.getUser(userId);
        UserResponseDto response = mapper.userToUserResponseDto(user);

        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getUsers() {

        // ⏹️ Pagination 적용 전
        List<User> users = userService.getUsers();
        List<UserResponseDto> response = mapper.userToUserResponseListDto(users);

        return new  ResponseEntity(response, HttpStatus.OK);
    }



    // 사용자 정보 수정
    @PatchMapping("/mypage/edit/{user-id}")
    public ResponseEntity patchUser(@PathVariable("user-id") @Positive long userId,
                                    @Valid @RequestBody UserPatchDto userPatchDto) {

        userPatchDto.setUserId(userId);

        User user = userService.updateUser(mapper.userPatchDtoToUser(userPatchDto));
        UserResponseDto response = mapper.userToUserResponseDto(user);

        return new ResponseEntity(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{user-id}")
    public ResponseEntity deleteUser(@PathVariable("user-id") @Positive long userId) {

        userService.deleteUser(userId);

        return new ResponseEntity(HttpStatus.NO_CONTENT);

    }
}
