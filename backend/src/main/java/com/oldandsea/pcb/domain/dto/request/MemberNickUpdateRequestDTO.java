package com.oldandsea.pcb.domain.dto.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberNickUpdateRequestDTO {
    @NotEmpty
    private String nickname;
    @Builder
    public MemberNickUpdateRequestDTO(String nickname) {
        this.nickname = nickname;
    }
}
