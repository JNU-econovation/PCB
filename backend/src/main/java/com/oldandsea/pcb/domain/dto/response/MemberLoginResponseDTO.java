package com.oldandsea.pcb.domain.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberLoginResponseDTO {
    private String sessionId;
    @Builder
    public MemberLoginResponseDTO(String sessionId) {
        this.sessionId = sessionId;
    }

}
