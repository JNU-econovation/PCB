package com.oldandsea.pcb.domain.repository;
import com.oldandsea.pcb.domain.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Long> {
    Optional<Tag> findByName (String name);
}
