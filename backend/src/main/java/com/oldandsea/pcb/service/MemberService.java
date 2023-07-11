package com.oldandsea.pcb.service;


import com.oldandsea.pcb.domain.dto.request.BoardUpdateRequestDto;
import com.oldandsea.pcb.domain.dto.request.MemberCreateRequestDto;

import com.oldandsea.pcb.domain.dto.request.MemberLoginOutRequestDto;
import com.oldandsea.pcb.domain.dto.request.MemberUidCheckRequestDto;
import com.oldandsea.pcb.domain.dto.response.MemberResponseDto;
import com.oldandsea.pcb.domain.entity.Member;
import com.oldandsea.pcb.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class MemberService {
   private final MemberRepository memberRepository;

    @Transactional
    public boolean uidCheck(MemberUidCheckRequestDto memberUidCheckRequestDto) {
        Optional<Member> member = memberRepository.findByUid(memberUidCheckRequestDto.getUid());
       return member.isPresent();
    }
    @Transactional
    public String createMember(MemberCreateRequestDto memberCreateRequestDto) {

        Member memeber = memberCreateRequestDto.toEntity();
        Optional<Member> findMember = memberRepository.findByUid(memeber.getUid());
        if (findMember.isPresent()) {
            throw new IllegalArgumentException("Duplicate uid");
        }
        else {
            memberRepository.save(memeber);
            return memeber.getUid();
        }
    }

    @Transactional
    public MemberResponseDto login(MemberLoginOutRequestDto memberLoginOutRequestDto) {
        Optional<Member> byMemberUid = memberRepository.findByUid(memberLoginOutRequestDto.getUid());
        if (byMemberUid.isEmpty()) {
            throw new NullPointerException("Please check uid or pwd");
        } else {
            Member member = byMemberUid.get();

            if (member.getPwd().equals(memberLoginOutRequestDto.getPwd())) {
                return MemberResponseDto.builder()
                        .memberId(member.getMemberId())
                        .uid(member.getUid())
                        .pwd(member.getPwd())
                        .nickname(member.getNickname())
                        .build();

            } else
                return null;

        }
    }
    @Transactional
    public void update(Long memberId, BoardUpdateRequestDto boardUpdateRequestDto) {
        Optional<Member> member = memberRepository.findByMemberId(memberId);
        if(member.isEmpty()) {
            throw new NullPointerException("Member doesn't exist");
        }
        Member member1 = member.get();
        String pwd = boardUpdateRequestDto.getPwd();
        String nickname = boardUpdateRequestDto.getNickname();

        if (!boardUpdateRequestDto.getPwd().isEmpty() && !boardUpdateRequestDto.getNickname().isEmpty()) {
            member1.updateMember(pwd, nickname);
        } else if (!boardUpdateRequestDto.getPwd().isEmpty()) {
            member1.updateMember(pwd, member1.getNickname());
        } else if (!boardUpdateRequestDto.getNickname().isEmpty()) {
            member1.updateMember(member1.getPwd(), nickname);
        }
    }

    @Transactional
    public void delete(Long memberId) {
        Optional<Member> member = memberRepository.findByMemberId1(memberId);
        if(member.isEmpty()) {
            throw new NullPointerException("Member doesn't exsist");
        }
        memberRepository.delete(member.get());
    }
}


