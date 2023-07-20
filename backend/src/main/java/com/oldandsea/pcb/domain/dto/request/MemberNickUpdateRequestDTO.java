package com.oldandsea.pcb.domain.dto.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberNickUpdateRequestDTO {
    private String nickname;
    @Builder
    public MemberNickUpdateRequestDTO(String nickname) {
        this.nickname = nickname;
    }
}
