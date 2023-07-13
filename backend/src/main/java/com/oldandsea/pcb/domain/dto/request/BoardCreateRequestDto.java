package com.oldandsea.pcb.domain.dto.request;

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
public class BoardCreateRequestDto {
    @NotNull(message = "Please  write title")
    private String title;
    @NotNull(message = "Please write content")
    private String content;
    @NotNull(message = "Please write tags")
    private List<String> boardTagList;
    @Builder
    public BoardCreateRequestDto (Long boardId,String title, String content, List<String> boardTagList) {

        this.title = title;
        this.content = content;
        this.boardTagList = boardTagList;
    }
    public Board toEntity(Member member) {
        return Board.builder()
                .title(title)
                .content(content)
                .member(member)
                .build();
    }
}


