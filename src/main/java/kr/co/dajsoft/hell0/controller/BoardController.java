package kr.co.dajsoft.hell0.controller;


import kr.co.dajsoft.hell0.dto.BoardDTO;
import kr.co.dajsoft.hell0.dto.PageRequestDTO;
import kr.co.dajsoft.hell0.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Log4j2
@RequiredArgsConstructor
//공통 URL 설정
@RequestMapping("/board/")
public class BoardController {
    private final BoardService boardService;
//
//    @GetMapping("board")
//    public void list(PageRequestDTO pageRequestDTO, Model model){
//        model.addAttribute("result", boardService.getList(pageRequestDTO));
//    }

    @GetMapping ("register")
    public void register(){

    }
    @PostMapping("register")
    public String register(BoardDTO dto, RedirectAttributes rattr){
        Long bno = boardService.register(dto);
        rattr.addFlashAttribute("msg", bno+" 등록");
        return "redirect:/board/board";
    }

    @GetMapping({"read", "modify"})
    //ModelAttribute를 작성한 파라미터는 아무런 작업을 하지 않아도 뷰로
    //전달 된다.
    public void read(@ModelAttribute("requestDTO") PageRequestDTO pageRequestDTO, Long bno, Model model){
        BoardDTO dto = boardService.get(bno);
        model.addAttribute("dto", dto);
    }

    @PostMapping("remove")
    public String remove(Long bno, RedirectAttributes rattr){
        boardService.removeWithReplies(bno);
        //출력할 메시지 저장
        rattr.addFlashAttribute("msg",bno + "삭제");
        return "redirect:/board/board";
    }

    @PostMapping("modify")
    public String modify(BoardDTO dto,@ModelAttribute("requestDTO") PageRequestDTO requestDTO, RedirectAttributes rattr){
        boardService.modify(dto);
        rattr.addAttribute("page", requestDTO.getPage());
        rattr.addAttribute("type", requestDTO.getType());
        rattr.addAttribute("keyword", requestDTO.getKeyword());
        rattr.addAttribute("bno", dto.getBoardNUMBER());
        return "redirect:/board/read";
    }
}

