package com.oldandsea.pcb.service;

import com.oldandsea.pcb.domain.dto.layer.CommentUpdateContentList;
import com.oldandsea.pcb.domain.dto.layer.CommentUpdatePositionList;
import com.oldandsea.pcb.domain.dto.request.CommentCreateRequestDTO;
import com.oldandsea.pcb.domain.dto.response.CommentCreateResponseDTO;
import com.oldandsea.pcb.domain.dto.response.CommentResponseDTO;
import com.oldandsea.pcb.domain.dto.response.CommentUpdateContentResponseDTO;
import com.oldandsea.pcb.domain.dto.response.CommentUpdatePositionResponseDTO;
import com.oldandsea.pcb.domain.entity.Board;
import com.oldandsea.pcb.domain.entity.Comment;
import com.oldandsea.pcb.domain.entity.Member;
import com.oldandsea.pcb.domain.repository.MemberRepository;
import com.oldandsea.pcb.domain.repository.boardrepository.BoardRepository;
import com.oldandsea.pcb.domain.repository.commentrepository.CommentRepository;
import com.oldandsea.pcb.domain.repository.commentrepository.CommentRepositoryCustom;
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
        return commentRepository.findByBoardBoardId(boardId);
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
    public CommentUpdatePositionResponseDTO updatePosition(List<CommentUpdatePositionList> requestDTO, Long boardId) {
        List<CommentUpdatePositionList> responseDTOList = new ArrayList<>();
        for (CommentUpdatePositionList request : requestDTO) {
            Long commentId = request.getCommentId();
            Long after = request.getAfter();
            Comment comment = findByCommentIdAndBoardId(commentId,boardId);
            if (after == -1 || checkAfter(after, comment.getBoard().getBoardId()) ) {
                comment.updatePosition(request.getAfter(), request.getPosition());
                if(Objects.equals(comment.getCommentId(), comment.getAfter())) {
                    throw new IllegalArgumentException("commentId랑 After랑 같을 수 없습니다");
                }
                responseDTOList.add(toUpdatePositionDTO(comment));
            } else throw new IllegalArgumentException("after에 해당하는 Id를 가진 comment가 같은 게시글에 있지 않습니다");
        }
        return CommentUpdatePositionResponseDTO.builder()
                .updatePositionList(responseDTOList)
                .build();
    }
    @Transactional
    public CommentUpdateContentResponseDTO updateContent(List<CommentUpdateContentList> requestDTO, Long memberId) {
        List<CommentUpdateContentList> responseDTOList = new ArrayList<>();
        for (CommentUpdateContentList request : requestDTO) {
            Long commentId = request.getCommentId();
            Comment comment = findByCommentIdAndMemberId(commentId, memberId);
            comment.updateColorAndContent(request.getColor(), request.getContent());
            responseDTOList.add(toUpdateContentDTO(comment));
            }
        return CommentUpdateContentResponseDTO.builder()
                .updateContentList(responseDTOList)
                .build();
    }

    private CommentUpdatePositionList toUpdatePositionDTO(Comment comment) {
        return CommentUpdatePositionList.builder()
                .commentId(comment.getCommentId())
                .after(comment.getAfter())
                .position(comment.getPosition())
                .build();
    }

    private CommentUpdateContentList toUpdateContentDTO(Comment comment) {
        return CommentUpdateContentList.builder()
                .commentId(comment.getCommentId())
                .color(comment.getColor())
                .content(comment.getContent())
                .build();
    }
    private boolean checkAfter(Long after, Long boardId) {
        Comment commentCheck = commentRepository.findById(after).orElseThrow(
                () -> new IllegalArgumentException("after에 해당하는 Id를 가진 comment가 존재하지 않습니다")
        );
        if(Objects.equals(commentCheck.getBoard().getBoardId(), boardId)) {
            return true;
        }else
            throw new IllegalArgumentException("after에 해당하는 Id를 가진 comment가 같은 게시글에 있지 않습니다");
    }
    private Comment findByCommentIdAndBoardId(Long commentId, Long boardId) {
            return commentRepository.findByCommentIdAndBoardBoardId(commentId, boardId).orElseThrow(
                    () -> new IllegalArgumentException("Comment doesn't exsist")
            );
        }

    @Transactional
    public CommentUpdatePositionResponseDTO deleteCommnet(List<CommentUpdatePositionList> requestDTO, Long memberId, Long commentId) {
        if(checkCommentCreator(commentId,memberId)) {
            commentRepository.deleteById(commentId);
            return updatePosition(requestDTO,memberId);
        }
        throw new IllegalArgumentException("댓글 작성자만 댓글을 삭제 할 수 있습니다");
    }

    public Boolean checkBoardCreator(Long boardId, Long memberId) {
        Long checkBoardCreatorId = boardRepository.findById(boardId).orElseThrow(
                () -> new IllegalArgumentException("boardId에 해당하는 board가 존재하지 않습니다(댓글 위치 수정)")
        ).getMember().getMemberId();
        return checkBoardCreatorId.equals(memberId);
    }

    private Boolean checkCommentCreator(Long commentId, Long memberId) {
        Long commentCreatorId = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("commentId에 해당하는 comment가 존재하지 않습니다(댓글 내용 수정)")
        ).getMember().getMemberId();
        return commentCreatorId.equals(memberId);
    }
    private Comment findByCommentIdAndMemberId(Long commnetId, Long memberId) {
        return commentRepository.findByCommentIdAndMemberMemberId(commnetId,memberId).orElseThrow(
                () -> new IllegalArgumentException("댓글 작성자만 댓글 내용을 수정할 수 있습니다")
        );
    }
}
