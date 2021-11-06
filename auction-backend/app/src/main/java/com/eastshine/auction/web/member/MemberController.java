package com.eastshine.auction.web.member;

import com.eastshine.auction.application.MemberService;
import com.eastshine.auction.domain.member.Member;
import com.github.dozermapper.core.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;

/**
 * 회원의 HTTP 요청에 대한 처리를 담당
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;
    private final Mapper mapper;

    @PostMapping()
    public ResponseEntity signUpMember(@RequestBody @Valid MemberDto.Signup request) {
        Member requestSignup = mapper.map(request, Member.class);

        Member signedUpMember = memberService.signUpMember(requestSignup);

        return ResponseEntity.created(URI.create("/members/" + signedUpMember.getId())).build();
    }

    @PatchMapping()
    public ResponseEntity modifyMember(@RequestBody @Valid MemberDto.Modification request) {
        Member requestMod = mapper.map(request, Member.class);

        Member modifiedMember = memberService.modifyMember(requestMod);

        return ResponseEntity.ok()
                .location(URI.create("/members/" + modifiedMember.getId()))
                .build();
    }
}
