package com.oldandsea.pcb.service;

import com.oldandsea.pcb.domain.entity.Board;
import com.oldandsea.pcb.domain.entity.BoardTag;
import com.oldandsea.pcb.domain.entity.Tag;
import com.oldandsea.pcb.domain.repository.BoardTagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
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

    public void updateBoardTag(List<Tag> tags, BoardTag boardTag) {
        for(Tag tag: tags) {
            boardTag.updateBoardTag(tag);
        }
    }


    public List<String> boardTagToStringTags(List<BoardTag> boardTags) {
        return boardTags.stream()
                .map(boardTag -> boardTag.getTag().getName())
                .collect(Collectors.toList());
    }
}
