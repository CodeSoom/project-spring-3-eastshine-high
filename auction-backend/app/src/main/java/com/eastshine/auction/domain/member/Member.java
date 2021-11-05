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

    // Todo 현제 null 값이 입력되고 있다.
    @Column(length = 32, columnDefinition = "varchar(32) default 'SINGUP'")
    @Enumerated(value = EnumType.STRING)
    private MemberStatus status;

    @Builder
    // 테스트를 위해 생성자에 id를 추가 이 정도는 괜찮을까?
    public Member(Long id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
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
