package com.oldandsea.pcb.controller;

import com.oldandsea.pcb.config.Login;
import com.oldandsea.pcb.config.apiresponse.ApiResult;
import com.oldandsea.pcb.config.apiresponse.ApiUtils;
import com.oldandsea.pcb.domain.dto.request.BoardCreateRequestDto;
import com.oldandsea.pcb.domain.dto.request.BoardUpdateRequestDto;
import com.oldandsea.pcb.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    public final BoardService boardService;
    @PostMapping("/create")
    public ApiResult<?> createBoard(@RequestBody @Valid BoardCreateRequestDto boardCreateDto, @Login Long memberId) {

        return ApiUtils.success(boardService.createBoard(boardCreateDto, memberId));
    }
    @PutMapping("/update/{boardId}")
    public ApiResult<?> updateBoard(@RequestBody @Valid BoardUpdateRequestDto boardUpdateRequestDto, @PathVariable Long boardId) {
        return ApiUtils.success(boardService.updateBoard(boardUpdateRequestDto, boardId));
    }

    @DeleteMapping("/delete/{boardId}")
    public ApiResult<?> deleteBoard(@PathVariable Long boardId) {
        boardService.deleteBoard(boardId);
        return ApiUtils.success("Success delete board");
    }
}
