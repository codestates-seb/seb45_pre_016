package com.codestates.server.security.auth.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

@Component
public class JwtTokenizer {

    @Getter
    @Value("${jwt.key}")
    private String secretKey;   // JWT 생성, 검증에 사용되는 SecretKey 정보

    @Getter
    @Value("${jwt.access-token-expiration-minutes}")
    private int accessTokenExpirationMinutes;   // Access Token에 대한 만료 시간 정보

    @Getter
    @Value("${jwt.refresh-token-expiration-minutes}")
    private int refreshTokenExpirationMinutes;   // Refresh Token 에 대한 만료 시간 정보

    /*
     * Plain Text 형태의 SecretKey -> Base64 형식 문자열로 인코딩
     * jjwt 이후 Plain Text 자체 사용 권장 X -> StandardCharsets.UTF_8
     */
    public String encodeBase64SecretKey(String secretKey) {

        return Encoders.BASE64.encode(secretKey.getBytes(StandardCharsets.UTF_8));

    }

    /**
     *
     * 인증된 사용자에게 JWT 최초 발급
     * JWT 생성 메서드
     *  @param claims : (인증된) 사용자 정보
     *  @param subject : 제목 추가
     * issuedAt : 발행일자 설정
     *  @param expiration : 만려 일시
     * signwith : 서명을 위한 Key 객체 설정
     * compact : JWT 생성 및 직렬화
     */
//    public String generateAccessToken(Map<String, Object> claims,
//                                      Long subject,
//                                      Date expiration,
//                                      String base64EncodedSecretKey) {
//
//        Key key = getKeyFromBase64EncodedKey(base64EncodedSecretKey);
//
//        return Jwts.builder()
//                .setClaims(claims)
//                .setSubject(String.valueOf(subject))
//                .setIssuedAt(Calendar.getInstance().getTime())
//                .setExpiration(expiration)
//                .signWith(key)
//                .compact();
//    }

    public String generateAccessToken(Map<String, Object> claims,
                                      String subject,
                                      Date expiration,
                                      String base64EncodedSecretKey) {

        Key key = getKeyFromBase64EncodedKey(base64EncodedSecretKey);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(Calendar.getInstance().getTime())
                .setExpiration(expiration)
                .signWith(key)
                .compact();
    }

    /**
     * refreshToken 생성
     *
     * @param subject
     * @param expiration
     * @param base64EncodedSecretKey
     * @return
     */
    public String generateRefreshToken(String subject, Date expiration, String base64EncodedSecretKey) {
        Key key = getKeyFromBase64EncodedKey(base64EncodedSecretKey);

        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(Calendar.getInstance().getTime())
                .setExpiration(expiration)
                .signWith(key)
                .compact();
    }

    public Jws<Claims> getClaims(String jws, String base64EncodedSecretKey) {

        Key key = getKeyFromBase64EncodedKey(base64EncodedSecretKey);

        Jws<Claims> claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build().parseClaimsJws(jws);

        return claims;
    }

    /*
     * 위조 및 변조 여부 확인
     * Secret Key 로 Signature 검증
     * -> 성공하면 JWT 파싱 후 Claims 얻을 수 있음 (parseClaimsJws()메서드)
     * 리턴할 필요는 X
     */
    public void verifySignature(String jws, String base64EncodedSecretKey) {
        Key key = getKeyFromBase64EncodedKey(base64EncodedSecretKey);

        Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(jws);
    }

    /**
     *  토큰 유효기간
     * @param expirationMinutes
     * @return
     */
    public Date getTokenExpiration(int expirationMinutes) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, expirationMinutes);
        Date expiration = calendar.getTime();

        return expiration;
    }

    private Key getKeyFromBase64EncodedKey(String base64EncodedSecretKey) {

        byte[] keyBytes = Decoders.BASE64.decode(base64EncodedSecretKey);
        Key key = Keys.hmacShaKeyFor(keyBytes);

        return key;
    }

}
