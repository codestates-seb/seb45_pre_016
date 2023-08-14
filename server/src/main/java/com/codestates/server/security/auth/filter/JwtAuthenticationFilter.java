package com.codestates.server.security.auth.filter;

import com.codestates.server.security.auth.dto.LoginDto;
import com.codestates.server.security.auth.jwt.JwtTokenizer;
import com.codestates.server.user.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/*
 * userName & Password 기반의 인증처리를 위해
 * UsernamePasswordAuthenticationFilter 확장
 */
@AllArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    // userName & Password 인증 여부 판단
    private final AuthenticationManager authenticationManager;

    // 클라이언트가 성공할 경우 JWT 생성
    private final JwtTokenizer jwtTokenizer;

    /*
      * 메서드 내부에서 인증을 시도하는 로직 구현
      * objectMapper : userName & Password 를 DTO로 역직렬화 하기 위해 인스턴스 생성
      * authenticationToken : userName & Password 포함한 Token 생성
      * UsernamePasswordAuthenticationToken 을 AuthenticationManager 에게 전달
     */
    @SneakyThrows
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) {

        ObjectMapper objectMapper = new ObjectMapper();
        LoginDto loginDto = objectMapper.readValue(request.getInputStream(), LoginDto.class);

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getUserName(), loginDto.getPassword());
        return super.attemptAuthentication(request, response);
    }

    /*
      * 클라이언트의 인증 정보를 이용해 인증에 성공할 경우 호출
      * delegateAccessToken(user) : Access Token 생성
      * delegateRefreshToken(user) : Refresh Token 생성
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) {

        User user = (User) authResult.getPrincipal();

        String accessToken = delegateAccessToken(user);
        String refreshToken = delegateRefreshToken(user);

        // header에 Access Token 추가
        response.setHeader("Authorization", "Bearer " + accessToken);
        // header에 Refresh Token 추가
        response.setHeader("Refresh", refreshToken);
    }

    /*
     * Access Token 생성 메서드
     */
    private String delegateAccessToken(User user) {

        Map<String, Object> claims = new HashMap<>();

        claims.put("username", user.getEmail());
        claims.put("roles", user.getRoles());

        String subject = user.getEmail();
        Date expiration = jwtTokenizer.getTokenExpiration(jwtTokenizer.getAccessTokenExpirationMinutes());

        String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getSecretKey());

        String accessToken = jwtTokenizer.generateAccessToken(claims, subject, expiration, base64EncodedSecretKey);

        return accessToken;
    }

    /*
     * Refresh Token 생성 메서드
     */
    private String delegateRefreshToken(User user) {

        String subject = user.getEmail();

        Date expiration = jwtTokenizer.getTokenExpiration(jwtTokenizer.getRefreshTokenExirationMinutes());
        String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getSecretKey());

        String refreshToken = jwtTokenizer.generateRefreshToken(subject, expiration, base64EncodedSecretKey);

        return refreshToken;
    }
}
