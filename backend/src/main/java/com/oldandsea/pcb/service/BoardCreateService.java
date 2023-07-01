package com.oldandsea.pcb.service;

import com.oldandsea.pcb.domain.dto.request.BoardCreateRequestDto;
import com.oldandsea.pcb.domain.dto.response.BoardCreateResponseDto;
import com.oldandsea.pcb.domain.entity.Board;
import com.oldandsea.pcb.domain.entity.Member;
import com.oldandsea.pcb.domain.repository.MemberRepository;
import com.oldandsea.pcb.domain.repository.boardrepository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class BoardCreateService {
    public final BoardRepository boardRepository;
    public final MemberRepository memberRepository;

    @Transactional
    public BoardCreateResponseDto createBoard(BoardCreateRequestDto boardCreateDto, Long memberId) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("회원이 존재하지 않습니다."));
        Board board = boardCreateDto.toEntity(member);
        Board save = boardRepository.save(board);
        return BoardCreateResponseDto.builder()
                .boardId(save.getBoardId())
                .title(save.getTitle())
                .content(save.getContent())
                .build();
    }
}





