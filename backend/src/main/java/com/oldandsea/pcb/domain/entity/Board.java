package com.oldandsea.pcb.domain.entity;

import javax.persistence.*;

import com.oldandsea.pcb.domain.dto.BoardDetailDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;




/*Entity에서 setter를 사용하는것을 최대한 지양해라.
 setter를 사용하는경우 그것을 생성하는경우인지, 변경하는경우인지 구별이 힘들뿐더러
 setter는 public으로 작성되기 때문에 의도치 않게 값을 변경하는 경우가 생길수 도 있다.
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity

@EntityListeners(AuditingEntityListener.class)
@Table(name = "board")
public class Board {
//    ZoneId seoulId = ZoneId.of("Asia/Seoul");
//    ZonedDateTime seoulTime = ZonedDateTime.now().withZoneSameInstant(seoulId);

    @Id
    @Column(name ="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardId;
    @Column(nullable = false, name = "title")
    private String title;
    @Column(nullable = false, name ="content")
    private String content;
    @Column(nullable = false, name ="viewCount")
    private Long viewCount =0L;
    @Column(nullable = false, name ="createdAt")
    @CreatedDate
//    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;


    @Column(nullable = false, name ="modifiedAt")
    @LastModifiedDate
//    @Temporal(TemporalType.DATE)
    private LocalDateTime modifiedAt;

    @Column(nullable = false, name = "tag")
    private String tag;


    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    //게시글 생성
    @Builder
    public Board(String title, String content, Member member, String tag) {
//        this.boardId = boardId;
        this.title = title;
        this.content = content;
        this.createdAt = LocalDateTime.now();
        this.modifiedAt= LocalDateTime.now();
        this.member = member;
        this.tag = tag;
        //viewCount 는 default 값이 있다.
    }

    //게시글 수정
    public void updateBoard(String title, String content) {
        this.title = title;
        this.content = content;
    }

    @Builder // Tag 추가
    private Board(String tag) {
        this.tag = tag;
    }
}
