package com.oldandsea.pcb.domain.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberLoginResponseDto {
    private String sessionId;
    @Builder
    public MemberLoginResponseDto(String sessionId) {
        this.sessionId = sessionId;
    }

}
