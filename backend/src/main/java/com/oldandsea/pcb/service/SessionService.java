package com.oldandsea.pcb.service;

import com.oldandsea.pcb.config.exception.NotAuthenticatedException;
import com.oldandsea.pcb.domain.dto.response.MemberLoginResponseDTO;
import com.oldandsea.pcb.domain.dto.response.MemberResponseDTO;
import com.oldandsea.pcb.domain.entity.Session;
import com.oldandsea.pcb.domain.repository.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
public class SessionService {
    private final SessionRepository sessionRepository;

    @Transactional
    public MemberLoginResponseDTO createSession(MemberResponseDTO loginResult, String sessionId) {
        Session dbSession1 = createSession1(sessionId, loginResult.getMemberId());
        return MemberLoginResponseDTO.builder()
                .sessionId(dbSession1.getSessionId())
                .build();
    }

    @Transactional
    public void deleteSession(String sessionId) {
        sessionRepository.deleteSessionBySessionId(sessionId);
    }
    @Transactional
    public  Session createSession1(String sessionId, Long memberId) {
        Session dbSession = Session.builder()
                .sessionId(sessionId)
                .memberId(memberId)
                .build();
        return sessionRepository.save(dbSession);
    }

    @Transactional
    public Session sessionFindByKey(String sessionId) {
        return sessionRepository.findBySessionId(sessionId).orElseThrow(
                () -> new IllegalArgumentException("Session doesn't exsist")
        );
    }

    @Transactional
    public Session sessionCheck(String sessionId) {
        Session dbSession = sessionRepository.findBySessionId(sessionId).orElseThrow(
                () -> new NotAuthenticatedException("Please login first")
        );
        if(dbSession.getModifiedAt().plusMinutes(30).isBefore(LocalDateTime.now())) {
            deleteSession(dbSession.getSessionId());
            throw new NotAuthenticatedException("Please login first");
        }
        return dbSession;
    }

    @Transactional
    public Session sessionFindBySessionId(String sessionId) {
        return sessionRepository.findBySessionId(sessionId).orElseThrow(
                () -> new NotAuthenticatedException("Please login first")
        );
    }

    @Transactional
    public void sessionSave(Session dbsession) {
        sessionRepository.save(dbsession);
    }

}


