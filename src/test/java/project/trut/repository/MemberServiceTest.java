package project.trut.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import project.trut.domain.member.Member;
import project.trut.repository.member.MemberRepository;
import project.trut.repository.member.MemberUpdateDto;
import project.trut.service.member.MemberService;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@Transactional
@SpringBootTest
@Slf4j
class MemberServiceTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    void save() {
        //given
        Member member = new Member("testId", "testName", "1234");

        //when
        Member saveMember = memberRepository.save(member);

        //then
        Member findMember = memberRepository.findById(member.getId()).get();
        assertThat(findMember).isEqualTo(saveMember);
    }

    @Test
    void update() {
        //given
        Member member = new Member("testId", "testName", "1234");
        Member saveMember = memberRepository.save(member);
        Long id = saveMember.getId();

        //when
        MemberUpdateDto updateParam = new MemberUpdateDto(member.getLoginId(),"newName", "asd");
        memberRepository.update(id, updateParam);

        //then
        Member findMember = memberRepository.findById(id).get();
        assertThat(findMember.getName()).isEqualTo(updateParam.getName());
        assertThat(findMember.getPassword()).isEqualTo(updateParam.getPassword());
    }

    @Test
    void findById() {
        //given
        Member member = new Member("test", "test", "test");
        Member saveMember = memberRepository.save(member);

        //when
        Member findMember = memberRepository.findById(saveMember.getId()).get();

        //then
        assertThat(findMember).isEqualTo(member);

    }

    @Test
    void findByLoginId() {
        //given
        Member member = new Member("test", "test", "test");
        Member saveMember = memberRepository.save(member);

        //when
        Optional<Member> findMember = memberRepository.findByLoginId(saveMember.getLoginId());

        //then
        assertThat(findMember.get()).isEqualTo(member);
    }
}