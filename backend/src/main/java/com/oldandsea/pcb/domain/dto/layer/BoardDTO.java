package com.oldandsea.pcb.domain.dto.layer;

import com.oldandsea.pcb.domain.entity.Board;
import com.oldandsea.pcb.domain.entity.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardDTO {
    private Long boardId;
    private String title;
    private String content;
    private List<TagDTO> boardTagList;
    @Builder
    public BoardDTO(Long boardId, String title, String content, List<TagDTO> boardTagList) {
        this.boardId = boardId;
        this.title = title;
        this.content = content;
        this.boardTagList = boardTagList;
    }
}
