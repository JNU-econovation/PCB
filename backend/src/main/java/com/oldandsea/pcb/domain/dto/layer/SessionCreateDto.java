package com.oldandsea.pcb.domain.dto.layer;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SessionCreateDto {
    private String sessionId;
    private Long memberId;
    @Builder
    public SessionCreateDto(String sessionId, Long memberId) {
        this.sessionId = sessionId;
        this.memberId = memberId;
    }
}
