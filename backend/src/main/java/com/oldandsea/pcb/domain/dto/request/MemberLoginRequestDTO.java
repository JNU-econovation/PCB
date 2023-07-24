package com.oldandsea.pcb.domain.dto.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberLoginRequestDTO {
    @NotNull(message = "Please write uid")
    private String uid;
    @NotNull(message = "Please write pwd")
    private String pwd;
    @Builder
    public MemberLoginRequestDTO(String uid, String pwd) {
        this.uid = uid;
        this.pwd = pwd;
    }
}