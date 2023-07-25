package com.oldandsea.pcb.controller;

import com.oldandsea.pcb.config.Login;
import com.oldandsea.pcb.config.apiresponse.ApiResult;
import com.oldandsea.pcb.config.apiresponse.ApiUtils;
import com.oldandsea.pcb.config.exception.NotAuthenticatedException;
import com.oldandsea.pcb.domain.dto.request.*;
import com.oldandsea.pcb.domain.dto.response.MemberLoginResponseDTO;
import com.oldandsea.pcb.domain.dto.layer.LoginDTO;
import com.oldandsea.pcb.service.MemberService;
import com.oldandsea.pcb.service.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Objects;


@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final SessionService sessionService;
    @PostMapping("/uid-check")
    public ApiResult<?> uidCheck(@RequestBody @Valid MemberUidCheckRequestDTO memberUidCheckRequestDto) {
        if (memberService.uidCheck(memberUidCheckRequestDto))
            return ApiUtils.error("중복된 아이디입니다", HttpStatus.BAD_REQUEST);
        else
            return ApiUtils.success("사용할 수 있는 아이디입니다");
    }

    @PostMapping("/nickname-check")
    public ApiResult<?> nickNameCheck(@RequestBody @Valid MemberNickNameCheckRequestDTO memberNickNameCheckRequestDto) {
        if (memberService.nickNameCheck(memberNickNameCheckRequestDto))
            return ApiUtils.error("중복된 닉네임입니다", HttpStatus.BAD_REQUEST);
        else
            return ApiUtils.success("사용할 수 있는 닉네임입니다");
    }

    @PostMapping("/create")
    public ApiResult<?> insertMember(@RequestBody @Valid MemberCreateRequestDTO memberCreateRequestDto) {
        memberService.createMember(memberCreateRequestDto);
        return ApiUtils.success("회원가입 성공");

    }

    @PostMapping("/login")
    public ApiResult<?> login(HttpServletResponse response, @RequestBody @Valid MemberLoginRequestDTO memberLoginRequestDto,
                                   HttpServletRequest request) throws DataAccessException {
        LoginDTO loginResult = memberService.login(memberLoginRequestDto);
        if (loginResult != null) {
            HttpSession session = request.getSession(true);
            String sessionId = session.getId();
            session.setMaxInactiveInterval(1);
            MemberLoginResponseDTO memberLoginResponseDto = sessionService.createSession(loginResult, sessionId, memberLoginRequestDto);
            response.setHeader("Set-Cookie", "PCBSESSIONID=" + sessionId + "; Path=/; HttpOnly; SameSite=None; Secure");
            return ApiUtils.success(memberLoginResponseDto);
        }
        else {
            return ApiUtils.error("로그인을 다시해주세요",HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping("/logout")
    public ApiResult<?> logout(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        String sessionId = null;

        for(Cookie cookie : cookies) {
            if(Objects.equals(cookie.getName(), "PCBSESSIONID")) {
                sessionId = cookie.getValue();
                break;
            }
        }
        if (sessionId == null) {
            throw new IllegalArgumentException("Please login first");
        } else {
            sessionService.deleteSession(sessionId);
            return ApiUtils.success("로그아웃 성공");
        }
    }

    @PutMapping("/pwd")
    public ApiResult<?> updatePwd(@Login Long memberId, @RequestBody MemberPwdUpdateRequestDTO memberPwdUpdateRequestDto) {
        memberService.updatePwd(memberId,memberPwdUpdateRequestDto);
        return ApiUtils.success("비밀번호 수정 성공");
    }


    @PutMapping("/nickname")
    public ApiResult<?> updateNickname(@Login Long memberId, @RequestBody MemberNickUpdateRequestDTO nickUpdateRequestDto) {
        memberService.updateNickname(memberId,nickUpdateRequestDto);
        return ApiUtils.success("닉네임 수정 성공");
    }
    @DeleteMapping("/delete")
    public ApiResult<?> delete(@Login Long memberId) {
        memberService.delete(memberId);
        return ApiUtils.success("회원 탈퇴 성공");
    }

}




