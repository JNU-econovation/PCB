package com.oldandsea.pcb.domain.repository;

import com.oldandsea.pcb.domain.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

public interface SessionRepository extends JpaRepository<Session, Long> {
    Optional<Session> findBySessionId(String sessionId);
    @Modifying
    @Query("delete from Session s where s.sessionId not in (:sessionId) ")
    void deleteDuplicateSession(@Param("sessionId") String sessionId);
    @Modifying
    @Query("delete from Session s where s.sessionId = :sessionId")
    void deleteSessionBySessionId(@Param("sessionId") String sessionId);
}
