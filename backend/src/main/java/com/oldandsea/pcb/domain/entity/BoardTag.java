package com.oldandsea.pcb.domain.entity;

import javax.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "boardTag")
public class BoardTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long boardTagId;

    @ManyToOne
    @JoinColumn(nullable = false ,name = "board_id")
    private Board board;

    @ManyToOne
    @JoinColumn(nullable = false, name = "tag_id")
    private Tag tag;

     // 게시글 - 태그 관계 생성
    @Builder
    public BoardTag(Board board, Tag tag) {
        this.board = board;
        this.tag = tag;
    }
    //게시글 - 태그 관계 수정
    public void updateBoardTag(Board board, Tag tag) {
        this.board = board;
        this.tag = tag;
    }
}
