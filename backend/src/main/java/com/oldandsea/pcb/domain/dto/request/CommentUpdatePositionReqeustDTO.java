package com.oldandsea.pcb.domain.dto.request;

import com.oldandsea.pcb.domain.dto.layer.CommentUpdatePositionList;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentUpdatePositionReqeustDTO {
    private List<CommentUpdatePositionList> updatePositionList;
    private Long boardId;
    
    @Builder
    public CommentUpdatePositionReqeustDTO(List<CommentUpdatePositionList> updatePositionList, Long boardId) {
        this.updatePositionList = updatePositionList;
        this.boardId = boardId;
    }
}
