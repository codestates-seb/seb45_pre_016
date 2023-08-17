package com.codestates.server.security.config;

import lombok.RequiredArgsConstructor;
import org.springframework.web.filter.CorsFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


@Configuration
@RequiredArgsConstructor
public class SecurityCorsConfig {


    @Bean
    public CorsFilter corsFilter() {
        // URL 기반의 CORS 설정 관리
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        // CorsConfiguraion config = new CorsConfiguration();
        CorsConfiguration config = new CorsConfiguration();
        // 요청에서 자격증명(쿠키, 인증 헤더 등) 을 허용
        config.setAllowCredentials(true);
        config.addAllowedOriginPattern("http://43.201.157.40:3000");

        // 응답헤더에 Authorization 헤더를 노출하도록 설정
        config.addExposedHeader("Authorization");

        config.addAllowedHeader("*");   // 모든 헤더 허용
        config.addAllowedMethod("GET"); // 특정 메서드만 허용
        config.addAllowedMethod("POST"); // 특정 메서드만 허용
        config.addAllowedMethod("PATCH"); // 특정 메서드만 허용
        config.addAllowedMethod("DELETE"); // 특정 메서드만 허용

        source.registerCorsConfiguration("/**", config); // corsConfiguration으로 등록

        return new CorsFilter(source);
    }
}

🔊 BE에 메시지 보내기
