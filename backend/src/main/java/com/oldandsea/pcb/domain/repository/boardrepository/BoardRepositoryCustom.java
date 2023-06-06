package com.oldandsea.pcb.domain.repository.boardrepository;

import com.oldandsea.pcb.domain.entity.Board;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepositoryCustom {
    Slice<Board> searchByTagAndSlice(Long lastBoardId, String tag, Pageable pageable);
}
