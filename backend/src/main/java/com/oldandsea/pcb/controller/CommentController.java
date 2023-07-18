package com.oldandsea.pcb.controller;

import com.oldandsea.pcb.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class CommentController {
    private final CommentService commentService;

}
