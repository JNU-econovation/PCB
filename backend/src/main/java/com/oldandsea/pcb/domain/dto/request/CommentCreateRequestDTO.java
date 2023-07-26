package com.oldandsea.pcb.domain.dto.request;

import com.oldandsea.pcb.domain.entity.Comment;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentCreateRequestDTO {
    @NotEmpty
    private Long boardId;
    @NotNull
    private String content;
    @NotEmpty
    private String position;

    private String color;

    @Builder
    public CommentCreateRequestDTO(Long boardId, String content, String position, String color) {
        this.boardId = boardId;
        this.content = content;
        this.position = position;
        this.color = color;
    }

}
