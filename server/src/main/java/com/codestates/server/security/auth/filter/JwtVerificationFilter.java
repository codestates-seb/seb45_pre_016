package com.codestates.server.security.auth.filter;

import com.codestates.server.security.auth.jwt.JwtTokenizer;
import com.codestates.server.security.auth.utils.CustomAuthorityUtils;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/*
 * JWT 검증 및 인증 필터
 *
 * 요청당 한 번 실행
 * 인증 헤더의 JWT 토큰을 검증하고 유효한 경우 사용자의 권한을 추출
 * 보안 컨텍스트에 인증 정보 설정
 */
@AllArgsConstructor
public class JwtVerificationFilter extends OncePerRequestFilter {

    private final JwtTokenizer jwtTokenizer;
    private final CustomAuthorityUtils authorityUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        try {
            // JWT 토큰 검증, 권한 정보 추출
            Map<String, Object> claims = verifyJws(request);
            // 추출 권한 정보를 보안 컨텍스트에 인증 정보로 설정
            setAuthenticationToContext(claims);
        } catch (SignatureException se) {
            request.setAttribute("exception", se);
        } catch (ExpiredJwtException ee) {
            request.setAttribute("exception", ee);
        } catch (Exception e) {
            request.setAttribute("exception", e);
        }

        // 필터 체인 계속 진행
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {

        // 요청 헤더에 Aythorization 헤더가 있는지 확인하하고 값이 Bearer 로 시작하는지 확인
        String authorization = request.getHeader("Authorization");
        return authorization == null || !authorization.startsWith("Bearer");
    }

    /*
     * JWT 토큰 검증, 권한 정보 추출
     */
    private Map<String, Object> verifyJws(HttpServletRequest request) {

        // 헤더 Authorization 부분 불러와서 "Bearer "부분 지우기
        String jws = request.getHeader("Authorization").replace("Bearer ", "");
        String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getSecretKey());

        // 헤더에서 가지고 온 토큰이랑 계정의 인코딩된 토큰 동일한지 검증 후 추출하고 반환
        Map<String, Object> claims = jwtTokenizer.getClaims(jws, base64EncodedSecretKey).getBody();

        return claims;
    }

    private void setAuthenticationToContext(Map<String, Object> claims) {

        // 사용자명, 역할 정보를 추출
        // ⭐ 여기의 username은 토큰의 클레임 중 "username"입니다. userName 시 오류납니다!! 수정 금지!!
        String username = (String) claims.get("username");

        // 사용자명과 역할 정보 추출
        List<GrantedAuthority> authorities = authorityUtils.createAuthorities((List)claims.get("roles"));

        Authentication authentication = new UsernamePasswordAuthenticationToken(username, null, authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
