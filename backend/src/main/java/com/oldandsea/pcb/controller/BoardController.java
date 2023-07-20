package com.oldandsea.pcb.controller;

import com.oldandsea.pcb.config.Login;
import com.oldandsea.pcb.config.apiresponse.ApiResult;
import com.oldandsea.pcb.config.apiresponse.ApiUtils;
import com.oldandsea.pcb.domain.dto.request.BoardCreateRequestDTO;
import com.oldandsea.pcb.domain.dto.request.BoardUpdateRequestDTO;
import com.oldandsea.pcb.domain.dto.response.BoardListResponseDTO;
import com.oldandsea.pcb.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    public final BoardService boardService;
    @PostMapping("/create")
    public ApiResult<?> createBoard(@RequestBody @Valid BoardCreateRequestDTO boardCreateDto, @Login Long memberId) {

        return ApiUtils.success(boardService.createBoard(boardCreateDto, memberId));
    }
    @PutMapping("/update/{boardId}")
    public ApiResult<?> updateBoard(@RequestBody @Valid BoardUpdateRequestDTO boardUpdateRequestDto, @PathVariable Long boardId) {
        return ApiUtils.success(boardService.updateBoard(boardUpdateRequestDto, boardId));
    }

    @DeleteMapping("/delete/{boardId}")
    public ApiResult<?> deleteBoard(@PathVariable Long boardId) {
        boardService.deleteBoard(boardId);
        return ApiUtils.success("Success delete board");
    }

    @GetMapping("/detail/{boardId}")
    public ApiResult<?> detailBoard(@PathVariable Long boardId) {
        BoardListResponseDTO detailResponseDto = boardService.detailBoard(boardId);
        return ApiUtils.success(detailResponseDto);
    }

    @GetMapping("/search")
    public Slice<BoardListResponseDTO> searchBoard(
            @RequestParam(value = "lastBoardId", required = false) Long lastBoardId,
            @RequestParam(value = "limit",defaultValue = "10") int limit,
            @RequestParam(value = "tag", required = true) String tag) {
        return boardService.searchBoard(tag, lastBoardId, limit);
    }
}
