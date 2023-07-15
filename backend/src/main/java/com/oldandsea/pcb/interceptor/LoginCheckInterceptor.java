package com.oldandsea.pcb.interceptor;


import com.oldandsea.pcb.config.exception.NotAuthenticatedException;
import com.oldandsea.pcb.domain.entity.Session;
import com.oldandsea.pcb.domain.repository.SessionRepository;
import com.oldandsea.pcb.service.SessionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;


@Slf4j
@RequiredArgsConstructor
public class LoginCheckInterceptor implements HandlerInterceptor {
    private final SessionService sessionService;
    private final SessionRepository sessionRepository;

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
        if (sessionIdFromCookie != null) {
            request.setAttribute("PCBSESSIONID", sessionIdFromCookie);
        }

        Session dbSession = sessionRepository.findBySessionId(sessionIdFromCookie).orElseThrow(
                () -> new NotAuthenticatedException("Please login first")
        );
        if(dbSession.getModifiedAt().plusMinutes(30).isBefore(LocalDateTime.now())) {
            sessionService.deleteSession(dbSession.getSessionId());
            throw new NotAuthenticatedException("Please login first");
        }

        dbSession.updateSession();
        sessionRepository.save(dbSession);


        return true;
    }
}