package com.oldandsea.pcb.controller;



import com.oldandsea.pcb.domain.dto.request.MemberRequestDto;

import com.oldandsea.pcb.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;



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


}




