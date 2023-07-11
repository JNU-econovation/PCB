package com.oldandsea.pcb.domain.dto.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardUpdateRequestDto {
    private String nickname;
    private String pwd;
    @Builder
    public BoardUpdateRequestDto(String nickname, String pwd) {
        this.nickname = nickname;
        this.pwd = pwd;
    }
}
