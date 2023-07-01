package com.oldandsea.pcb.domain.dto.request;

import com.oldandsea.pcb.domain.entity.Board;
import com.oldandsea.pcb.domain.entity.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardCreateRequestDto {

    private String title;
    private String content;


    public Board toEntity(Member member) {
        return Board.builder()
                .title(title)
                .content(content)
                .member(member)
                .build();
    }
//    private List<BoardTag> conversion(List<String> boardTagList){
//        return boardTagList.stream()
//                .map(tag -> BoardTag.builder()
//                        .board()
//                        .tag()
//                        .build())
//                .collect(Collectors.toList());
//                }

    @Builder
    public BoardCreateRequestDto (Long boardId,String title, String content) {

        this.title = title;
        this.content = content;

    }
}


