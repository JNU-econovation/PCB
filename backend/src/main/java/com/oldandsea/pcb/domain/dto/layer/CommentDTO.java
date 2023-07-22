package com.oldandsea.pcb.domain.dto.layer;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentDTO {
    private String title;
    private String content;
    private List<TagDTO> boardTagList;
    @Builder
    public CommentDTO(String title, String content, List<TagDTO> boardTagList) {
        this.title = title;
        this.content = content;
        this.boardTagList = boardTagList;
    }
}
