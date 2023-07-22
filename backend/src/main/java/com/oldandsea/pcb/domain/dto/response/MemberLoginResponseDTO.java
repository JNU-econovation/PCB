package com.oldandsea.pcb.domain.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberLoginResponseDTO {
    private String sessionId;
    private String nickname;
    @Builder
    public MemberLoginResponseDTO(String sessionId, String nickname) {
        this.sessionId = sessionId;
        this.nickname = nickname;
    }

}
