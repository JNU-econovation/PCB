package com.oldandsea.pcb.service;

import com.oldandsea.pcb.domain.dto.request.BoardCreateRequestDto;
import com.oldandsea.pcb.domain.dto.request.BoardUpdateRequestDto;
import com.oldandsea.pcb.domain.dto.response.BoardCreateResponseDto;
import com.oldandsea.pcb.domain.dto.response.BoardDetailResponseDto;
import com.oldandsea.pcb.domain.dto.response.BoardListResponseDto;
import com.oldandsea.pcb.domain.dto.response.BoardUpdateResponseDto;
import com.oldandsea.pcb.domain.entity.Board;
import com.oldandsea.pcb.domain.entity.BoardTag;
import com.oldandsea.pcb.domain.entity.Member;
import com.oldandsea.pcb.domain.entity.Tag;
import com.oldandsea.pcb.domain.repository.BoardTagRepository;
import com.oldandsea.pcb.domain.repository.MemberRepository;
import com.oldandsea.pcb.domain.repository.TagRepository;
import com.oldandsea.pcb.domain.repository.boardrepository.BoardRepository;
import com.oldandsea.pcb.domain.repository.boardrepository.BoardRepositoryCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.time.ZoneOffset;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class BoardService {
   private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    private final TagService tagService;
    private final BoardTagRepository boardTagRepository;
    private final BoardTagService boardTagService;
    private final MainPageListService mainPageListService;

    private final BoardRepositoryCustom boardRepositoryCustom;

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
        List<Tag> tags = tagService.stringToTagTags(boardCreateDto.getBoardTagList());

        //반복문을 통해 생성하고 저장한 Board Entity와 List<Tag>들의 tag들을 받아와 BoardTag 생성(연관 맺어주기)
        boardTagService.createBoardTag(tags,savedBoard);

        //Board를 BoardTag생성 전에 이미 persist 해놓았기 때문에 수정사항이 있으면 알아서 flush 시에(commit시에) DirtyChekcing 되서 수정될것이다.
        return BoardCreateResponseDto.builder()
                .boardId(savedBoard.getBoardId())
                .title(savedBoard.getTitle())
                .content(savedBoard.getContent())
                .boardTagList(tagService.tagToStringTags(tags))
                .build();
    }
    @Transactional
    public BoardUpdateResponseDto updateBoard(BoardUpdateRequestDto boardUpdateRequestDto, Long boardId) {
        Board board = boardRepository.findById(boardId).orElseThrow(
                () -> new IllegalArgumentException("Board doesn't exsist")
        );
        board.updateBoard(boardUpdateRequestDto.getTitle(),boardUpdateRequestDto.getContent());

        List<Tag> tags = tagService.stringToTagTags(boardUpdateRequestDto.getBoardTagList());
        List<BoardTag> boardTag = boardTagRepository.findByBoardId(boardId).orElseThrow(
                () -> new IllegalArgumentException("Board doesn't exsist")
        );
        for(BoardTag boardTags : boardTag) {
            boardTagService.updateBoardTag(tags, boardTags);
        }
        return BoardUpdateResponseDto.builder()
                .boardId(board.getBoardId())
                .title(board.getTitle())
                .content(board.getContent())
                .boardTagList(tagService.tagToStringTags(tags))
                .build();
    }

    @Transactional
    public void deleteBoard(Long boardId) {
        Board board = boardRepository.findByBoardTagFetch(boardId).orElseThrow(
                () -> new IllegalArgumentException("Board doesn't exsist")
        );
        boardRepository.delete(board);
    }

    @Transactional
    public BoardListResponseDto detailBoard(Long boardId) {
        Board board = boardRepository.findByBoardTagFetch(boardId).orElseThrow(
                () -> new IllegalArgumentException("Board doesn't exist")
        );

        List<String> tagNames = board.getBoardTagList().stream()
                .map(boardTag -> boardTag.getTag().getName())
                .collect(Collectors.toList());

        return BoardListResponseDto.builder()
                .boardId(board.getBoardId())
                .title(board.getTitle())
                .content(board.getContent())
                .boardTagList(tagNames)
                .createdAt(board.getCreatedAt().toEpochSecond(ZoneOffset.UTC))
                .build();
    }

    @Transactional
    public Slice<BoardListResponseDto> searchBoard(String tag, Long lastBoardId, int limit) {
        PageRequest pageRequest = PageRequest.of(0, limit, Sort.by(Sort.Direction.DESC, "boardId"));
        Slice<Board> boardsSlice = boardRepositoryCustom.searchByTagAndSlice(lastBoardId, tag, pageRequest);
        return mainPageListService.getBoardListResponseDtos(pageRequest, boardsSlice);
    }

}





