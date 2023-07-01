package com.oldandsea.pcb.domain.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardListResponseDto {
    private Long boardId;
    private String title;
    private String content;
    private List<String> boardTagList;
    private Long createdAt;

    @Builder
    public BoardListResponseDto(Long boardId, String title, String content, List<String> boardTagList, Long createdAt) {
        this.boardId = boardId;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;

         /* board에 연결된 boardtag 리스트를 스트림으로 가져오고 map()을 사용하여 각 boardtag에서 tag이름을 가져온다.
        그 결과를 리스트로 수집하여 boardTagList에 저장
         */

        this.boardTagList = boardTagList;
    }
}

