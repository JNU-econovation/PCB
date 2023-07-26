package com.oldandsea.pcb.domain.dto.layer;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentUpdatePositionList {
    @NotEmpty(message = "Please write after")
    private Long after;
    @NotEmpty(message = "Please write position")
    private String position;
    @NotEmpty(message = "Please write commentId")
    private Long commentId;
    @Builder
    public CommentUpdatePositionList(Long after, String position, Long commentId) {
        this.after = after;
        this.position = position;
        this.commentId = commentId;
    }
}
