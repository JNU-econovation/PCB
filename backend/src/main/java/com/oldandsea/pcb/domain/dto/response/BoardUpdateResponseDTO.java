package com.oldandsea.pcb.domain.dto.response;

import com.oldandsea.pcb.domain.dto.layer.TagDTO;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardUpdateResponseDTO {
    private Long boardId;
    private String title;
    private String content;
    private List<TagDTO> boardTagList;
    @Builder
    public BoardUpdateResponseDTO(Long boardId, String title, String content, List<TagDTO> boardTagList) {
        this.boardId = boardId;
        this.title = title;
        this.content = content;
        this.boardTagList = boardTagList;
    }
}
