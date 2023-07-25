package com.oldandsea.pcb.interceptor;

import com.oldandsea.pcb.domain.entity.Session;
import com.oldandsea.pcb.service.SessionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;


@Slf4j
@RequiredArgsConstructor
public class LoginCheckInterceptor implements HandlerInterceptor {
    private final SessionService sessionService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(request.getMethod().equals("OPTIONS")) {
            return true;
        }
        Cookie[] cookies = request.getCookies();
        String sessionId = null;
        for(Cookie cookie : cookies) {
            if(Objects.equals(cookie.getName(), "PCBSESSIONID")) {
                sessionId = cookie.getValue();
                break;
            }
        }

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