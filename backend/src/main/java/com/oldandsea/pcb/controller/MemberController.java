package com.oldandsea.pcb.controller;



import com.oldandsea.pcb.config.apiresponse.ApiResult;
import com.oldandsea.pcb.config.apiresponse.ApiUtils;
import com.oldandsea.pcb.domain.dto.request.*;

import com.oldandsea.pcb.domain.dto.response.MemberLoginResponseDto;
import com.oldandsea.pcb.domain.dto.response.MemberResponseDto;
import com.oldandsea.pcb.service.MemberService;
import com.oldandsea.pcb.service.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;


@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final SessionService sessionService;

    @PostMapping("/uid-check")
    public ApiResult<?> uidCheck(@RequestBody @Valid MemberUidCheckRequestDto memberUidCheckRequestDto) {
        if (memberService.uidCheck(memberUidCheckRequestDto))
            return ApiUtils.error("Duplicate uid", HttpStatus.BAD_REQUEST);
        else
            return ApiUtils.success("Can use uid");
    }

    @PostMapping("/nickname-check")
    public ApiResult<?> nickNameCheck(@RequestBody @Valid MemberNickNameCheckRequestDto memberNickNameCheckRequestDto) {
        if (memberService.nickNameCheck(memberNickNameCheckRequestDto))
            return ApiUtils.error("Duplicate nickname", HttpStatus.BAD_REQUEST);
        else
            return ApiUtils.success("Can use nickname");
    }

    @PostMapping("/create")
    public ApiResult<?> insertMember(@RequestBody @Valid MemberCreateRequestDto memberCreateRequestDto) {
        memberService.createMember(memberCreateRequestDto);
        return ApiUtils.success("Create member success");

    }

    @PostMapping("/login")
    public ResponseEntity<?> login(HttpServletResponse response,@RequestBody @Valid MemberLoginRequestDto memberLoginRequestDto, HttpServletRequest request) {
        MemberResponseDto loginResult = memberService.login(memberLoginRequestDto);
        if (loginResult != null) {
            HttpSession session = request.getSession(true);
            session.setMaxInactiveInterval(1800);
            MemberLoginResponseDto memberLoginResponseDto = sessionService.createSession(loginResult, session.getId());
            return ResponseEntity.ok(ApiUtils.success(memberLoginResponseDto));
        } else {
            return ResponseEntity.badRequest().body(ApiUtils.error("Please check uid or pwd", HttpStatus.BAD_REQUEST));
        }
    }


    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        sessionService.deleteSession(session.getId());
        return ResponseEntity.ok(ApiUtils.success("Logout success"));
    }

    @PutMapping("/update/{memberId}")
    public ResponseEntity<?> update(@PathVariable("memberId") Long memberId, @RequestBody MemberUpdateRequestDto memberUpdateRequestDto) {
        memberService.update(memberId, memberUpdateRequestDto);
        return ResponseEntity.ok(ApiUtils.success("Member update success"));
    }

    @DeleteMapping("/delete/{memberId}")
    public ResponseEntity<?> delete(@PathVariable("memberId") Long memberId) {
        memberService.delete(memberId);
        return ResponseEntity.ok((ApiUtils.success("Memeber delete success")));
    }

}




