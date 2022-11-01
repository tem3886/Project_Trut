package project.trut.repository.member.jpa;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import project.trut.domain.member.Member;
import project.trut.repository.member.MemberRepository;
import project.trut.repository.member.MemberUpdateDto;

import java.util.Optional;

@Repository
@Transactional
@RequiredArgsConstructor
public class JpaMemberRepositoryImple implements MemberRepository {

    private final JpaMemberRepository jpaMemberRepository;

    @Override
    public Member save(Member member) {
        return jpaMemberRepository.save(member);
    }

    @Override
    public void update(Long id, MemberUpdateDto updateParam) {
        Member findMember = jpaMemberRepository.findById(id).orElseThrow();
        findMember.setPassword(updateParam.getPassword());
        findMember.setName(updateParam.getName());
    }

    @Override
    public Optional<Member> findById(Long id) {
        return jpaMemberRepository.findById(id);
    }

    @Override
    public Optional<Member> findByLoginId(String loginId) {
        return jpaMemberRepository.findByLoginId(loginId);
    }
}
