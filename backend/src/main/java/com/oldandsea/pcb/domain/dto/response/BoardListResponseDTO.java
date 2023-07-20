package com.oldandsea.pcb.domain.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardListResponseDTO {
    private Long boardId;
    private String title;
    private String content;
    private List<String> boardTagList;
    private Long createdAt;
    private String nickname;


    @Builder
    public BoardListResponseDTO(Long boardId, String title, String content, List<String> boardTagList, Long createdAt, String nickname) {
        this.boardId = boardId;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.boardTagList = boardTagList;
        this.nickname = nickname;
    }
}

