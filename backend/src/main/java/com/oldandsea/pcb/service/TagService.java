package com.oldandsea.pcb.service;

import com.oldandsea.pcb.domain.entity.Tag;
import com.oldandsea.pcb.domain.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class TagService {
    private final TagRepository tagRepository;

    public boolean hasDuplicateTagNames(List<String> tagNameList) {
        Set<String> tagNameSet = new HashSet<>(tagNameList);
        return tagNameSet.size() != tagNameList.size();
    }

    public List<String> tagToStringTags(List<Tag> tagNames) {
        return IntStream.range(0, tagNames.size())
                .mapToObj((i) -> tagNames.get(i).getName())
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
}

