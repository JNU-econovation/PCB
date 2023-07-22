package com.oldandsea.pcb.domain.repository.boardrepository;
import com.oldandsea.pcb.domain.entity.Board;
import com.oldandsea.pcb.domain.entity.BoardTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface BoardRepository extends JpaRepository<Board, Long>, BoardRepositoryCustom {
    @Query("select b FROM Board b join fetch b.boardTagList bt where bt.board.boardId = :boardId")
    Optional<Board> findByBoardTagFetch(@Param("boardId") Long boardId);

    List<Board> findByMemberMemberId(Long memberId);
}
