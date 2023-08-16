package com.codestates.server.security.config;

import com.codestates.server.security.auth.filter.JwtAuthenticationFilter;
import com.codestates.server.security.auth.filter.JwtVerificationFilter;
import com.codestates.server.security.auth.handler.UserAccessDeniedHandlerIpl;
import com.codestates.server.security.auth.handler.UserAuthenticationEntryPointImp;
import com.codestates.server.security.auth.handler.UserAuthenticationFailureHandler;
import com.codestates.server.security.auth.handler.UserAuthenticationSuccessHandler;
import com.codestates.server.security.auth.jwt.JwtTokenizer;
import com.codestates.server.security.auth.utils.CustomAuthorityUtils;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@AllArgsConstructor
public class SecurityConfiguration {

    private final JwtTokenizer jwtTokenizer;
    private final CustomAuthorityUtils authorityUtils;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .headers().frameOptions().sameOrigin()
                .and()
                .csrf().disable()
                .cors(withDefaults())   // SecurityConfiguration Bean 이용
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 세션 생성하지 않도록 설정
                .and()
                .formLogin().disable()
                .httpBasic().disable()
                .exceptionHandling()    // 예외 처리
                .authenticationEntryPoint(new UserAuthenticationEntryPointImp())
                .accessDeniedHandler(new UserAccessDeniedHandlerIpl())
                .and()
                .apply(new CustomFilterConfigurer())    // CustomFilterConfigurer 인스턴스 생성
                .and()
                .authorizeHttpRequests(authorize -> authorize
                        .antMatchers(HttpMethod.POST, "/users/signup").permitAll()         // 회원가입 전체 접근 가능
                        .antMatchers(HttpMethod.PATCH, "/users/mypage/edit/**").hasRole("USER")  // 마이페이지 수정 -> 해당 user만
                        .antMatchers(HttpMethod.GET, "/users").hasRole("ADMIN")     // userinfo (전체 회원 조회) -> 전체 접근 가능
                        .antMatchers(HttpMethod.GET, "/users/mypage/**").hasAnyRole("USER", "ADMIN")  // mypage 역할 가진 사용자
                        .antMatchers(HttpMethod.DELETE, "/users/**").hasRole("USER")  // user 삭제 page -> 해당 user 만
                        .antMatchers(HttpMethod.POST, "/questions/ask").hasAnyRole("USER", "ADMIN") // user/ask 역할 가진 사용자
                        .antMatchers(HttpMethod.PATCH, "/quesitons/**").hasAnyRole("USER", "ADMIN")
                        .antMatchers(HttpMethod.GET, "/question/**").permitAll()
                        .antMatchers(HttpMethod.GET, "/guestions/**").permitAll()   // 질문 조회 -> 전체 접근 가능
                        .antMatchers(HttpMethod.DELETE, "/questions/delete/**").hasAnyRole("USER", "ADMIN")

                );
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));    // 모든 출처에 대한 통신 허용
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PATCH", "DELETE"));   // 지정한 HTTPMethod에 대한 통신 허용

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // 구성한 CORS 정책 적용
        return source;
    }

    /*
      * JwtAuthenticationFilter 등록하는 CustomFilterConfigurer 클래스
      *
     */
    private class CustomFilterConfigurer extends AbstractHttpConfigurer<CustomFilterConfigurer, HttpSecurity> {

        @Override
        public void configure(HttpSecurity builder) throws Exception {

            AuthenticationManager authenticationManager = builder.getSharedObject(AuthenticationManager.class);

            JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(authenticationManager, jwtTokenizer);
            jwtAuthenticationFilter.setFilterProcessesUrl("/users/login");   // ⏹️  request URL 체크
            jwtAuthenticationFilter.setAuthenticationSuccessHandler(new UserAuthenticationSuccessHandler());
            jwtAuthenticationFilter.setAuthenticationFailureHandler(new UserAuthenticationFailureHandler());

            JwtVerificationFilter jwtVerificationFilter = new JwtVerificationFilter(jwtTokenizer, authorityUtils);

            builder
                    .addFilter(jwtAuthenticationFilter) // Spring Security Filter Chain에 추가
                    .addFilterAfter(jwtVerificationFilter, JwtAuthenticationFilter.class);  // JwtAuthenticationFilter 뒤에 jwtVerificationFilter 보내겠다
        }
    }
}
