package com.oldandsea.pcb.domain.repository;
import com.oldandsea.pcb.domain.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
}
