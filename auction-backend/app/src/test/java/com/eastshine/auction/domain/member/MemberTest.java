package com.eastshine.auction.domain.member;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberTest {

    @Test
    void changeMemberStatus() {
        Member member = Member.builder()
                .id(2L)
                .email("test@email.com")
                .status(MemberStatus.SINGUP)
                .build();

        member.changeMemberStatus(MemberStatus.DROPOUT);

        assertThat(member.getStatus()).isEqualTo(MemberStatus.DROPOUT);
    }
}
