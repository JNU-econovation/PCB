package com.oldandsea.pcb.interceptor;

import com.oldandsea.pcb.config.exception.NotAuthenticatedException;
import com.oldandsea.pcb.domain.entity.Session;
import com.oldandsea.pcb.service.SessionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@Slf4j
@RequiredArgsConstructor
public class LoginCheckInterceptor implements HandlerInterceptor {
    private final SessionService sessionService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String sessionId = request.getHeader("PCBSESSIONID");
        if (sessionId == null) {
            throw new IllegalArgumentException("Please login first");
        } else {
            Session dbSession = sessionService.sessionCheck(sessionId);
            dbSession.updateSession();
            sessionService.saveSession(dbSession);
            return true;
        }
    }
}