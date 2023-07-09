package com.oldandsea.pcb.controller;

import com.oldandsea.pcb.config.SessionConst;
import com.oldandsea.pcb.config.apiresponse.ApiResult;
import com.oldandsea.pcb.config.apiresponse.ApiUtils;
import com.oldandsea.pcb.domain.dto.request.BoardCreateRequestDto;
import com.oldandsea.pcb.service.BoardCreateService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    public final BoardCreateService boardCreateService;
    @PostMapping("/create")
    public ApiResult<?> createBoard(@RequestBody @Valid BoardCreateRequestDto boardCreateDto, HttpSession session) {

        Long memberId = (Long) session.getAttribute(SessionConst.LOGIN_MEMBER);
        if (memberId == null) {
            return ApiUtils.error("Please login first", HttpStatus.BAD_REQUEST);
        }
        return ApiUtils.success(boardCreateService.createBoard(boardCreateDto, memberId));
    }
}
