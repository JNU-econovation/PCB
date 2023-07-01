package com.oldandsea.pcb.domain.repository;
import com.oldandsea.pcb.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByNickname(String nickname);
    //Member findByIdentification(String identification);
    Optional<Member> findByUid(String identification);
    Optional<Member> findByMemberId(Long memberId);
}
