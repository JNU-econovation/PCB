package com.oldandsea.pcb.controller;

import com.oldandsea.pcb.config.Login;
import com.oldandsea.pcb.config.apiresponse.ApiResult;
import com.oldandsea.pcb.config.apiresponse.ApiUtils;
import com.oldandsea.pcb.domain.dto.layer.CommentUpdateContentList;
import com.oldandsea.pcb.domain.dto.request.CommentCreateRequestDTO;
import com.oldandsea.pcb.domain.dto.layer.CommentUpdatePositionList;
import com.oldandsea.pcb.domain.dto.request.CommentUpdateContentRequestDTO;
import com.oldandsea.pcb.domain.dto.request.CommentUpdatePositionReqeustDTO;
import com.oldandsea.pcb.domain.dto.response.CommentCreateResponseDTO;
import com.oldandsea.pcb.domain.dto.response.CommentUpdateContentResponseDTO;
import com.oldandsea.pcb.domain.dto.response.CommentUpdatePositionResponseDTO;
import com.oldandsea.pcb.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/create")
    public ApiResult<?> createComment(@RequestBody @Valid CommentCreateRequestDTO createRequestDTO, @Login Long memberId) {
        CommentCreateResponseDTO createResponseDTO = commentService.createComment(createRequestDTO,memberId);
        return ApiUtils.success(createResponseDTO);
    }

    @PostMapping("/update/position")
    public ApiResult<?> updatePostion(@RequestBody @Valid CommentUpdatePositionReqeustDTO requestDTO,
                                      @Login Long memberId) {
        List<CommentUpdatePositionList> updatePositionList = requestDTO.getUpdatePositionList();
        CommentUpdatePositionResponseDTO responseDTO = commentService.updatePosition(updatePositionList,memberId);
        return ApiUtils.success(responseDTO);
    }

    @PostMapping("/update/content")
    public ApiResult<?> updateContent(@RequestBody @Valid CommentUpdateContentRequestDTO requestDTO,
                                      @Login Long memberId) {
        List<CommentUpdateContentList> updateContentList = requestDTO.getUpdateContentList();
        CommentUpdateContentResponseDTO responseDTO = commentService.updateContent(updateContentList,memberId);
        return ApiUtils.success(responseDTO);
    }

    @PostMapping("/delete/{commentId}")
    public ApiResult<?> deleteComment(@RequestBody @Valid CommentUpdatePositionReqeustDTO reqeustDTO,
                                      @Login Long memberId, @PathVariable Long commentId) {
        List<CommentUpdatePositionList> updatePositionList = reqeustDTO.getUpdatePositionList();
        CommentUpdatePositionResponseDTO responseDTO = commentService.deleteCommnet(updatePositionList,memberId,commentId);
        return ApiUtils.success(responseDTO);
    }

}
