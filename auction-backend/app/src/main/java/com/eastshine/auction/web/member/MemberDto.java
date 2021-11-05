package com.eastshine.auction.web.member;

import com.github.dozermapper.core.Mapping;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

public class MemberDto {

    @Getter
    @Builder
    public static class Signup{

        @Mapping("email")
        @NotBlank
        private String email;

        @Mapping("password")
        @NotBlank
        private String password;
    }
}
