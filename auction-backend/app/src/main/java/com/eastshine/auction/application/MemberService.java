package com.eastshine.auction.application;

import com.eastshine.auction.domain.member.Member;
import com.eastshine.auction.domain.member.MemberRepository;
import com.eastshine.auction.domain.member.MemberStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class MemberService {
    private MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원 가입을 하고 가입된 회원 정보를 반환합니다.
     *
     * @param requestSignup 회원 가입할 정보.
     * @return 회원 가입된 정보.
     * @Throw IllegalArgumentException 회원 가입할 이메일 정보가 중복된 경우.
     */
    public Member signUpMember(Member requestSignup) {
        if(memberRepository.existsByEmail(requestSignup.getEmail())){
            throw new IllegalArgumentException("duplicate email");
        }

        Member signedUpMember = memberRepository.save(requestSignup);

        return signedUpMember;
    }

    /**
     * 회원 정보를 수정하고 수정된 회원 정보를 반환합니다.
     *
     * @param requestMod 회원 수정 요청 정보.
     * @return 수정된 회원 정보.
     */
    public Member modifyMember(Member requestMod) {
        Member member = this.getMember(requestMod.getId());

        member.modifyEmail(requestMod.getEmail());
        member.modifyPassword(requestMod.getPassword());

        return member;
    }

    /**
     * 회원 상태를 탈퇴로 변경합니다.
     *
     * @param requestDropout 회원 삭제 요청 정보.
     */
    public void dropOutMember(Member requestDropout) {
        Member member = getMember(requestDropout.getId());

        member.changeMemberStatus(MemberStatus.DROPOUT);
    }

    /**
     * 식별자에 해당하는 회원 엔티티를 찾아 반환합니다.
     *
     * @param id 회원 식별자.
     * @return 회원 엔티티.
     * @Throw IllegalArgumentException 식별자에 해당하는 엔티티를 찾을 수 없는 경우.
     */
    private Member getMember(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("아이디를 찾을 수 없습니다." + id));
    }
}
