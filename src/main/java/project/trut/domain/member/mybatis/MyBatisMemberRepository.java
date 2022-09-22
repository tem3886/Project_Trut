package project.trut.domain.member.mybatis;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import project.trut.domain.member.Member;
import project.trut.domain.member.MemberRepository;
import project.trut.domain.member.MemberUpdateDto;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class MyBatisMemberRepository implements MemberRepository {

    private final MemberMapper memberMapper;

    @Override
    public Member save(Member member) {
        memberMapper.save(member);
        return member;
    }

    @Override
    public void update(Long id, MemberUpdateDto updateParam) {
        memberMapper.update(id,updateParam);
    }

    @Override
    public Optional<Member> findById(Long id) {
        return memberMapper.findById(id);
    }

    @Override
    public Optional<Member> findByLoginId(String loginId) {
        return memberMapper.findByLoginId(loginId);
    }
}
