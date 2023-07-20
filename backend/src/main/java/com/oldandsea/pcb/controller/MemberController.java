package com.oldandsea.pcb.controller;



import com.oldandsea.pcb.config.apiresponse.ApiResult;
import com.oldandsea.pcb.config.apiresponse.ApiUtils;
import com.oldandsea.pcb.domain.dto.request.*;

import com.oldandsea.pcb.domain.dto.response.MemberLoginResponseDTO;
import com.oldandsea.pcb.domain.dto.response.MemberResponseDTO;
import com.oldandsea.pcb.service.MemberService;
import com.oldandsea.pcb.service.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.servlet.http.Cookie;
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
    private final EntityManager em;

    @PostMapping("/uid-check")
    public ApiResult<?> uidCheck(@RequestBody @Valid MemberUidCheckRequestDTO memberUidCheckRequestDto) {
        if (memberService.uidCheck(memberUidCheckRequestDto))
            return ApiUtils.error("Duplicate uid", HttpStatus.BAD_REQUEST);
        else
            return ApiUtils.success("Can use uid");
    }

    @PostMapping("/nickname-check")
    public ApiResult<?> nickNameCheck(@RequestBody @Valid MemberNickNameCheckRequestDTO memberNickNameCheckRequestDto) {
        if (memberService.nickNameCheck(memberNickNameCheckRequestDto))
            return ApiUtils.error("Duplicate nickname", HttpStatus.BAD_REQUEST);
        else
            return ApiUtils.success("Can use nickname");
    }

    @PostMapping("/create")
    public ApiResult<?> insertMember(@RequestBody @Valid MemberCreateRequestDTO memberCreateRequestDto) {
        memberService.createMember(memberCreateRequestDto);
        return ApiUtils.success("Create member success");

    }

    @PostMapping("/login")
    public ResponseEntity<?> login(HttpServletResponse response, @RequestBody @Valid MemberLoginRequestDTO memberLoginRequestDto, HttpServletRequest request) throws DataAccessException {
        MemberResponseDTO loginResult = memberService.login(memberLoginRequestDto);
        if (loginResult != null) {
            HttpSession session = request.getSession(true);
            String sessionId = session.getId();
            session.setMaxInactiveInterval(1);
            try {
                MemberLoginResponseDTO memberLoginResponseDto = sessionService.createSession(loginResult, sessionId);
                response.setHeader("Set-Cookie", "PCBSESSIONID=" + sessionId + "; Path=/; HttpOnly; SameSite=None; Secure");
                return ResponseEntity.ok(ApiUtils.success(memberLoginResponseDto));
            }
            catch (DataAccessException e) {
                return ResponseEntity.badRequest().body(ApiUtils.error("Multiple sessions exist in one member", HttpStatus.BAD_REQUEST));
            }

        } else {
            return ResponseEntity.badRequest().body(ApiUtils.error("Multiple sessions exist in one member", HttpStatus.BAD_REQUEST));
        }
    }


    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        String sessionIdFromCookie = null;
        for (Cookie cookie : cookies) {
            if ("PCBSESSIONID".equals(cookie.getName())) {
                sessionIdFromCookie = cookie.getValue();
                break;
            }
        }
        sessionService.deleteSession(sessionIdFromCookie);
        return ResponseEntity.ok(ApiUtils.success("Logout success"));
    }

    @PutMapping("/pwd/{memberId}")
    public ResponseEntity<?> updatePwd(@PathVariable("memberId") Long memberId, @RequestBody MemberPwdUpdateRequestDTO memberPwdUpdateRequestDto) {
        memberService.updatePwd(memberId,memberPwdUpdateRequestDto);
        return ResponseEntity.ok(ApiUtils.success("Pwd update success"));
    }


    @PutMapping("/nickname/{memberId}")
    public ResponseEntity<?> updateNickname(@PathVariable("memberId") Long memberId, @RequestBody MemberNickUpdateRequestDTO nickUpdateRequestDto) {
        memberService.updateNickname(memberId,nickUpdateRequestDto);
        return ResponseEntity.ok(ApiUtils.success("Nickname update success"));
    }
    @DeleteMapping("/delete/{memberId}")
    public ResponseEntity<?> delete(@PathVariable("memberId") Long memberId) {
        memberService.delete(memberId);
        return ResponseEntity.ok((ApiUtils.success("Memeber delete success")));
    }

}




