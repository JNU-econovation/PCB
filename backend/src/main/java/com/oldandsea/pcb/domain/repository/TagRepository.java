package com.oldandsea.pcb.domain.repository;
import com.oldandsea.pcb.domain.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Long> {
    Optional<Tag> findByName (String name);
    @Query("SELECT bt.tag FROM BoardTag bt WHERE bt.board.boardId = :boardId")
    List<Tag> findByBoardTagFetch(@Param("boardId") Long boardId);

}
