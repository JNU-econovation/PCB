package com.oldandsea.pcb.domain.dto.layer;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SessionCheckDto {
    private String sessionId;

    @Builder
    public SessionCheckDto(String sessionId) {
        this.sessionId = sessionId;
    }
}

