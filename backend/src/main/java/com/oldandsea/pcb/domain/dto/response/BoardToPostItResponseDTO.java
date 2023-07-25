package com.oldandsea.pcb.domain.dto.response;

import com.oldandsea.pcb.domain.dto.layer.TagDTO;
import com.oldandsea.pcb.domain.entity.BoardTag;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardToPostItResponseDTO {
    private Long boardId;
    private String title;
    private List<CommentResponseDTO> boardCommentList;
    private Long createdAt;
    private String nickname;
    private List<BoardTag> boardTagList;
    @Builder
    public BoardToPostItResponseDTO(Long boardId, String title, List<CommentResponseDTO> boardCommentList, Long createdAt, String nickname,
                                    List<BoardTag> boardTagList) {
        this.boardId = boardId;
        this.title = title;
        this.boardCommentList = boardCommentList;
        this.createdAt = createdAt;
        this.nickname = nickname;
        this.boardCommentList = boardCommentList;
    }
}