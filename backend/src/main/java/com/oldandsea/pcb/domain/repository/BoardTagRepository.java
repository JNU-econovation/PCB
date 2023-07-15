package com.oldandsea.pcb.domain.repository;

import com.oldandsea.pcb.domain.entity.BoardTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;


public interface BoardTagRepository extends JpaRepository<BoardTag, Long> {
    @Query("select bt from BoardTag bt where bt.board.boardId = :boardId")
    Optional<List<BoardTag>>findByBoardId(@Param("boardId") Long boardId);
}

