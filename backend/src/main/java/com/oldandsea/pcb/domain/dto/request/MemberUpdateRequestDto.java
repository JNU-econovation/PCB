package com.oldandsea.pcb.domain.dto.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberUpdateRequestDto {
    private String nickname;
    private String pwd;
    @Builder
    public MemberUpdateRequestDto(String nickname, String pwd) {
        this.nickname = nickname;
        this.pwd = pwd;
    }
}
