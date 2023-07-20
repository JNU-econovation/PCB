package com.oldandsea.pcb.interceptor;

import com.oldandsea.pcb.domain.entity.Session;
import com.oldandsea.pcb.service.SessionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@Slf4j
@RequiredArgsConstructor
public class LoginCheckInterceptor implements HandlerInterceptor {
    private final SessionService sessionService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Cookie[] cookies = request.getCookies();
        String sessionIdFromCookie = null;
        for (Cookie cookie : cookies) {
            if ("PCBSESSIONID".equals(cookie.getName())) {
                sessionIdFromCookie = cookie.getValue();
                break;
            }
        }
        Session dbSession = sessionService.sessionCheck(sessionIdFromCookie);
        dbSession.updateSession();
        sessionService.saveSession(dbSession);
        return true;
    }
}