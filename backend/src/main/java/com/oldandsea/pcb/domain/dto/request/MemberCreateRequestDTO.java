package com.oldandsea.pcb.domain.dto.request;

import com.oldandsea.pcb.domain.entity.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberCreateRequestDTO {
    @NotNull(message = "Please write uid")
    private String uid;
    @NotNull(message = "Please write pwd")
    private String pwd;
    @NotNull(message = "Please write nickname")
    private String nickname;
    @Builder
    public MemberCreateRequestDTO(String uid, String pwd, String nickname) {
        this.uid = uid;
        this.pwd = pwd;
        this.nickname = nickname;
    }
    public Member toEntity() {
        return Member.builder()
                .uid(uid)
                .pwd(pwd)
                .nickname(nickname)
                .build();
    }
}
