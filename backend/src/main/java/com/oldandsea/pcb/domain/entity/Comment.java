package com.oldandsea.pcb.domain.entity;

import javax.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import java.time.LocalDateTime;
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "comments")
@DynamicUpdate
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

    @Column(name = "after", nullable = false)
    private Long after;

    @Column(name = "posititon", nullable = false)
    private String position;

    @Column(name = "color", columnDefinition = "varchar(100) default 'white'")
    private String color;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id", nullable = false)
    private Board board;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id",nullable = false)
    private Member member;

    @Builder
    public Comment(String content, Board board, Member member, Long after, String position, String color) {
        this.content = content;
        this.createdAt = LocalDateTime.now();
        this.modifiedAt= LocalDateTime.now();
        this.board = board;
        this.member = member;
        this.after = after;
        this.position = position;
        this.color = color;
    }

    public void updatePosition(Long after, String position) {
        this.after = after;
        this.position = position;
    }

    public void updateColorAndContent(String color, String content) {
        this.color = color;
        this.content = content;
    }

    public void updateAfter(Long after) {
        this.after = after;
    }

}
