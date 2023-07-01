//package com.oldandsea.pcb.repository;
//
//import com.oldandsea.pcb.domain.entity.Member;
//
//import com.oldandsea.pcb.domain.repository.MemberRepository;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.Optional;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@SpringBootTest
//public class MemberRepositoryTest {
//
//    @Autowired
//    private MemberRepository memberRepository;
//
//    @Test
//    public void createAndFindMemberTest() {
//        // given
//        String identification = "testUser3";
//        String pwd = "testPwd2";
//        String nickname = "testNick3";
//
//        memberRepository.save(Member.builder()
//                .identification(identification)
//                .pwd(pwd)
//                .nickname(nickname)
//                .build());
//
//        // when
//        Optional<Member> memberOptional = memberRepository.findByNickname(nickname);
//
//        // then
//        assertThat(memberOptional.isPresent()).isTrue();
//        memberOptional.ifPresent(member -> {
//            assertThat(member.getIdentification()).isEqualTo(identification);
//            assertThat(member.getPwd()).isEqualTo(pwd);
//            assertThat(member.getNickname()).isEqualTo(nickname);
//        });
//    }
//}