package project.trut.web.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import project.trut.domain.member.Member;
import project.trut.repository.member.MemberUpdateDto;
import project.trut.service.member.MemberService;
import project.trut.web.SessionConst;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

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
            memberService.save(member);
        } catch (DuplicateKeyException e) {
            bindingResult.reject("addFail", "중복된 아이디가 존재합니다.");
            return "members/addMemberForm";
        }
        return "redirect:/";
    }

    @GetMapping("/update")
    public String updateForm(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Member member = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);

        model.addAttribute("member", member);
        return "members/updateMemberForm";
    }

    @PostMapping("/update")
    public String update(@Validated @ModelAttribute("member") MemberUpdateDto memberUpdateDto,
                         BindingResult bindingResult,
                         HttpServletRequest request,
                         Model model) {

        if (bindingResult.hasErrors()) {
            return "members/updateMemberForm";
        }

        HttpSession session = request.getSession(false);
        Member member = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);

        memberService.update(member.getId(), memberUpdateDto);
        member.setName(memberUpdateDto.getName());
        model.addAttribute("member", member);

        return "redirect:/";
    }

    @RequestMapping("/list")
    public String listForm(@ModelAttribute Member member) {
        return "/";
    }
}
