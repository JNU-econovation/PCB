package com.oldandsea.pcb.service;

import com.oldandsea.pcb.domain.dto.request.CommentCreateRequestDTO;
import com.oldandsea.pcb.domain.dto.layer.CommentUpdatePositionList;
import com.oldandsea.pcb.domain.dto.response.CommentCreateResponseDTO;
import com.oldandsea.pcb.domain.dto.response.CommentResponseDTO;
import com.oldandsea.pcb.domain.dto.response.CommentUpdatePositionResponseDTO;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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

    @Transactional
    public CommentUpdatePositionResponseDTO updatePosition(List<CommentUpdatePositionList> requestDTO, Long memberId) {
        List<CommentUpdatePositionList> responseDTOList = new ArrayList<>();
        for (CommentUpdatePositionList requet : requestDTO) {
            Long commentId = requet.getCommentId();
            Comment comment = commentRepository.findByCommentIdAndMemberMemberId(commentId, memberId).orElseThrow(
                    () -> new IllegalArgumentException("Comment doesn't exsist")
            );

            if (checkAfter(requet.getAfter(), comment.getBoard().getBoardId())) {
                comment.updatePosition(requet.getAfter(), requet.getPosition());
                responseDTOList.add(toUpdatePositionDTO(comment));
            } else throw new IllegalArgumentException("after에 해당하는 Id를 가진 comment가 같은 게시글에 있지 않습니다");
        }
        return CommentUpdatePositionResponseDTO.builder()
                .updatePositionList(responseDTOList)
                .build();
    }

    private CommentUpdatePositionList toUpdatePositionDTO(Comment comment) {
        return CommentUpdatePositionList.builder()
                .commentId(comment.getCommentId())
                .after(comment.getAfter())
                .position(comment.getPosition())
                .build();
    }
    private boolean checkAfter(Long after, Long boardId) {
        Comment commentCheck = commentRepository.findById(after).orElseThrow(
                () -> new IllegalArgumentException("after에 해당하는 Id를 가진 comment가 없습니다")
        );
        if(Objects.equals(commentCheck.getBoard().getBoardId(), boardId)) {
            return true;
        }else
            throw new IllegalArgumentException("after에 해당하는 Id를 가진 comment가 같은 게시글에 있지 않습니다");
    }
}
