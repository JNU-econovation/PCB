package com.oldandsea.pcb.domain.entity;

import javax.persistence.*;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "tags")
@DynamicUpdate
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long tagId;

    @Column(nullable = false, unique = true, name = "name")
    private String name;

    @Builder //tag 생성
    public Tag(String name) {
        this.name = name;
    }
    //tag 수정
    public void updateTag(String name) {
        this.name = name;
    }
}
