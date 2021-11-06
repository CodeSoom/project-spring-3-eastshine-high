package com.eastshine.auction.web.member;

import com.eastshine.auction.web.ControllerTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class MemberControllerTest extends ControllerTest {
    public static final String EMAIL = "test@eamil.com";
    public static final String PASSWORD = "test1234";

    // Todo 공통 Response 만든 후에 작성.

    @DisplayName("올바른 정보를 통해 회원가입을 요청할 경우, 상태코드 201 Created 를 응답한다.")
    @Test
    void signUpWithValidValue() throws Exception {
        MemberDto.Signup requestSignup = MemberDto.Signup.builder()
                .email(EMAIL)
                .password(PASSWORD)
                .build();

                mockMvc.perform(
                        post("/members")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(requestSignup))
                ).andExpect(status().isCreated());
    }

    @DisplayName("올바르지 못한 정보를 통해 회원가입을 요청할 경우, 상태코드 400 BadRequest 를 응답한다.")
    @Test
    void signUpWithInvalidValue() throws Exception {
        MemberDto.Signup requestSignup = MemberDto.Signup.builder()
                .password(PASSWORD)
                .build();

        mockMvc.perform(
                post("/members")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestSignup))
        ).andExpect(status().isBadRequest());
    }
}
