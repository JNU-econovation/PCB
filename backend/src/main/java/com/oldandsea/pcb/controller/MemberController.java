package com.oldandsea.pcb.controller;



import com.oldandsea.pcb.config.apiresponse.ApiResult;
import com.oldandsea.pcb.config.apiresponse.ApiUtils;
import com.oldandsea.pcb.domain.dto.request.BoardUpdateRequestDto;
import com.oldandsea.pcb.domain.dto.request.MemberCreateRequestDto;

import com.oldandsea.pcb.domain.dto.request.MemberLoginOutRequestDto;
import com.oldandsea.pcb.domain.dto.request.MemberUidCheckRequestDto;
import com.oldandsea.pcb.domain.dto.response.MemberResponseDto;
import com.oldandsea.pcb.service.MemberService;
import com.oldandsea.pcb.config.SessionConst;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;


@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    @PostMapping("/uidcheck")
    public ApiResult<?> uidCheck(@RequestBody @Valid MemberUidCheckRequestDto memberUidCheckRequestDto) {
       if(memberService.uidCheck(memberUidCheckRequestDto))
          return ApiUtils.error("Duplicate uid",HttpStatus.BAD_REQUEST);
        else
           return ApiUtils.success("Can use uid");
    }
    @PostMapping("/create")
    public  ApiResult<?> insertMember(@RequestBody @Valid MemberCreateRequestDto memberCreateRequestDto) {
        memberService.createMember(memberCreateRequestDto);
        return ApiUtils.success("Create member success");

    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid MemberLoginOutRequestDto memberLoginOutRequestDto, HttpServletRequest request) {
        MemberResponseDto loginResult = memberService.login(memberLoginOutRequestDto);
        if(loginResult != null) {
            HttpSession session = request.getSession(true);
            session.setAttribute(SessionConst.LOGIN_MEMBER,loginResult.getMemberId());
            session.setMaxInactiveInterval(1800);
            return ResponseEntity.ok(ApiUtils.success("Login success"));
        } else {
            return ResponseEntity.badRequest().body(ApiUtils.error("Please check uid or pwd", HttpStatus.BAD_REQUEST));
        }
    }
    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
            session.invalidate();
            return ResponseEntity.ok(ApiUtils.success("Logout success"));
    }
    @PutMapping("/update/{memberId}")
    public ResponseEntity<?> update(@PathVariable("memberId") Long memberId, @RequestBody BoardUpdateRequestDto boardUpdateRequestDto){
        memberService.update(memberId, boardUpdateRequestDto);
        return ResponseEntity.ok(ApiUtils.success("Member update success"));
    }

    @DeleteMapping("/delete/{memberId}")
    public ResponseEntity<?> delete(@PathVariable("memberId") Long memberId)add {
        memberService.delete(memberId);
        return ResponseEntity.ok((ApiUtils.success("Memeber delete success")));
    }
}




