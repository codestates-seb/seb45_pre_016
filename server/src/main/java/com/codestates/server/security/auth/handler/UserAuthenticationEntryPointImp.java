package com.codestates.server.security.auth.handler;

import com.codestates.server.security.auth.utils.ErrorResponder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class UserAuthenticationEntryPointImp implements AuthenticationEntryPoint {
    /**
     * 인증 예외 발생될 때 호출, 오류 응답 보내고 메세지 로깅 작업 처리하는 commence 메서드
     *
     * @param request that resulted in an <code>AuthenticationException</code>
     * @param response so that the user agent can begin authentication
     * @param authException that caused the invocation
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {

        // 요청 속성에서 추가 예외 정보를 가져옴
        Exception exception = (Exception) request.getAttribute("exception");
        // ErrorResponder 유틸리티 클래스를 사용하여 권한 없음 오류 응답을 전송
        ErrorResponder.sendErrorResponse(response, HttpStatus.UNAUTHORIZED);
        // 예외 메세지 로깅
        logExceptionMessage(authException, exception);
    }

    /**
     * 제공된 예외 또는 인증 예외에서 예외 메시지를 로깅하는 메서드
     *
     * @param authException 사용자 인증 중에 발생한 인증 예외
     * @param exception 인증 프로세스와 관련된 추가 예외가 있는 경우 제공
     */
    private void logExceptionMessage(AuthenticationException authException, Exception exception) {
        // 사용 가ㅡㅇ한 예외에 따라 로깅 메세지 결정
        String message = exception != null ? exception.getMessage() : authException.getMessage();
        // 권한 없음 오류 발생 경고 메세지
        log.warn("Unauthorized error happend: {}", message);
    }
}