package com.eastshine.auction.web.member;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

public class MemberDto {

    @Getter
    @Builder
    public static class Signup{
        @NotBlank
        private String email;
        @NotBlank
        private String password;
    }
}
