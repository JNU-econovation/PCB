package com.oldandsea.pcb.domain.repository.commentrepository;

import com.oldandsea.pcb.domain.entity.Comment;
import com.oldandsea.pcb.domain.entity.QComment;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;

@Repository
public class CommentRepositoryImpl implements CommentRepositoryCustom{
    private final JPAQueryFactory query;
    private final EntityManager em;

    public CommentRepositoryImpl(EntityManager em) {
        this.em = em;
        this.query = new JPAQueryFactory(em);
    }
    @Override
    public void updateAfter(Comment comment) {
        Long commentId = comment.getCommentId();
        Long boardId = comment.getBoard().getBoardId();

        Comment preComment = query
                .selectFrom(QComment.comment)
                .where(beforeCommnet(commentId,boardId))
                .orderBy(QComment.comment.commentId.desc())
                .fetchFirst();
        if(preComment != null) {
            preComment.updateAfter(commentId);
            em.persist(preComment);
        }
    }
    private BooleanExpression beforeCommnet(Long commentId, Long boardId) {
        if(commentId == null || boardId == null) {
            return null;
        }
        return QComment.comment.board.boardId.eq(boardId)
                .and(QComment.comment.commentId.lt(commentId));
    }
}
