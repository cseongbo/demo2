package spring.basic.demo2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import spring.basic.demo2.domain.Member;
import spring.basic.demo2.service.MemberService;

import java.util.List;

@Controller
public class MemberController {

    MemberService service;

    @Autowired
    public MemberController(MemberService service) {
        this.service = service;
    }

    @GetMapping("members/new")
    public String createMember() {
        return "members/createForm";
    }

    @PostMapping("members/new")
    public String create(MemberForm memberForm) {
        // 우리는 사용자가 입력한 name 값을 가지고 객체를 만들어야함.

        Member m = new Member();
        m.setName(memberForm.getName());

        // DB에 넣어야 함. (DB에 넣어 줄 때, Member 객체의 id값을 시스템에서
        service.join(m);

        return "redirect:/";    // 제일 첫페이m
    }

    @GetMapping("members/find")
    public String findMember() {
        return "members/findForm";
    }

    @PostMapping("members/find")
    public String find(@RequestParam("id") int id, Model model) {
        // Service를 통해서 id로 Member를 찾아서
        Member m = service.findMemberById(id);

        // member 상자에 m객체를 담아서 다음 페이지로 넘겨줘
        model.addAttribute("member", m);

        // 다음페이지로 이동
        return "members/findMember";
    }

    @GetMapping("members") // localhost:8080/members
    public String memberList(Model model) {

        List<Member> members = service.findAllMember();
        model.addAttribute("members", members);

        return "members/memberList";



    }

}
