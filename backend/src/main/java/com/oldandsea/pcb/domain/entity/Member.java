package com.oldandsea.pcb.domain.entity;

import javax.persistence.*;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import java.util.ArrayList;
import java.util.List;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "members")
@DynamicUpdate
public class Member {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column(nullable = false, unique = true, name = "uid" )
    private String uid;

    @Column(nullable = false, name = "pwd")
    private String pwd;

    @Column(nullable = false, unique = true, name = "nickname")
    private String nickname;
    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Board> boards = new ArrayList<>();


    @Builder
    public Member(String uid, String pwd, String nickname) {
        this.uid = uid;
        this.pwd = pwd;
        this.nickname = nickname;
    }
    public void updatePwd(String pwd) {
        this.pwd = pwd;
    }

    public void updateNickname(String nickname) {
        this.nickname = nickname;
    }
}


