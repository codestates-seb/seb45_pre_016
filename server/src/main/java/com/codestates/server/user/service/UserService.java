package com.codestates.server.user.service;

import com.codestates.server.answer.entity.Answer;
import com.codestates.server.answer.repository.AnswerRepository;
import com.codestates.server.exception.BusinessLogicException;
import com.codestates.server.exception.ExceptionCode;
import com.codestates.server.security.auth.utils.AuthUserUtils;
import com.codestates.server.question.entity.Question;
import com.codestates.server.question.repository.QuestionRepository;
import com.codestates.server.security.auth.utils.CustomAuthorityUtils;
import com.codestates.server.security.help.UserRegistrationApplicationEvent;
import com.codestates.server.user.entity.User;
import com.codestates.server.user.repository.UserRepository;
import org.springframework.context.ApplicationEventPublisher;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
@AllArgsConstructor
//@Transactional
public class UserService {

    private final UserRepository userRepository;
    // 내부에서 발생하는 사건을 다른 곳에 알릴 수 있음
    private final ApplicationEventPublisher publisher;

    private final PasswordEncoder passwordEncoder;
    private final CustomAuthorityUtils authorityUtils;

    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;

    // 회원 가입에 대한 메서드
    public User createUser(User user) {

        verifyExistsUser(user.getEmail());

        // Password 단방향 암호화
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);

        // DB에 등록하는 User 의 Role 정보를 생성하고 저장
        List<String> roles = authorityUtils.createRoles(user.getEmail());
        user.setRoles(roles);

        User savedUser = userRepository.save(user);

        publisher.publishEvent(new UserRegistrationApplicationEvent(savedUser));
        return savedUser;
    }

    // 회원 정보 수정에 대한 메서드
    public User updateUser(User user) {

        User getUser = getVerifiedUser(user.getUserId());

        // 로그인 User의 아이디와 회원정보를 가진 user의 아이디가 다르면 예외 던지기
        if(!getLoginUser().getUserId().equals(getUser.getUserId()))
            throw new BusinessLogicException(ExceptionCode.UNAUTHORIZED_USER);   // 🚨 예외처리

        Optional.ofNullable(user.getUserName())
                .ifPresent(name -> getUser.setUserName(user.getUserName()));

        return userRepository.save(getUser);
    }

    // user 사용자 정보 가지고 오기
    public User getUser(Long userId) {
        User user = getVerifiedUser(userId);

        List<Question> questions = getUserQuestionByUserId(userId);
        List<Question> userQuestionList = new ArrayList<>();

        for (Question question : questions) {
            Question userQuestion = new Question();
            userQuestion.setQuestionId(question.getQuestionId());
            userQuestion.setTitle(question.getTitle());
            userQuestion.setContent(question.getContent());
            userQuestion.setCreatedAt(question.getCreatedAt());
            userQuestionList.add(userQuestion);
        }

        List<Answer> answers = getUserAnswerByUserId(userId);
        List<Answer> userAnswerList = new ArrayList<>();

        for(Answer answer : answers) {
            Answer userAnswer = new Answer();
            userAnswer.setAnswerId(answer.getAnswerId());
            userAnswer.setContent(answer.getContent());
            userAnswer.setCreatedAt(answer.getCreatedAt());
            userAnswerList.add(userAnswer);
        }

        user.setQuestions(userQuestionList);
        user.setAnswers(userAnswerList);

        return user;
    }

    private List<Question> getUserQuestionByUserId(Long userId) {
        return questionRepository.findAllByUserId(userId);
    }

    private List<Answer> getUserAnswerByUserId(Long userId) {
        return answerRepository.findAllByUserId(userId);
    }

    public List<User> getUsers() {
        // ⏹️ pagination 변경 예정

        List<User> users = userRepository.findAll();

        return users;
    }
/*
 * Pagination 구현한 getUsers()
 */
//    public Page<User> getUsers(int page, int size) {
//        // ⏹️ pagination 변경 예정
//        return userRepository.findAll(PageRequest.of(page, size,
//                Sort.by("userId").descending()));
//
//    }

    public void deleteUser(Long userId) {
        User getUser = getVerifiedUser(userId);

        // 로그인 User의 아이디와 회원정보를 가진 user의 아이디가 다르면 예외 던지기
        if(!getLoginUser().getUserId().equals(getUser.getUserId()))
            throw new BusinessLogicException(ExceptionCode.UNAUTHORIZED_USER);   // 🚨 예외처리

        userRepository.delete(getUser);
    }

    // 있는 user인지 확인하기 -> 없으면 예외 던지기("없는 회원 입니다.")
    // 🔔 Question & Comment 쓸 때 로그인 안 되어 있으면 해당 메서드 사용 해야 함
    private User getVerifiedUser(Long userId) {

        Optional<User> user = userRepository.findById(userId);

        User getUser =
                user.orElseThrow(() -> new BusinessLogicException(ExceptionCode.USER_NOT_FOUND));
        // 🚨 예외 처리
        return getUser;
    }

    // 중복 가입인지 확인 -> 있으면 예외 던지기 ("이미 있는 회원 입니다.")
    private void verifyExistsUser(String email) {

        Optional<User> user = userRepository.findByEmail(email);

        if(user.isPresent())
            throw new BusinessLogicException(ExceptionCode.USER_EXISTS);
        // 🚨 예외 처리
    }

    // 로그인한 User를 가지고 오는 메서드
    public User getLoginUser() {
        return userRepository.findByEmail(AuthUserUtils.getAuthUser().getName())
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.USER_NOT_FOUND)); // 🚨 예외처리
    }
}
