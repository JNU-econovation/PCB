package com.oldandsea.pcb.domain.dto.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberUidCheckRequestDTO {
    @NotNull(message = "Please write uid")
    private String uid;
    @Builder
    public MemberUidCheckRequestDTO(String uid) {
        this.uid = uid;
    }
}
