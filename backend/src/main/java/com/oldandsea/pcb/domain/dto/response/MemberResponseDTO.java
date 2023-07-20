package com.oldandsea.pcb.domain.dto.response;

import com.oldandsea.pcb.domain.entity.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberResponseDTO {
    private Long memberId;

    private String uid;

    private String pwd;

    private String nickname;
    @Builder
    public MemberResponseDTO(Long memberId, String uid, String pwd, String nickname) {
        this.memberId = memberId;
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
