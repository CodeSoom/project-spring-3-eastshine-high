package com.eastshine.auction.application;

import com.eastshine.auction.domain.member.Member;
import com.eastshine.auction.domain.member.MemberRepository;
import com.eastshine.auction.web.member.MemberDto;
import com.github.dozermapper.core.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;

@Service
public class MemberService {
    private MemberRepository memberRepository;
    private Mapper mapper;

    public MemberService(MemberRepository memberRepository, Mapper mapper) {
        this.memberRepository = memberRepository;
        this.mapper = mapper;
    }

    public Long signUpMember(MemberDto.Registration registration) {
        String email = registration.getEmail();
        if(memberRepository.existsByEmail(email)){
            throw new InvalidParameterException("duplicate email");
        }

        Member member = mapper.map(registration, Member.class);
        Member savedMember = memberRepository.save(member);

        return savedMember.getId();
    }
}
