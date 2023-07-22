package com.oldandsea.pcb.domain.dto.response;

import com.oldandsea.pcb.domain.dto.layer.CommentUpdatePositionList;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentUpdatePositionResponseDTO {
   private List<CommentUpdatePositionList> updatePositionList;
    @Builder
    public CommentUpdatePositionResponseDTO(List<CommentUpdatePositionList> updatePositionList) {
        this.updatePositionList = updatePositionList;
    }
}

