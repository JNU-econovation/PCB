package com.oldandsea.pcb.domain.entity;

import javax.persistence.*;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;



@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "members")
public class Member {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column(nullable = false, name = "uid" )
    private String uid;

    @Column(nullable = false, name = "pwd")
    private String pwd;

    @Column(nullable = false, unique = true, name = "nickname")
    private String nickname;

    @Builder
    public Member(String uid, String pwd, String nickname) {
        this.uid = uid;
        this.pwd = pwd;
        this.nickname = nickname;
    }
    public void updateMember(String pwd, String nickname) {
        this.pwd = pwd;
        this.nickname = nickname;
    }
}


