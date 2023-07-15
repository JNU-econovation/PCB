package com.oldandsea.pcb.domain.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardUpdateResponseDto {
    private Long boardId;
    private String title;
    private String content;
    private List<String> boardTagList;
    @Builder
    public BoardUpdateResponseDto(Long boardId, String title, String content, List<String> boardTagList) {
        this.boardId = boardId;
        this.title = title;
        this.content = content;
        this.boardTagList = boardTagList;
    }
}
