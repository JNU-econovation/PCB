package com.oldandsea.pcb.domain.dto.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardUpdateRequestDto {
    private String title;
    private String content;
    private List<String> boardTagList;

    @Builder
    public BoardUpdateRequestDto(String title, String content, List<String> boardTagList) {
        this.title = title;
        this.content = content;
        this.boardTagList = boardTagList;
    }
}
