package com.oldandsea.pcb.service;

import com.oldandsea.pcb.domain.dto.layer.SessionCreateDto;
import com.oldandsea.pcb.domain.dto.response.MemberLoginResponseDto;
import com.oldandsea.pcb.domain.dto.response.MemberResponseDto;
import com.oldandsea.pcb.domain.entity.Session;
import com.oldandsea.pcb.domain.repository.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SessionService {
    private final SessionRepository sessionRepository;

    @Transactional
    public MemberLoginResponseDto createSession(MemberResponseDto loginResult, String sessionId) {
        Session dbSession = createSession(sessionId, loginResult.getMemberId());
        return MemberLoginResponseDto.builder()
                .sessionId(dbSession.getId())
                .build();
    }

    @Transactional
    public void deleteSession(String sessionId) {
        sessionRepository.deleteSessionBySessionId(sessionId);
    }

    private Session createSession(String sessionId, Long memberId) {
        Session dbSession = Session.builder()
                .sessionId(sessionId)
                .memberId(memberId)
                .build();
        return sessionRepository.save(dbSession);
    }


}


