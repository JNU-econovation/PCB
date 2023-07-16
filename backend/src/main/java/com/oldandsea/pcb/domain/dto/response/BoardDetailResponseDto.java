package com.oldandsea.pcb.domain.dto.response;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardDetailResponseDto {
    private Long boardId;
    private String title;
    private String content;
    private Long createdAt;
    private List<String> boardTagList;
    @Builder
    public BoardDetailResponseDto(Long boardId, String title, String content, Long createdAt, List<String> boardTagList) {
        this.boardId = boardId;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.boardTagList = boardTagList;
    }
}
