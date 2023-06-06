package com.oldandsea.pcb.domain.dto;

import com.oldandsea.pcb.domain.entity.Board;
import lombok.*;

import java.time.ZoneOffset;


@Getter
@NoArgsConstructor
public class BoardDetailDto {
    private  Long boardId;
    private String title;
    private String content;
    private Long attendance;
    private Long createdAt;
    private String tag;
    private Long memberId;
//   @Builder
//    public BoardDetailDto(Board board) {
//        this.boardId = board.getBoardId();
//        this.title = board.getTitle();
//        this.content = board.getContent();
//        this.attendance = board.getViewCount();
//        this.createdAt = board.getCreatedAt().toEpochSecond(ZoneOffset.UTC);
//        this.tag = board.getTag();
//        this.memberId = board.getMember().getMemberId();
//    }

    @Builder
    public BoardDetailDto(BardDetailQuery query) {
        this.boardId = query.getBoardId();
        this.title = title;
        this.content = content;
        this.attendance = attendance;
        this.createdAt = createdAt;
        this.tag = tag;
        this.memberId = memberId;
    }

    public static void main(String[] args) {
        Board board = new Board();
        BoardDetailDto.builder().board(board).build();

        BoardDetailDto boardDetailDto = new BoardDetailDto(board);

        BoardDetailDto.builder().boardId(board.getBoardId()).tag(boardDetailDto.getTag())

    }
    @Data
    static class BardDetailQuery() {
        Long boardId:

        String title:

        String content:

        Long attendance:

        Long createdAt:

        String tag;:
        Long memberId;
    }
}
