package com.oldandsea.pcb.domain.repository;

import com.oldandsea.pcb.domain.entity.BoardTag;
import com.oldandsea.pcb.domain.entity.BoardTagId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardTagRepository extends JpaRepository<BoardTag, BoardTagId> {
}

