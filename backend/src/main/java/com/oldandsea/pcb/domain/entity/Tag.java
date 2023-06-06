package com.oldandsea.pcb.domain.entity;

import javax.persistence.*;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "tag")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long tagId;

    @Column(nullable = false, name = "name")
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
