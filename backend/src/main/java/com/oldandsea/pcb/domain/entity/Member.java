package com.oldandsea.pcb.domain.entity;

import javax.persistence.*;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;



@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "member")
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

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof Member))
//            return false;
//        Member member = (Member) o;
//        return member.uid == uid && member.nickname == nickname;
//    }
//
//    @Override
//    public int hashCode() {
//        int c = 31;
//        int result = uid == null ? 0 : uid.hashCode();
//        result = c * result + nickname == null ? 0 : nickname.hashCode();
//        return result;
//
//    }
}


