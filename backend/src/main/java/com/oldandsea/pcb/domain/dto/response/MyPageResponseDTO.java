package com.oldandsea.pcb.domain.dto.response;

import com.oldandsea.pcb.domain.dto.layer.BoardDTO;
import com.oldandsea.pcb.domain.dto.layer.CommentDTO;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MyPageResponseDTO {
    private List<BoardDTO> myBoardList;
    private List<CommentDTO> myCommentList;
    @Builder
    public MyPageResponseDTO(List<BoardDTO> myBoardList, List<CommentDTO> myCommentList) {
        this.myBoardList = myBoardList;
        this.myCommentList = myCommentList;
    }
}
