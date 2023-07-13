package com.oldandsea.pcb.domain.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberLoginResponseDto {
    private Long sessionId;
    @Builder
    public MemberLoginResponseDto(Long sessionId) {
        this.sessionId = sessionId;
    }

}
