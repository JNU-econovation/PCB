package com.oldandsea.pcb.interceptor;

import com.oldandsea.pcb.config.SessionConst;
import com.oldandsea.pcb.config.exception.NotAuthenticatedException;
import com.oldandsea.pcb.domain.entity.Session;
import com.oldandsea.pcb.domain.repository.SessionRepository;
import com.oldandsea.pcb.service.SessionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class LoginCheckInterceptor implements HandlerInterceptor {
    private final SessionService sessionService;
    private final SessionRepository sessionRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession(false);

        Optional<Session> dbSession = sessionRepository.findBySessionId(session.getId());
        Session dbSessionCheck = dbSession.get();
        if (session ==null || dbSession.isEmpty()) {
            log.info("미인증 사용자 요청");
            throw new NotAuthenticatedException("Plase login first");
        }
        Session activeSession = dbSession.get();
        activeSession.updateSession();
        sessionRepository.save(activeSession);

        if(dbSessionCheck.getModifiedAt().plusMinutes(30).isBefore(LocalDateTime.now())) {
            sessionService.deleteSession(dbSessionCheck.getSessionId());
            throw new NotAuthenticatedException("Please login first");
        }
        return true;
    }
}