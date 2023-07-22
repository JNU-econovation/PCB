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
    
    @Builder
    public CommentUpdatePositionReqeustDTO(List<CommentUpdatePositionList> updatePositionList) {
        this.updatePositionList = updatePositionList;
    }
}
