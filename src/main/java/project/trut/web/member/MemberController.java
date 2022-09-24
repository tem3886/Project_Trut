package project.trut.web.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import project.trut.domain.member.Member;
import project.trut.domain.member.MemberRepository;
import project.trut.domain.member.MemberUpdateDto;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberRepository memberRepository;

    @GetMapping("/add")
    public String addForm(@ModelAttribute Member member) {
        return "members/addMemberForm";
    }

    @PostMapping("/add")
    public String save(@Validated @ModelAttribute Member member, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "members/addMemberForm";
        }
        try {
            memberRepository.save(member);
        } catch (DuplicateKeyException e) {
            bindingResult.reject("addFail", "중복된 아이디가 존재합니다.");
            return "members/addMemberForm";
        }
        return "redirect:/";
    }

    @GetMapping("/update")
    public String updateForm(@ModelAttribute Member member) {
        return "members/updateMemberForm";
    }

    @PostMapping("/update")
    public String update(@Validated @ModelAttribute MemberUpdateDto memberUpdateDto,
                         BindingResult bindingResult) {

        return "redirect:/";
    }

    @RequestMapping("/list")
    public String listForm(@ModelAttribute Member member) {
        return "members/list";
    }
}
