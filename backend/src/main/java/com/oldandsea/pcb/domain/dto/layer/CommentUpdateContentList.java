package com.oldandsea.pcb.domain.dto.layer;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentUpdateContentList {
    private String color;
    @NotEmpty
    private String content;
    @NotEmpty
    private Long commentId;
    @Builder
    public CommentUpdateContentList(String color, String content, Long commentId) {
        this.color = color;
        this.content = content;
        this.commentId = commentId;
    }
}
