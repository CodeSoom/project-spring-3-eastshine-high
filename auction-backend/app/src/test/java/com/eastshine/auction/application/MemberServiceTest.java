package com.eastshine.auction.application;

import com.eastshine.auction.domain.member.MemberRepository;
import com.eastshine.auction.web.member.MemberDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;

import javax.transaction.Transactional;
import java.security.InvalidParameterException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Transactional
@Commit
class MemberServiceTest extends ServiceTest {
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    private static final String REGISTERED_EMAIL ="test@email.com";
    private final String PASSWORD ="1234";


    @BeforeEach
    void setUp() {
        memberService = new MemberService(memberRepository, mapper);

        memberService.signUpMember(
                MemberDto.Signup.builder()
                .email(REGISTERED_EMAIL)
                .password(PASSWORD)
                .build()
        );
    }

    @Nested
    @DisplayName("회원 가입시에")
    class Describe_signUpMember {
        private MemberDto.Signup signupRequest;

        @Nested
        @DisplayName("이메일이 중복된 경우")
        class Context_with_exsisted_id{

            @BeforeEach
            void setUp() {
                signupRequest = MemberDto.Signup.builder()
                        .email(REGISTERED_EMAIL)
                        .password("other_password")
                        .build();
            }

            @DisplayName("예외를 던진다.")
            @Test
            void it_throws_InvalidParameterException() {
                assertThatThrownBy(() -> memberService.signUpMember(signupRequest))
                        .isInstanceOf(InvalidParameterException.class);
            }
        }
    }
}
