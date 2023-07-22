package com.oldandsea.pcb.domain.dto.layer;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentUpdateContentList {
    private String color;
    private String content;
    private Long commentId;
    @Builder
    public CommentUpdateContentList(String color, String content, Long commentId) {
        this.color = color;
        this.content = content;
        this.commentId = commentId;
    }
}
