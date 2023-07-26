package com.oldandsea.pcb.domain.dto.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberLoginRequestDTO {
    @NotEmpty(message = "Please write uid")
    private String uid;
    @NotEmpty(message = "Please write pwd")
    private String pwd;
    @Builder
    public MemberLoginRequestDTO(String uid, String pwd) {
        this.uid = uid;
        this.pwd = pwd;
    }
}
