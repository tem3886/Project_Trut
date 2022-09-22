package project.trut.domain.member.mybatis;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import project.trut.domain.member.Member;
import project.trut.domain.member.MemberUpdateDto;

import java.util.Optional;

@Mapper
public interface MemberMapper {

    void save(Member member);

    void update(@Param("id") Long id, @Param("updateParam") MemberUpdateDto memberUpdateDto);

    Optional<Member> findById(Long id);

    Optional<Member> findByLoginId(String loginId);
}
