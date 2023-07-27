package com.oldandsea.pcb.domain.dto.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardUpdateRequestDTO {
    @NotEmpty
    private String title;
    @NotEmpty
    private String content;
    @NotEmpty
    private List<String> boardTagList;

    @Builder
    public BoardUpdateRequestDTO(String title, String content, List<String> boardTagList) {
        this.title = title;
        this.content = content;
        this.boardTagList = boardTagList;
    }
}
