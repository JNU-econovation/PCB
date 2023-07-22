//package com.oldandsea.pcb.repository;
//
//import com.oldandsea.pcb.domain.entity.Board;
//import com.oldandsea.pcb.domain.entity.Comment;
//import com.oldandsea.pcb.domain.entity.Member;
//
//import com.oldandsea.pcb.domain.repository.boardrepository.BoardRepository;
//import com.oldandsea.pcb.domain.repository.commentrepository.CommentRepository;
//import com.oldandsea.pcb.domain.repository.MemberRepository;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@SpringBootTest
//public class CommentRepositoryTest {
//    @Autowired
//    private CommentRepository commentRepository;
//    @Autowired
//    private MemberRepository memberRepository;
//    @Autowired
//    private BoardRepository boardRepository;
//
//    @Test
//    public void createCommentTest() {
//        // given
//        String memberId = "testMember12";
//        String memberPwd = "testPwd12";
//        String memberNickname = "testUser12";
//
//        Member member = memberRepository.save(Member.builder()
//                .identification(memberId)
//                .pwd(memberPwd)
//                .nickname(memberNickname)
//                .build());
//
//        String boardTitle = "Test Board Title1";
//        String boardContent = "Test Board Content1";
//        String boardTag = "Test1";
//
//        Board board = boardRepository.save(Board.builder()
//                .title(boardTitle)
//                .content(boardContent)
//                .member(member)
//                .tag(boardTag)
//                .build());
//
//        String commentContent = "Test Comment Content1";
//
//        // when
//        Comment comment = commentRepository.save(Comment.builder()
//                .content(commentContent)
//                .member(member)
//                .board(board)
//                .parentId(-1L)
//                .build());
//
//        // then
//        assertThat(comment.getMember().getIdentification()).isEqualTo(memberId);
//        assertThat(comment.getBoard().getTitle()).isEqualTo(boardTitle);
//        assertThat(comment.getContent()).isEqualTo(commentContent);
//    }
//
//}