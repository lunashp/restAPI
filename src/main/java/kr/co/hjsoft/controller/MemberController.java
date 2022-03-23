package kr.co.hjsoft.controller;

import kr.co.hjsoft.dto.BoardDTO;
import kr.co.hjsoft.dto.MemberDTO;
import kr.co.hjsoft.dto.PageRequestDTO;
import kr.co.hjsoft.entity.Member;
import kr.co.hjsoft.repository.MemberRepository;
import kr.co.hjsoft.service.MemberService;
import kr.co.hjsoft.service.MemberServiceImpl;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Map;
import java.util.Optional;

@Controller
@AllArgsConstructor
//@RequiredArgsConstructor
public class MemberController {
    private MemberServiceImpl memberService;
    private final MemberService memberservice2;
    private MemberRepository memberRepository;

    @GetMapping("/login/signup")
    public String dispSignup(MemberDTO memberdto) {
        return "/login/signup";
    }

    @PostMapping("/login/signup")
    public String execSignup(@Valid MemberDTO memberdto, Errors errors, Model model) {
        if (errors.hasErrors()) {
            // 회원가입 실패시, 입력 데이터를 유지
            model.addAttribute("memberdto", memberdto);

            // 유효성 통과 못한 필드와 메시지를 핸들링
            Map<String, String> validatorResult = memberService.validateHandling(errors);
            for (String key : validatorResult.keySet()) {
                model.addAttribute(key, validatorResult.get(key));
            }

            return "/login/signup";
        }

        memberService.joinUser(memberdto);
        return "/login/login";
    }

    @GetMapping("/login/login")
    public String displogin() {
        return "/login/login";
    }


    // 로그인 결과 페이지
    @GetMapping("/login/login/result")
    public String dispLoginResult() {
        return "/login/loginSuccess";
    }

    // 로그아웃 결과 페이지
    @GetMapping("/login/logout/result")
    public String dispLogout() {
        return "/login/logout";
    }

    // 접근 거부 페이지
    @GetMapping("/login/denied")
    public String dispDenied() {
        return "/login/denied";
    }

    // 내 정보 페이지
    @GetMapping("/login/info")
    public String dispMyInfo() {
        return "/login/myinfo";
    }

    // 어드민 페이지
    @GetMapping("/admin")
    public String dispAdmin() {
        return "/login/admin";
    }


//    @GetMapping({"read", "modify"})
//    //ModelAttribute를 작성한 파라미터는 아무런 작업을 하지 않아도 뷰로
//    //전달 된다.
//    public void read(@ModelAttribute("requestDTO") PageRequestDTO pageRequestDTO, String memberNICKNAME, Model model){
//        MemberDTO memberdto = memberService.get(memberNICKNAME);
//        model.addAttribute("memberdto", memberdto);
//    }

    // 정보 수정 페이지
//    @GetMapping("/login/modify")
//        public void modify(@ModelAttribute("requestDTO") PageRequestDTO pageRequestDTO, String memberEMAIL, Model model){
//        Optional<Member> memberWrapper = memberRepository.findBymemberEMAIL(memberEMAIL);
//        System.out.println(memberWrapper.toString());
//        Member member = memberWrapper.get();
//        model.addAttribute("memberdto", member);
//    }
    @GetMapping("/login/modify")
    public String modify(){
        return "/login/modify";
    }
    @PostMapping("/login/modify")
    public String modify(MemberDTO memberdto, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, RedirectAttributes rattr){
        memberservice2.modify(memberdto);
        rattr.addAttribute("memberEAMIL", memberdto.getMemberEMAIL());
        return "redirect:/login/info";
    }


    // 회원 탈퇴 페이지
    @GetMapping("/login/delete")
    public String dispDelete() {
        return "/login/delete";
    }

    @PostMapping("/login/delete")
    public String remove(String memberEMAIL, RedirectAttributes rattr){
        memberservice2.delete(memberEMAIL);
        //출력할 메시지 저장
        rattr.addFlashAttribute("msg",memberEMAIL + "탈퇴");
        return "redirect:/login/delete";
    }
}
