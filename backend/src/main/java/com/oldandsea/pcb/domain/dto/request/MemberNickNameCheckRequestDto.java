package com.oldandsea.pcb.domain.dto.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberNickNameCheckRequestDto {
    @NotNull(message = "Please write nickname")
    private String nickname;
    @Builder
    public MemberNickNameCheckRequestDto(String nickname) {
        this.nickname = nickname;
    }
}
