package com.codestates.server.security.config;

import com.codestates.server.security.auth.filter.JwtAuthenticationFilter;
import com.codestates.server.security.auth.jwt.JwtTokenizer;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
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

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .headers().frameOptions().sameOrigin()
                .and()
                .csrf().disable()
                .cors(withDefaults())   // SecurityConfiguration Bean 이용
                .formLogin().disable()
                .httpBasic().disable()
                .apply(new CustomFilterConfigurer())    // CustomFilterConfigurer 인스턴스 생성
                .and()
                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest().permitAll()
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

            builder.addFilter(jwtAuthenticationFilter); // Spring Security Filter Chain에 추가

        }
    }
}
