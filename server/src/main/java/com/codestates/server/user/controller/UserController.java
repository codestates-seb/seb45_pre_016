package com.codestates.server.user.controller;

import com.codestates.server.dto.MultiResponseDto;
import com.codestates.server.question.uri.UriCreator;
import com.codestates.server.user.dto.UserPatchDto;
import com.codestates.server.user.dto.UserPostDto;
import com.codestates.server.user.dto.UserResponseDto;
import com.codestates.server.user.entity.User;
import com.codestates.server.user.mapper.UserMapper;
import com.codestates.server.user.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/users")
@Validated
@AllArgsConstructor
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

    private final UserService userService;
    private final UserMapper mapper;

    /**
    * user 회원가입
    * @param userPostDto
     */
    @PostMapping("/signup")
    public ResponseEntity postUser(@Valid
                                   @RequestBody UserPostDto userPostDto) {

        User user = mapper.userPostDtoToUser(userPostDto);
        User createdUser = userService.createUser(user);

        URI location = UriCreator.createUri("/users/signup", user.getUserId());
        // ex) http://localhost:8080/signup/{user-id}

        ResponseEntity response = ResponseEntity.created(location).build();

//        return new ResponseEntity(response, HttpStatus.CREATED) ;
        return ResponseEntity.created(location).build();
    }

    /**
     * user 회원 정보수정
     * @param userPatchDto
     * ✅ Patch는 수정 기능이라 response로 Response했습니다!
     */
    @PatchMapping("/mypage/edit/{user-id}")
    public ResponseEntity patchUser(@PathVariable("user-id") @Positive Long userId,
                                    @Valid @RequestBody UserPatchDto userPatchDto) {

        userPatchDto.setUserId(userId);

        User user = userService.updateUser(mapper.userPatchDtoToUser(userPatchDto));
        UserResponseDto response = mapper.userToUserResponseDto(user);

        return new ResponseEntity(response, HttpStatus.OK);
    }

    /**
     * user 회원 마이페이지
     * @param userId
     */
    @GetMapping("/mypage/{user-id}")
    public ResponseEntity getUser(@PathVariable("user-id") @Positive Long userId) {

        User user = userService.getUser(userId);
        UserResponseDto response = mapper.userToUserResponseDto(user);

        return new ResponseEntity(response, HttpStatus.OK);
    }

    /*
     * user 회원 전체 조회
     */
    @GetMapping
    public ResponseEntity getUsers() {

        // ⏹️ Pagination 적용 전
        List<User> users = userService.getUsers();
        List<UserResponseDto> response = mapper.usersToUserResponseDto(users);

        return new  ResponseEntity(response, HttpStatus.OK);
    }

/*
 * Pagination 구현한 getUsers()
 */
//    @GetMapping
//    public ResponseEntity getUsers(@Positive @RequestParam int page,
//                                   @Positive @RequestParam int size) {
//
//        Page<User> userPages = userService.getUsers(page -1, size);
//        List<User> users = userPages.getContent();
//
//        return  new ResponseEntity(
//                new MultiResponseDto<>(mapper.usersToUserResponseDto(users), userPages), HttpStatus.OK);
//    }


    @DeleteMapping("/{user-id}")
    public ResponseEntity deleteUser(@PathVariable("user-id") @Positive Long userId) {

        userService.deleteUser(userId);

        return new ResponseEntity(HttpStatus.NO_CONTENT);

    }
}
