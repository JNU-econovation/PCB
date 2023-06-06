package com.oldandsea.pcb.domain.repository;
import com.oldandsea.pcb.domain.entity.Member;
import com.oldandsea.pcb.domain.entity.LikeCount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeCountRepository extends JpaRepository<LikeCount, Long> {
    Optional<LikeCount> findByMember(Member member);
}
