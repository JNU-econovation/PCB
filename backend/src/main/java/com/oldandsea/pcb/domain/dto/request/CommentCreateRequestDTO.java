package com.oldandsea.pcb.domain.dto.request;

import com.oldandsea.pcb.domain.entity.Comment;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentCreateRequestDTO {
    private Long boardId;
    private String content;
    private Long after;
    private String position;

    @Builder
    public CommentCreateRequestDTO(Long boardId, String content, Long after, String position) {
        this.boardId = boardId;
        this.content = content;
        this.after = after;
        this.position = position;
    }

}
