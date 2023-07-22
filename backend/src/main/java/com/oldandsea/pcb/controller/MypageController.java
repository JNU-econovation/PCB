package com.oldandsea.pcb.controller;

import com.oldandsea.pcb.config.Login;
import com.oldandsea.pcb.config.apiresponse.ApiResult;
import com.oldandsea.pcb.config.apiresponse.ApiUtils;
import com.oldandsea.pcb.domain.dto.response.MyPageResponseDTO;
import com.oldandsea.pcb.service.MyPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mypage")
public class MypageController {
    private final MyPageService myPageService;

    @GetMapping()
    public ApiResult<?> mypage(@Login Long memberId) {
        MyPageResponseDTO responseDTO = myPageService.myPage(memberId);
        return ApiUtils.success(responseDTO);
    }

}
