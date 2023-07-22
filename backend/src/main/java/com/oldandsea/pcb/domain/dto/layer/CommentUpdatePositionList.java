package com.oldandsea.pcb.domain.dto.layer;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentUpdatePositionList {
    @NotNull(message = "Please write after")
    private Long after;
    @NotNull(message = "Please write position")
    private String position;
    @NotNull(message = "Please write commentId")
    private Long commentId;
    @Builder
    public CommentUpdatePositionList(Long after, String position, Long commentId) {
        this.after = after;
        this.position = position;
        this.commentId = commentId;
    }
}
