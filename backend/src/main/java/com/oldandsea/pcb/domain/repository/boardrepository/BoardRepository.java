package com.oldandsea.pcb.domain.repository.boardrepository;
import com.oldandsea.pcb.domain.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;




public interface BoardRepository extends JpaRepository<Board, Long>, BoardRepositoryCustom {
}
