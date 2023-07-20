package com.oldandsea.pcb.domain.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentResponseDTO {
    private Long commentId;
    private String content;
    private String color;
    private String nickname;
    private Long after;
    private String position;
    @Builder
    public CommentResponseDTO(Long commentId, String content, String color, String nickname, Long after, String position) {
        this.commentId = commentId;
        this.content = content;
        this.color = color;
        this.nickname = nickname;
        this.after = after;
        this.position = position;
    }
}
