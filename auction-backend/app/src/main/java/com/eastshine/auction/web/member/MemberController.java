package com.eastshine.auction.web.member;

import com.eastshine.auction.application.MemberService;
import com.eastshine.auction.domain.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("members")
public class MemberController {
    private final MemberService memberService;

    @PostMapping("")
    public String signUpMember(@RequestBody @Valid MemberDto.Signup signupRequest) {
        Member member = memberService.signUpMember(signupRequest);
        return null;
    }

}
