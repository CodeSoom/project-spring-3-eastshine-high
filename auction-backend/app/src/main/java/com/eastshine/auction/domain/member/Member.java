package com.eastshine.auction.domain.member;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 회원 도메인
 */
@Getter
@NoArgsConstructor
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;

    @Enumerated(value = EnumType.STRING)
    private MemberStatus status;

    @Builder
    public Member(Long id, String email, String password, MemberStatus status) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.status = status;
    }

    public void modifyEmail(String email) {
        this.email = email;
    }

    public void modifyPassword(String password){
        this.password = password;
    }

    public void changeMemberStatus(MemberStatus memberStatus) {
        this.status = memberStatus;
    }
}
