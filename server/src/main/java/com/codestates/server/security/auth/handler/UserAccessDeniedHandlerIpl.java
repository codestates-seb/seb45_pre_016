package com.codestates.server.security.auth.handler;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.access.AccessDeniedException;
import com.codestates.server.security.auth.utils.ErrorResponder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 접근 거부 응답 보내는 클래스
 */
@Slf4j
@Component
public class UserAccessDeniedHandlerIpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {
        // 클라이언트에게 접근 거부 응답 보내기
        ErrorResponder.sendErrorResponse(response, HttpStatus.FORBIDDEN);
        // 접근 거부 에러 메세지 기록
        log.warn("Forbidden error happened: {}", accessDeniedException.getMessage());
    }
}