package com.codestates.server.user.service;

import com.codestates.server.user.entity.User;
import com.codestates.server.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
//@Transactional
public class UserService {

    private final UserRepository userRepository;

    // 회원 가입에 대한 메서드
    public User createUser(User user) {

        verifyExistsUser(user.getEmail());

        return userRepository.save(user);
    }

    // 회원 정보 수정에 대한 메서드
    public User updateUser(User user) {

        User getUser = getVerifiedUser(user.getUserId());

        Optional.ofNullable(user.getUsername()).ifPresent(name -> getUser.setUsername(name));

        return userRepository.save(getUser);
    }

    // user 사용자 정보 가지고 오기
    public User getUser(long userId) {
        return getVerifiedUser(userId);
    }


    public List<User> getUsers() {
        // ⏹️ pagination 변경 예정
        List<User> users = userRepository.findAll();

        return users;
    }

    public void deleteUser(long userId) {
        User getUser = getVerifiedUser(userId);

        userRepository.delete(getUser);
    }

    // 있는 user인지 확인하기 -> 없으면 예외 던지기("없는 회원 입니다.")
    // 🔔 Question & Comment 쓸 때 로그인 안 되어 있으면 해당 메서드 사용 해야 함
    private User getVerifiedUser(long userId) {

        // ✅ userId로만 검증하면 되는지
        Optional<User> user = userRepository.findById(userId);

        User getUser =
                user.orElseThrow(() -> new RuntimeException());
        // 🚨 예외 처리
        return getUser;
    }

    // 중복 가입인지 확인 -> 있으면 예외 던지기 ("이미 있는 회원 입니다.")
    private void verifyExistsUser(String email) {

        Optional<User> user = userRepository.findByemail(email);

        if(user.isPresent())
            throw new RuntimeException();
        // 🚨 예외 처리
    }
}
