package project.trut.domain.service.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import project.trut.domain.member.Member;
import project.trut.domain.member.MemberUpdateDto;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
@Slf4j
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Test
    void save() {
        //given
        Member member = new Member("testId", "testName", "1234");

        //when
        Member saveMember = memberService.save(member);

        //then
        Member findMember = memberService.findById(member.getId()).get();
        assertThat(findMember).isEqualTo(saveMember);
    }

    @Test
    void update() {
        //given
        Member member = new Member("testId", "testName", "1234");
        Member saveMember = memberService.save(member);
        Long id = saveMember.getId();

        //when
        MemberUpdateDto updateParam = new MemberUpdateDto("newName", "asd");
        memberService.update(id, updateParam);

        //then
        Member findMember = memberService.findById(id).get();
        assertThat(findMember.getName()).isEqualTo(updateParam.getName());
        assertThat(findMember.getPassword()).isEqualTo(updateParam.getPassword());
    }

    @Test
    void findById() {
        //given
        Member member = new Member("test", "test", "test");
        Member saveMember = memberService.save(member);

        //when
        Member findMember = memberService.findById(saveMember.getId()).get();

        //then
        assertThat(findMember).isEqualTo(member);

    }

    @Test
    void findByLoginId() {
    }
}