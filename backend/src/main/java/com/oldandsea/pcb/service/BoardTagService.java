package com.oldandsea.pcb.service;

import com.oldandsea.pcb.domain.dto.request.BoardUpdateRequestDto;
import com.oldandsea.pcb.domain.entity.Board;
import com.oldandsea.pcb.domain.entity.BoardTag;
import com.oldandsea.pcb.domain.entity.Tag;
import com.oldandsea.pcb.domain.repository.BoardTagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardTagService {
    private final BoardTagRepository boardTagRepository;

    @Transactional
    public void createBoardTag(List<Tag> tags, Board savedBoard) {
        for (Tag tag : tags) {
            BoardTag boardTag = BoardTag.builder()
                    .board(savedBoard)
                    .tag(tag)
                    .build();
            boardTagRepository.save(boardTag);
        }
    }

    @Transactional
    public void updateBoardTag(List<Tag> tags, BoardTag boardTag) {
        for(Tag tag: tags) {
            boardTag.updateBoardTag(tag);
        }
    }
}
