package com.oldandsea.pcb.domain.entity;

import javax.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "board_tags")
@DynamicUpdate
public class BoardTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long boardTagId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false ,name = "board_id")
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(nullable = false, name = "tag_id")
    private Tag tag;


    // 게시글 - 태그 관계 생성
    @Builder
    public BoardTag(Board board, Tag tag) {
        this.board = board;
        this.tag = tag;
    }
    //게시글 - 태그 관계 수정
    public void updateBoardTag(Tag tag) {
        this.tag = tag;
    }
}
