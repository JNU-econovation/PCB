package com.oldandsea.pcb.domain.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentCreateResponseDTO {
    private Long commentId;
    private String content;
    private String nickmame;
    private Long after;
    private String position;
    private Long createdAt;
    @Builder
    public CommentCreateResponseDTO(Long commentId, String content, String nickmame, Long after, String position, Long createdAt) {
        this.commentId = commentId;
        this.content = content;
        this.nickmame = nickmame;
        this.after = after;
        this.position = position;
        this.createdAt = createdAt;
    }
}
