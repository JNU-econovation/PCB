package com.oldandsea.pcb.controller;


import com.oldandsea.pcb.config.apiresponse.ApiResult;
import com.oldandsea.pcb.config.apiresponse.ApiUtils;
import com.oldandsea.pcb.domain.dto.response.BoardListResponseDTO;
import com.oldandsea.pcb.service.MainPageListService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MainApiController {
    private final MainPageListService mainPageListService;

    /*
    무한 스크롤, Response의 last가 true 가 될 때 까지 요청 받음
     */
    @GetMapping("/api/main")
    public ApiResult<?> getBoardList(
            @RequestParam(value = "lastBoardId", required = false) Long lastBoardId,
            @RequestParam(value = "limit",defaultValue = "10") int size) {
        Slice<BoardListResponseDTO>  responseDTOSlice = mainPageListService.getAllBoards(lastBoardId, size);
        return ApiUtils.success(responseDTOSlice);
    }
}

