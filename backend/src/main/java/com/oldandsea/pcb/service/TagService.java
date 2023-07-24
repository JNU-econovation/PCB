package com.oldandsea.pcb.service;

import com.oldandsea.pcb.domain.dto.layer.TagDTO;
import com.oldandsea.pcb.domain.entity.Tag;
import com.oldandsea.pcb.domain.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class TagService {
    private final TagRepository tagRepository;


    public boolean hasDuplicateTagNames(List<String> tagNameList) {
        Set<String> tagNameSet = new HashSet<>(tagNameList);
        return tagNameSet.size() != tagNameList.size();
    }

    public List<String> tagToStringTags(List<Tag> tagNames) {
        return tagNames.stream().map(Tag::getName)
                .collect(Collectors.toList());
    }

    public List<Tag> stringToTagTags(List<String> tagNames) {
        return tagNames.stream()
                .map(tagName -> tagRepository.findByName(tagName)
                        .orElseGet(() -> {
                            Tag newTag = Tag.builder()
                                    .name(tagName)
                                    .build();
                            tagRepository.save(newTag);
                            return newTag;
                        }))
                .collect(Collectors.toList());
    }

    public List<TagDTO> toTagDTOList(Long boardId) {
        List<Tag> tagList = findByBoardId(boardId);
        return tagList.stream()
                .map(tag -> TagDTO.builder()
                        .name(tag.getName())
                        .build())
                .collect(Collectors.toList());
    }

    private List<Tag> findByBoardId(Long boardId) {
        List<Tag> tagList = tagRepository.findByBoardTagFetch(boardId);
        if(tagList.isEmpty()) {
            throw new IllegalArgumentException("Tag doens't exsist");
        }
        return tagList;
    }
}
