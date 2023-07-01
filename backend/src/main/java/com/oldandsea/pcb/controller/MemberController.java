package com.oldandsea.pcb.controller;



import com.oldandsea.pcb.domain.dto.request.MemberRequestDto;

import com.oldandsea.pcb.domain.dto.response.MemberResponseDto;
import com.oldandsea.pcb.service.MemberService;
import com.oldandsea.pcb.config.SessionConst;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;



    @PostMapping("/create")

    public ResponseEntity<String> insertMember(@RequestBody MemberRequestDto memberRequestDto) {
        if (memberService.memberCheck(memberRequestDto)) {
            memberService.createMember(memberRequestDto);
            return ResponseEntity.ok("Member created successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Duplicate ID");
        }
    }

    @PostMapping("/login")
    public  ResponseEntity<String> login(@RequestBody MemberRequestDto memberRequestDto, HttpServletRequest request) {
        MemberResponseDto loginResult = memberService.login(memberRequestDto);
        if(loginResult != null) {
            HttpSession session = request.getSession(true);
            session.setAttribute(SessionConst.LOGIN_MEMBER,loginResult.getMemberId());

            return ResponseEntity.ok("Login Success");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed");
        }
    }
    @PostMapping("/logout")

    public ResponseEntity<String> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if(session!=null) {
            session.invalidate();
            return ResponseEntity.ok(("Logout success"));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Please Login First");
        }
    }

}




