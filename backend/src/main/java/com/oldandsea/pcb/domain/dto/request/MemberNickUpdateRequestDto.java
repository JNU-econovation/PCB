package com.oldandsea.pcb.domain.dto.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberNickUpdateRequestDto {
    private String nickname;
    @Builder
    public MemberNickUpdateRequestDto(String nickname) {
        this.nickname = nickname;
    }
}
