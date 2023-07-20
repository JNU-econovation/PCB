package com.oldandsea.pcb.service;


import com.oldandsea.pcb.domain.dto.response.BoardListResponseDTO;
import com.oldandsea.pcb.domain.entity.Board;
import com.oldandsea.pcb.domain.repository.boardrepository.BoardRepositoryCustom;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.ZoneOffset;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MainPageListService {

    private final BoardRepositoryCustom boardRepositoryCustom;
    private final BoardTagService boardTagService;


    public Slice<BoardListResponseDTO> getAllBoards(Long lastBoardId, int limit) {
        PageRequest pageRequest = PageRequest.of(0, limit, Sort.by(Sort.Direction.DESC, "boardId"));
        Slice<Board> boardsSlice = boardRepositoryCustom.searchAllBySlice(lastBoardId, pageRequest);
        return getBoardListResponseDtos(pageRequest, boardsSlice);

    }

    public Slice<BoardListResponseDTO> getBoardListResponseDtos(PageRequest pageRequest, Slice<Board> boardsSlice) {
        List<BoardListResponseDTO> boardListResponseDto = boardsSlice.getContent().stream()
                .map(board -> BoardListResponseDTO.builder()
                        .boardId(board.getBoardId())
                        .title(board.getTitle())
                        .content(board.getContent())
                        .createdAt(board.getCreatedAt().toEpochSecond(ZoneOffset.UTC))
                        .boardTagList(boardTagService.boardTagToStringTags(board.getBoardTagList()))
                        .nickname(board.getMember().getNickname())
                        .build())
                .collect(Collectors.toList());

        return new SliceImpl<>(boardListResponseDto, pageRequest, boardsSlice.hasNext());
    }

}
