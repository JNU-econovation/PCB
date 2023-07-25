package com.oldandsea.pcb.service;

import com.oldandsea.pcb.config.exception.NotAuthenticatedException;
import com.oldandsea.pcb.domain.dto.request.MemberLoginRequestDTO;
import com.oldandsea.pcb.domain.dto.response.MemberLoginResponseDTO;
import com.oldandsea.pcb.domain.dto.layer.LoginDTO;
import com.oldandsea.pcb.domain.entity.Member;
import com.oldandsea.pcb.domain.entity.Session;
import com.oldandsea.pcb.domain.repository.MemberRepository;
import com.oldandsea.pcb.domain.repository.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
public class SessionService {
    private final SessionRepository sessionRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public MemberLoginResponseDTO createSession(LoginDTO loginResult, String sessionId, MemberLoginRequestDTO requestDTO) {
        Session dbSession1 = createSession1(sessionId, loginResult.getMemberId());
        return MemberLoginResponseDTO.builder()
                .sessionId(dbSession1.getSessionId())
                .nickname(findMemberUid(requestDTO.getUid()))
                .build();
    }

    @Transactional
    public void deleteSession(String sessionId) {
        sessionRepository.deleteSessionBySessionId(sessionId);
    }
    @Transactional
    public   Session createSession1(String sessionId, Long memberId) {
        Session dbSession = Session.builder()
                .sessionId(sessionId)
                .memberId(memberId)
                .build();
        return sessionRepository.save(dbSession);
    }

    @Transactional
    public Session sessionCheck(String sessionId) {
        Session dbSession = sessionRepository.findBySessionId(sessionId).orElseThrow(
                () -> new IllegalArgumentException("Please login first")
        );
        deleteDuplicateSession(dbSession);
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
    public void saveSession(Session dbsession) {
        sessionRepository.save(dbsession);
    }

    @Transactional
    public void deleteDuplicateSession(Session dbSession) {
        String sessionId = dbSession.getSessionId();
        sessionRepository.deleteDuplicateSession(sessionId);
    }
    private String findMemberUid(String memberUid) {
        Member member = memberRepository.findByUid(memberUid).orElseThrow(
                () -> new IllegalArgumentException("회원을 찾을 수 없습니다.")
        );
        return member.getNickname();
    }
}


