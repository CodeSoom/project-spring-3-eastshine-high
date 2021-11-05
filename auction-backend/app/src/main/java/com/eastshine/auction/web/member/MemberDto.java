package com.eastshine.auction.web.member;

import com.github.dozermapper.core.Mapping;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;


public class MemberDto {

    @ToString
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

    @ToString
    @Getter
    @Builder
    public static class Modification{

        @Mapping("id")
        @NotBlank
        private Long id;

        @Mapping("email")
        @NotBlank
        private String email;

        @Mapping("password")
        @NotBlank
        private String password;
    }
}
