package project.trut.domain.service.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.trut.domain.member.Member;
import project.trut.domain.member.MemberRepository;
import project.trut.domain.member.MemberUpdateDto;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Member save(Member member){
        return memberRepository.save(member);
    }

    public void update(Long id, MemberUpdateDto updateParam) {
        memberRepository.update(id,updateParam);
    }

    public Optional<Member> findById(Long id) {
        return memberRepository.findById(id);
    }

    public Optional<Member> findByLoginId(String loginId) {
        return memberRepository.findByLoginId(loginId);
    }

}
