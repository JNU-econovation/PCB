package com.oldandsea.pcb.service;

import com.oldandsea.pcb.domain.dto.request.CommentCreateRequestDTO;
import com.oldandsea.pcb.domain.dto.response.CommentCreateResponseDTO;
import com.oldandsea.pcb.domain.dto.response.CommentResponseDTO;
import com.oldandsea.pcb.domain.entity.Board;
import com.oldandsea.pcb.domain.entity.Comment;
import com.oldandsea.pcb.domain.entity.Member;
import com.oldandsea.pcb.domain.repository.commentrepository.CommentRepository;
import com.oldandsea.pcb.domain.repository.commentrepository.CommentRepositoryCustom;
import com.oldandsea.pcb.domain.repository.MemberRepository;
import com.oldandsea.pcb.domain.repository.boardrepository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZoneOffset;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {
    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    private final CommentRepositoryCustom commentRepositoryCustom;

    @Transactional
    public CommentCreateResponseDTO createComment(CommentCreateRequestDTO createRequestDTO, Long memberId) {
        Board board = boardRepository.findById(createRequestDTO.getBoardId()).orElseThrow(
                () -> new IllegalArgumentException("Board doesn't exsist")
        );
        Member member = memberRepository.findByMemberId(memberId).orElseThrow(
                () -> new IllegalArgumentException("Member doesn't exsist")
        );
        try {
            Comment comment = dtoToEntity(board,member,createRequestDTO);
            commentRepository.save(comment);
            updateAfter(comment);
            return entityToDTO(comment);
        } catch (DataAccessException e) {
            throw new IllegalArgumentException("After of comment must not duplicate");
        }
    }
    private Comment dtoToEntity(Board board, Member member, CommentCreateRequestDTO createRequestDTO) {
        return Comment.builder()
                .board(board)
                .after(-1L)
                .position(createRequestDTO.getPosition())
                .content(createRequestDTO.getContent())
                .member(member)
                .color(createRequestDTO.getColor())
                .build();
    }
    private CommentCreateResponseDTO entityToDTO(Comment comment) {
       return CommentCreateResponseDTO.builder()
                .commentId(comment.getCommentId())
                .nickmame(comment.getMember().getNickname())
                .after(comment.getAfter())
                .position(comment.getPosition())
                .content(comment.getContent())
                .createdAt(comment.getCreatedAt().toEpochSecond(ZoneOffset.UTC))
                .color(comment.getColor())
                .build();
    }

    public List<Comment> commentListfindByBoardId(Long boardId) {
        List<Comment> commentList =  commentRepository.findByBoardBoardId(boardId);
        if(commentList.isEmpty()) {
            throw new IllegalArgumentException("comment doesn't exsist");
        }
        return commentList;
    }

    public List<CommentResponseDTO> commentDTO (Board board) {
        Long boardId = board.getBoardId();
        List<Comment> commentList = commentListfindByBoardId(boardId);
        return commentList.stream()
                .map(comment -> CommentResponseDTO.builder()
                        .color(comment.getColor())
                        .nickname(comment.getMember().getNickname())
                        .commentId(comment.getCommentId())
                        .position(comment.getPosition())
                        .after(comment.getAfter())
                        .content(comment.getContent())
                        .build())
                .collect(Collectors.toList());
    }
    @Transactional
    public void updateAfter(Comment comment) {
        commentRepositoryCustom.updateAfter(comment);
    }

}
