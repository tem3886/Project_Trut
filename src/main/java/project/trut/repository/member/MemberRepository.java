package project.trut.repository.member;

import project.trut.domain.member.Member;

import java.util.Optional;

public interface MemberRepository {

    Member save(Member member);

    void update(Long id, MemberUpdateDto updateParam);

    Optional<Member> findById(Long id);

    Optional<Member> findByLoginId(String loginId);

}
