package com.oldandsea.pcb.domain.repository.commentrepository;

import com.oldandsea.pcb.domain.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByBoardBoardId(Long boardId);
    List<Comment> findByMemberMemberId(Long memberId);
    @Query("select c FROM Comment c where c.commentId = :commentId AND c.member.memberId = :memberId")
    Optional<Comment> findByCommentIdAndMemberMemberId(@Param("commentId") Long commentId, @Param("memberId") Long memberId);
    @Query("select c FROM Comment c where c.commentId = :commentId AND c.position = :position")
    Optional<Comment> findByIdAndPosition(@Param("commentId") Long commentId, @Param("position") String position);

    @Query("select c FROM Comment c where c.commentId = :commentId AND c.board.boardId = :boardId")
    Optional<Comment> findByCommentIdAndBoardBoardId(@Param("commentId") Long commentId, @Param("boardId") Long boarId);
}
