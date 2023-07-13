package com.oldandsea.pcb.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "session",indexes = @Index(name = "idx_session_key",columnList = "session_key"))
public class Session {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "session_key",nullable = false,unique = true)
    private String sessionId;
    @Column(name = "seesion_value",nullable = false,unique = true)
    private Long memberId;
    @CreatedDate
    private LocalDateTime createdAt;
    @Builder
    public Session(String sessionId, Long memberId) {
        this.sessionId = sessionId;
        this.memberId= memberId;
        this.createdAt = LocalDateTime.now();
    }
}
