package com.oldandsea.pcb.domain.dto;

import com.oldandsea.pcb.domain.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class MemberDto {
    private Long memberId;
    private String identification;
    private String pwd;
    private String nickname;
    @Builder
    private MemberDto(Member member) {
        this.memberId = member.getMemberId();
        this.identification = member.getIdentification();
        this.pwd = member.getPwd();
        this.nickname = member.getNickname();
    }
}
