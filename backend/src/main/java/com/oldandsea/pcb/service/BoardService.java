package com.oldandsea.pcb.service;

import com.oldandsea.pcb.domain.dto.request.BoardCreateRequestDTO;
import com.oldandsea.pcb.domain.dto.request.BoardUpdateRequestDTO;
import com.oldandsea.pcb.domain.dto.response.BoardCreateResponseDTO;
import com.oldandsea.pcb.domain.dto.response.BoardListResponseDTO;
import com.oldandsea.pcb.domain.dto.response.BoardToPostItResponseDTO;
import com.oldandsea.pcb.domain.dto.response.BoardUpdateResponseDTO;
import com.oldandsea.pcb.domain.entity.*;
import com.oldandsea.pcb.domain.repository.BoardTagRepository;
import com.oldandsea.pcb.domain.repository.MemberRepository;
import com.oldandsea.pcb.domain.repository.boardrepository.BoardRepository;
import com.oldandsea.pcb.domain.repository.boardrepository.BoardRepositoryCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZoneOffset;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {
   private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    private final TagService tagService;
    private final BoardTagRepository boardTagRepository;
    private final BoardTagService boardTagService;
    private final MainPageListService mainPageListService;
    private final BoardRepositoryCustom boardRepositoryCustom;
    private final CommentService commentService;

    @Transactional
    public BoardCreateResponseDTO createBoard(BoardCreateRequestDTO boardCreateDto, Long memberId) {
        /*
        먼저 Board Entity를 생성하고 저장한다.
         */
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("memberId에 해당하는 member가 존재하지 않습니다(게시글 생성)"));
        Board board = boardCreateDto.toEntity(member);
        Board savedBoard = boardRepository.save(board);

        //BoardCreateDto로부터 List<String> 태그들을 가져와 List<Tag> 를 생성
        if(tagService.hasDuplicateTagNames(boardCreateDto.getBoardTagList())) {
            throw new IllegalArgumentException("중복된 태그는 입력할 수 없습니다");
        }
        List<Tag> tags = tagService.stringToTagTags(boardCreateDto.getBoardTagList());
        //반복문을 통해 생성하고 저장한 Board Entity와 List<Tag>들의 tag들을 받아와 BoardTag 생성(연관 맺어주기)
        boardTagService.createBoardTag(tags,savedBoard);

        //Board를 BoardTag생성 전에 이미 persist 해놓았기 때문에 수정사항이 있으면 알아서 flush 시에(commit시에) DirtyChekcing 되서 수정될것이다.
        return BoardCreateResponseDTO.builder()
                .boardId(savedBoard.getBoardId())
                .title(savedBoard.getTitle())
                .content(savedBoard.getContent())
                .boardTagList(tagService.tagToStringTags(tags))
                .build();
    }
    @Transactional
    public BoardUpdateResponseDTO updateBoard(BoardUpdateRequestDTO boardUpdateRequestDto, Long boardId) {
        Board board = boardFindById(boardId);
        board.updateBoard(boardUpdateRequestDto.getTitle(),boardUpdateRequestDto.getContent());

        List<Tag> tags = tagService.stringToTagTags(boardUpdateRequestDto.getBoardTagList());
        List<BoardTag> boardTag = boardTagRepository.findByBoardId(boardId);
        if(boardTag.isEmpty()) {
            throw new IllegalArgumentException("boardId에 해당하는 board가 존재하지 않습니다(게시글 수정)");
        }

        for(BoardTag boardTags : boardTag) {
            boardTagService.updateBoardTag(tags, boardTags);
        }
        return BoardUpdateResponseDTO.builder()
                .boardId(board.getBoardId())
                .title(board.getTitle())
                .content(board.getContent())
                .boardTagList(tagService.tagToStringTags(tags))
                .build();
    }

    @Transactional
    public void deleteBoard(Long boardId) {
        Board board = boardRepository.findByBoardTagFetch(boardId).orElseThrow(
                () -> new IllegalArgumentException("boardId에 해당하는 board가 존재하지 않습니다(게시글 삭제)")
        );
        boardRepository.delete(board);
    }

    public BoardListResponseDTO detailBoard(Long boardId) {
        Board board = boardRepository.findByBoardTagFetch(boardId).orElseThrow(
                () -> new IllegalArgumentException("boardId에 해당하는 board가 존재하지 않습니다(게시글 상세)")
        );

        List<String> tagNames = board.getBoardTagList().stream()
                .map(boardTag -> boardTag.getTag().getName())
                .collect(Collectors.toList());

        return BoardListResponseDTO.builder()
                .boardId(board.getBoardId())
                .title(board.getTitle())
                .content(board.getContent())
                .boardTagList(tagNames)
                .createdAt(board.getCreatedAt().toEpochSecond(ZoneOffset.UTC))
                .nickname(board.getMember().getNickname())
                .build();
    }

    public Slice<BoardListResponseDTO> searchBoard(String tag, Long lastBoardId, int limit) {
        PageRequest pageRequest = PageRequest.of(0, limit, Sort.by(Sort.Direction.DESC, "boardId"));
        Slice<Board> boardsSlice = boardRepositoryCustom.searchByTagAndSlice(lastBoardId, tag, pageRequest);
        return mainPageListService.getBoardListResponseDtos(pageRequest, boardsSlice);
    }

    public BoardToPostItResponseDTO boardToPostIt(Long boardId) {
        Board board = boardFindById(boardId);
        return toBoardToPostItResponseDTO(board);
    }

    private BoardToPostItResponseDTO toBoardToPostItResponseDTO(Board board) {
        return BoardToPostItResponseDTO.builder()
                .title(board.getTitle())
                .boardId(board.getBoardId())
                .createdAt(board.getCreatedAt().toEpochSecond(ZoneOffset.UTC))
                .nickname(board.getMember().getNickname())
                .boardCommentList(commentService.commentDTO(board))
                .build();
    }
    public Board boardFindById(Long boardId) {
        return  boardRepository.findById(boardId).orElseThrow(
                () -> new IllegalArgumentException("boardId에 해당하는 board가 존재하지 않습니다(게시글 수정)")
        );
    }
}





