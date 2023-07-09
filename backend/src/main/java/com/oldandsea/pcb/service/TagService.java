package com.oldandsea.pcb.service;

import com.oldandsea.pcb.domain.entity.Tag;
import com.oldandsea.pcb.domain.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class TagService {
    private final TagRepository tagRepository;

    public List<Tag> createTags(List<String> tagNames) {
        List<Tag> tags = new ArrayList<>();
        for(String tagName : tagNames) {
            Tag tag = tagRepository.findByName(tagName)
                    .orElseGet(() -> {
                        Tag newTag = Tag.builder()
                                .name(tagName)
                                .build();
                        tagRepository.save(newTag);
                        return newTag;
                    });
            tags.add(tag);
        }
        return tags;
    }

    public boolean hasDuplicateTagNames(List<String> tagNameList) {
        Set<String> tagNameSet = new HashSet<>(tagNameList);
        return tagNameSet.size() != tagNameList.size();
    }

}

