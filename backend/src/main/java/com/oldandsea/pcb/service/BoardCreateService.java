package com.oldandsea.pcb.service;

import com.oldandsea.pcb.domain.dto.request.BoardCreateRequestDto;
import com.oldandsea.pcb.domain.dto.response.BoardCreateResponseDto;
import com.oldandsea.pcb.domain.entity.Board;
import com.oldandsea.pcb.domain.entity.BoardTag;
import com.oldandsea.pcb.domain.entity.Member;
import com.oldandsea.pcb.domain.entity.Tag;
import com.oldandsea.pcb.domain.repository.BoardTagRepository;
import com.oldandsea.pcb.domain.repository.MemberRepository;
import com.oldandsea.pcb.domain.repository.boardrepository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;


@Service
@RequiredArgsConstructor
public class BoardCreateService {
    public final BoardRepository boardRepository;
    public final MemberRepository memberRepository;
    public final TagService tagService;
    public final BoardTagRepository boardTagRepository;

    @Transactional
    public BoardCreateResponseDto createBoard(BoardCreateRequestDto boardCreateDto, Long memberId) {
        /*
        먼저 Board Entity를 생성하고 저장한다.
         */
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("회원이 존재하지 않습니다."));

        Board board = boardCreateDto.toEntity(member);
        Board savedBoard = boardRepository.save(board);

        //BoardCreateDto로부터 List<String> 태그들을 가져와 List<Tag> 를 생성
        if(tagService.hasDuplicateTagNames(boardCreateDto.getBoardTagList())) {
            throw new IllegalArgumentException("Duplicate tag name");
        }
        List<Tag> tags = tagService.createTags(boardCreateDto.getBoardTagList());

        //반복문을 통해 생성하고 저장한 Board Entity와 List<Tag>들의 tag들을 받아와 BoardTag 생성(연관 맺어주기)
        for(Tag tag: tags) {
            BoardTag boardTag = BoardTag.builder()
                    .board(savedBoard)
                    .tag(tag)
                    .build();
            boardTagRepository.save(boardTag);
        }

        //Board를 BoardTag생성 전에 이미 persist 해놓았기 때문에 수정사항이 있으면 알아서 flush 시에(commit시에) DirtyChekcing 되서 수정될것이다.
        return BoardCreateResponseDto.builder()
                .boardId(savedBoard.getBoardId())
                .title(savedBoard.getTitle())
                .content(savedBoard.getContent())
                .build();
    }
}





