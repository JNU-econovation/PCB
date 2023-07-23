package com.oldandsea.pcb.domain.dto.request;

import com.oldandsea.pcb.domain.entity.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberCreateRequestDTO {
    @NotNull(message = "Please write uid")
    @Size(min = 5, max = 20, message = "5에서 20자 이내여야 합니다")
    @Pattern(regexp = "^(?=.*[a-zA-Z])[a-zA-Z0-9]+$", message = "영어만 사용하거나 영어와 함께 숫자를 사용할 수 있습니다")
    private String uid;
    @NotNull(message = "Please write pwd")
    @Size(min = 8, max = 20, message = "8에서 20자 이내여야 합니다")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@#$%^&+=!~`<>,./?;:'\"\\[\\]{}\\\\()|_-])\\S*$", message = "영문, 숫자, 특수문자가 포함되어야하고 공백이 포함될 수 없습니다.")
    private String pwd;
    @NotNull(message = "Please write nickname")
    @Size(min = 3, max = 10, message = "3에서 10자 이내여야 합니다")
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
