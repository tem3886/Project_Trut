package project.trut.repository.member.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import project.trut.domain.member.Member;

import java.util.Optional;

public interface JpaMemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByLoginId(String loginId);
}
