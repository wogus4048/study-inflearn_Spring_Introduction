package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller //이렇게 컨트롤러 어노테이션을 적어놓으면 스프링컨테이너가 생성되면서 멤버컨트롤러로서 해당 어노테이션을 가진 컨트롤러를 넣어놓는다.
//그리고 스프링이 관리를 한다.
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }


    @GetMapping("/members/new")
    //GetMapping을 이용해서 해당 도메인(http://127.0.0.1:8080/members/new)에 접근하면 템플릿에 존재하는 html파일로 이동,내용을 보여주고
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    //http://127.0.0.1:8080/members/new에서 무언가 값을 보낼때 MemberForm으로 그 값을 받고
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName()); // form에 받은값을 멤버에 저장해서

//        System.out.println("member = " + member.getName());

        memberService.join(member); //가입시켜주는 로직

        return "redirect:/";// 가입이 끝났으니 redirect를 이용하여 home화면으로 보내준다.
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members); // model에다가 members를 담아서 화면(뷰템플릿)에 보내기 위함
        return "members/memberList";

    }
}
