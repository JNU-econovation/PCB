package com.oldandsea.pcb.domain.entity;

import javax.persistence.*;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "board")
public class Board {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardId;

    @Column(nullable = false, name = "title")
    private String title;

    @Column(nullable = false, name = "content")
    private String content;

    @Column(nullable = false, name = "view_count")
    private Long viewCount = 0L;

    @Column(nullable = false, name = "created_at")
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(nullable = false, name = "modified_at")
    @LastModifiedDate
    private LocalDateTime modifiedAt;

    @OneToMany(fetch = FetchType.LAZY)
    private List<BoardTag> boardTagList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Builder
    public Board(String title, String content, Member member, List<BoardTag> boardTagList) {
        this.title = title;
        this.content = content;
        this.createdAt = LocalDateTime.now();
        this.modifiedAt = LocalDateTime.now();
        this.member = member;
        this.boardTagList = boardTagList;
    }


    public void updateBoard(String title, String content, List<BoardTag> boardTagList) {
        this.title = title;
        this.content = content;
        this.boardTagList = boardTagList;
    }
}
