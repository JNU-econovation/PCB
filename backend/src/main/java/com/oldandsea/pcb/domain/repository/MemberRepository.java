package com.oldandsea.pcb.domain.repository;
import com.oldandsea.pcb.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByUid(String uid);
    @Query("select m from Member m left join fetch m.boards where m.memberId = :memberId")
    Optional<Member> findByMemberIdFetch(@Param("memberId") Long memberId);
    Optional<Member> findByMemberId(Long memberId);

    Optional<Member> findByNickname(String nickname);
}
