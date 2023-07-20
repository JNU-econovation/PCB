package com.oldandsea.pcb.service;

import com.oldandsea.pcb.domain.dto.request.CommentCreateRequestDTO;
import com.oldandsea.pcb.domain.dto.response.CommentCreateResponseDTO;
import com.oldandsea.pcb.domain.entity.Board;
import com.oldandsea.pcb.domain.entity.Comment;
import com.oldandsea.pcb.domain.entity.Member;
import com.oldandsea.pcb.domain.repository.CommentRepository;
import com.oldandsea.pcb.domain.repository.MemberRepository;
import com.oldandsea.pcb.domain.repository.boardrepository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.time.ZoneOffset;


@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

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
            return entityToDTO(comment);
        } catch (DataAccessException e) {
            throw new IllegalArgumentException("After of comment must not duplicate");
        }
    }
    private Comment dtoToEntity(Board board, Member member, CommentCreateRequestDTO createRequestDTO) {
        return Comment.builder()
                .board(board)
                .after(createRequestDTO.getAfter())
                .position(createRequestDTO.getPosition())
                .content(createRequestDTO.getContent())
                .member(member)
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
                .build();
    }
}
