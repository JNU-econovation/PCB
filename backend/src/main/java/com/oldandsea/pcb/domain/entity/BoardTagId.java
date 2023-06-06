package com.oldandsea.pcb.domain.entity;

import javax.persistence.*;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class BoardTagId {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long boardTagId;
    @Column(nullable = false, name = "board_id")
    private Long boardId;
    @Column(nullable = false, name = "tag_id")
    private Long tagId;

    @Override
    /*동등성 검사 : 두객체가 같은지 비교하는 equals()메서드를 재정의
    Object타입의 o를 BoardtagId타입으로 캐스팅을 하고 that이라는 참조변수가 BoardTagId타입으로 캐스팅한 객체를 가르키는 o와 같은 객체를 가르키게 한다
    this(지금 현재 객체)의 boardId와 that의 boardId가 같은지, this(지금 현재 객체)의 tagId와 that의 tagId가 같은지 확인한다.
     */
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof BoardTagId))
            return false;
        BoardTagId bti = (BoardTagId) o;
            return bti.boardId == boardTagId && bti.tagId == tagId;
    }

    @Override
    public int hashCode() {
        int c = 31;
        int result = Long.hashCode(boardId);
        result = c * result + Long.hashCode(tagId);
        return result;
    }
    @Builder
    public BoardTagId(Long boardId, Long tagId) {
        this.boardId = boardId;
        this.tagId = tagId;
    }
    public void updateBoardTagId(Long boardId, Long tagId) {
        this.boardId = boardId;
        this.tagId = tagId;
    }
}
