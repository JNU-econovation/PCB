package com.oldandsea.pcb.domain.dto.response;


import com.oldandsea.pcb.domain.entity.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberResponseDto {
    private Long memberId;

    private String identification;

    private String pwd;

    private String nickname;
    @Builder
    public MemberResponseDto(Long memberId, String identification, String pwd, String nickname) {
        this.memberId = memberId;
        this.identification = identification;
        this.pwd = pwd;
        this.nickname = nickname;
    }
    public Member toEntity() {
        return Member.builder()
                .identification(identification)
                .pwd(pwd)
                .nickname(nickname)
                .build();
    }
}
