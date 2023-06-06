package com.oldandsea.pcb.domain.dto;


import com.oldandsea.pcb.domain.entity.BoardTag;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BoardTagDto {
    private Long boarTagId;
    private Long boardId;
    private Long tagId;
    @Builder
    private BoardTagDto(BoardTag boardTag) {
        this.boarTagId = boardTag.getBoardTagId();
        this.boardId = boardTag.getBoard().getBoardId();
        this.tagId = boardTag.getTag().getTagId();
    }
}
