package com.eastshine.auction.application;

import com.eastshine.auction.domain.member.Member;
import com.eastshine.auction.domain.member.MemberRepository;
import com.eastshine.auction.domain.member.MemberStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MemberServiceTest extends ServiceTest {
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    private static final String REGISTERED_EMAIL ="test@email.com";
    private static final String PASSWORD ="1234";
    private static Long REGISTERED_ID;

    @BeforeEach
    void setUpEach() {
        memberService = new MemberService(memberRepository);

        Member member = memberService.signUpMember(
                Member.builder()
                        .email(REGISTERED_EMAIL)
                        .password(PASSWORD)
                        .build()
        );
        REGISTERED_ID = member.getId();
    }

    @AfterEach
    void afterEach() {
        memberRepository.deleteAll();
    }

    @Nested
    @DisplayName("회원 가입시에")
    class Describe_signUpMember {
        private Member signupInfo;

        @Nested
        @DisplayName("이메일이 중복되지 않은 경우")
        class Context_with_not_exsisted_email{
            private String otherEmail = "other@email.com";

            @BeforeEach
            void setUp() {
                signupInfo = Member.builder()
                        .email(otherEmail)
                        .password(PASSWORD)
                        .build();
            }

            @DisplayName("가입한 회원 정보를 반환한다.")
            @Test
            void it_throws_InvalidParameterException() {
                Member signedUpMember = memberService.signUpMember(signupInfo);

                assertThat(signedUpMember.getEmail()).isEqualTo(otherEmail);
                assertThat(signedUpMember.getPassword()).isEqualTo(PASSWORD);
                assertThat(signedUpMember.getStatus()).isEqualTo(MemberStatus.SINGUP);
            }

            // Todo 이메일 형식에 맞지 않는 것도 테스트하면 좋을 것 같다.
            // 테스트에 대한 개인적 생각 : 이 어플리케이션이 어떻게 돌아가는 지에 대한 스케치이다.
        }

        @Nested
        @DisplayName("이메일이 중복된 경우")
        class Context_with_exsisted_email{

            @BeforeEach
            void setUp() {
                signupInfo = Member.builder()
                        .email(REGISTERED_EMAIL)
                        .password("other_password")
                        .build();
            }

            @DisplayName("IllegalArgumentException 예외를 던진다.")
            @Test
            void it_throws_IllegalArgumentException() {
                assertThatThrownBy(() -> memberService.signUpMember(signupInfo))
                        .isInstanceOf(IllegalArgumentException.class);
            }
        }
    }

    @Nested
    @DisplayName("회원 정보 수정시")
    class Describe_modifyMember{
        Member modInfo;

        @Nested
        @DisplayName("회원 식별자가 존재할 경우")
        class Context_with_exist_memberId {
            String modEmail = "mod@email.com";
            String modPassword = "modPassword";

            @BeforeEach
            void setUp() {
                modInfo = Member.builder()
                        .id(REGISTERED_ID)
                        .email(modEmail)
                        .password(modPassword)
                        .build();
            }

            @DisplayName("회원 정보를 수정한다.")
            @Test
            void it_modifies_member(){
                Member modifiedMember = memberService.modifyMember(modInfo);

                assertThat(modifiedMember.getId()).isEqualTo(REGISTERED_ID);
                assertThat(modifiedMember.getEmail()).isEqualTo(modEmail);
                assertThat(modifiedMember.getPassword()).isEqualTo(modPassword);
            }
        }

        @Nested
        @DisplayName("회원 식별자가 존재하지 않을 경우")
        class Context_with_not_exist_memberId {

            @BeforeEach
            void setUp() {
                modInfo = Member.builder()
                        .id(9999999L)
                        .email(REGISTERED_EMAIL)
                        .build();
            }

            @DisplayName("IllegalArgumentException 예외를 던진다.")
            @Test
            void it_throws_IllegalArgumentException(){
                assertThatThrownBy(() -> memberService.signUpMember(modInfo))
                        .isInstanceOf(IllegalArgumentException.class);
            }
        }
    }

    @Nested
    @DisplayName("회원 탈퇴시")
    class Describe_dropOutMember{
        Member dropoutRequest;

        @Nested
        @DisplayName("회원 식별자가 존재할 경우")
        class Context_with_exist_memberId {

            @BeforeEach
            void setUp() {
                dropoutRequest = Member.builder()
                        .id(REGISTERED_ID)
                        .build();
            }

            @DisplayName("회원 상태를 탈퇴로 변경한다.")
            @Test
            void it_modifies_member_status(){
                memberService.dropOutMember(dropoutRequest);

                Member member = memberRepository.findById(REGISTERED_ID).orElse(new Member());

                assertThat(member.getId()).isEqualTo(REGISTERED_ID);
                // Test 실패 Dirty check?? ㅜㅜ
                assertThat(member.getStatus()).isEqualTo(MemberStatus.DROPOUT);
            }
        }

        @Nested
        @DisplayName("회원 식별자가 존재하지 않을 경우")
        class Context_with_not_exist_memberId {

            @BeforeEach
            void setUp() {
                dropoutRequest = Member.builder()
                        .id(9999999L)
                        .build();
            }

            @DisplayName("IllegalArgumentException 예외를 던진다.")
            @Test
            void it_throws_IllegalArgumentException(){
                assertThatThrownBy(() -> memberService.dropOutMember(dropoutRequest))
                        .isInstanceOf(IllegalArgumentException.class);
            }
        }
    }
}
