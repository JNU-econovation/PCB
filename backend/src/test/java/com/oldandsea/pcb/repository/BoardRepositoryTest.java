package com.oldandsea.pcb.repository;

import com.oldandsea.pcb.domain.repository.boardrepository.BoardRepository;
import com.oldandsea.pcb.domain.repository.MemberRepository;
import org.junit.jupiter.api.Test;

import com.oldandsea.pcb.domain.entity.Board;
import com.oldandsea.pcb.domain.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import static org.assertj.core.api.Assertions.assertThat;
//@DataJpaTest
@SpringBootTest
public class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void creatdeAndFindBoarTest() {
        // given
        String identification = "testId1122";
        String pwd = "testPwd121";
        String nickname = "testUser3122";

        Member member = Member.builder()
                .identification(identification)
                .pwd(pwd)
                .nickname(nickname)
                .build();
        Member savedMember = memberRepository.save(member);
//        Member member = memberRepository.save(Member.builder()
//                .identification(identification)
//                .pwd(pwd)
//                .nickname(nickname)
//                .build());

        String title = "Test Title13";
        String content = "Test Content23";
        String tag = "Test13";
        Board board1 = boardRepository.save(Board.builder()
                .title(title)
                .content(content)
                .member(savedMember)
                .tag(tag)
                .build());
        System.out.println("board.getBoardId() = " + board1.getBoardId());
        System.out.println("board.getCreatedAt() = " + board1.getCreatedAt());
        System.out.println("board.getModifiedAt() = " + board1.getModifiedAt());
        System.out.println("board.getTitle() = " + board1.getTitle());
        System.out.println("board.getContent() = " + board1.getContent());
        System.out.println("board.getMember() = " + board1.getMember());
        System.out.println("board.getTag() = " + board1.getTag());
        System.out.println("board.getAttendance() = " + board1.getViewCount());

        // when
        



    }
}



