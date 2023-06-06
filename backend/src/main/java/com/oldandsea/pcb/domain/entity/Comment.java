package com.oldandsea.pcb.domain.entity;

import javax.persistence.*;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;



@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "comment")
public class Comment {


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @Column(nullable = false, name = "content")
    private String content;

    @CreatedDate
    //@Temporal(value = TemporalTyp) // @Temporal은 날짜관련 에노테이션
    private LocalDateTime createdAt;

    @LastModifiedDate
//    @Temporal(value = TemporalType.DATE)
    private LocalDateTime modifiedAt;

    @Column(name = "parent_id", nullable = false)
    private Long parentId; // 0이면 댓글, 1이면 대댓글
    @Column(name = "sequence", nullable = false)
    private Long sequence;

    /*
    @Column(nullable = true, name = TABLE_NAME + "_replycontent")
    private String replyContent;
    */

    /*
    @CreatedDate
    @Temporal(value = TemporalType.DATE)
    private Date replyDate;
    */
    @Column(name = "like_count",nullable = false,columnDefinition = "bigint default 0")
    private Long like;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Board board;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Member member;

    @Builder
    public Comment(String content,  Long parentId, Board board, Member member, Long sequence) {
        this.content = content;
        this.createdAt = LocalDateTime.now();
        this.modifiedAt= LocalDateTime.now();
        this.parentId = parentId;
        this.board = board;
        this.member = member;
        this.sequence = sequence;
    }

    public void updateComment(String content) {
        this.content = content;
        this.modifiedAt = LocalDateTime.now();
    }

}
