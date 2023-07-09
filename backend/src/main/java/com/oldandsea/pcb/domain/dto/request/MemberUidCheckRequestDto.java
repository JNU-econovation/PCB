package com.oldandsea.pcb.domain.dto.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberUidCheckRequestDto {
    private String uid;
    @Builder
    public MemberUidCheckRequestDto(String uid) {
        this.uid = uid;
    }
}
