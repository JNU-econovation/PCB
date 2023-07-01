package com.oldandsea.pcb.domain.dto.response;

import lombok.*;

import java.util.List;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardDetailResponseDto {
    private Long boardId;
    private String title;
    private String content;
    private Long viewCount;
    private Long createdAt;
    private List<String> boardTagList;
    private Long memberId;
    @Builder
    public BoardDetailResponseDto(Long boardId, String title, String content, Long viewCount, Long createdAt, List<String> boardTagList, Long memberId) {
        this.boardId = boardId;
        this.title = title;
        this.content = content;
        this.viewCount = viewCount;
        this.createdAt = createdAt;

        /*board에 연결된 boardtag 리스트를 스트림으로 가져오고 map()을 사용하여 각 boardtag에서 tag이름을 가져온다.
        그 결과를 리스트로 수집하여 boardTagList에 저장
         */

        this.boardTagList = boardTagList;

        this.memberId = memberId;
    }
}
