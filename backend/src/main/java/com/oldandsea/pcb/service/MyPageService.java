package com.oldandsea.pcb.service;

import com.oldandsea.pcb.domain.dto.layer.BoardDTO;
import com.oldandsea.pcb.domain.dto.layer.CommentDTO;
import com.oldandsea.pcb.domain.dto.response.MyPageResponseDTO;
import com.oldandsea.pcb.domain.entity.Board;
import com.oldandsea.pcb.domain.entity.Comment;
import com.oldandsea.pcb.domain.repository.commentrepository.CommentRepository;
import com.oldandsea.pcb.domain.repository.boardrepository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MyPageService {
    private final BoardRepository boardRepository;
    private final TagService tagService;
    private final CommentRepository commentRepository;
    public MyPageResponseDTO myPage(Long memberId) {
        List<Board> boardList = boardListFindByMemberId(memberId);
        List<BoardDTO> boardDTOList = toBoardDTOList(boardList);
        List<Comment> commentList = commentListFindByMemberId(memberId);
        List<CommentDTO> commentDTOList = toCommentDTOList(commentList);
        return MyPageResponseDTO.builder()
                .myBoardList(boardDTOList)
                .myCommentList(commentDTOList)
                .build();
    }
    public List<BoardDTO> toBoardDTOList(List<Board> boards) {
        return boards.stream()
                .map(board -> BoardDTO.builder()
                        .boardId(board.getBoardId())
                        .content(board.getContent())
                        .title(board.getTitle())
                        .boardTagList(tagService.toTagDTOList(board))
                        .build())
                .collect(Collectors.toList());
    }
    public List<CommentDTO> toCommentDTOList(List<Comment> comments) {
        return comments.stream()
                .map(comment -> CommentDTO.builder()
                        .title(comment.getBoard().getTitle())
                        .content(comment.getContent())
                        .boardTagList(tagService.toTagDTOList(comment.getBoard()))
                        .build())
                .collect(Collectors.toList());
    }
    private List<Board> boardListFindByMemberId(Long memberId) {
        List<Board> boardList =  boardRepository.findByMemberMemberId(memberId);
        if(boardList.isEmpty()) {
            throw new IllegalArgumentException("Board doens't exsist");
        }
        return boardList;
    }

    private List<Comment> commentListFindByMemberId(Long memberId) {
        List<Comment> commentList = commentRepository.findByMemberMemberId(memberId);
        if(commentList.isEmpty()) {
            throw new IllegalArgumentException("Comment doesn't exsist");
        }
        return commentList;
    }
}
