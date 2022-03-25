package kr.co.hjsoft.controller;

import kr.co.hjsoft.dto.ReplyDTO;
import kr.co.hjsoft.service.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/replies/")
@Log4j2
@RequiredArgsConstructor
public class ReplyController {
    private final ReplyService replyService;//자동주입을 위해 final

    @GetMapping(value = "/board/{boardNUMBER}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ReplyDTO>> getListByBoard(@PathVariable("boardNUMBER") Long boardNUMBER ){
        log.info("boardNUMBER: " + boardNUMBER);

        return new ResponseEntity<>(replyService.getList(boardNUMBER), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Long> register(@RequestBody ReplyDTO replyDTO){
        log.info(replyDTO);
        log.info("replyNUMBER" + replyDTO.getReplyNUMBER());
        Long replyNUMBER = replyService.register(replyDTO);
        return new ResponseEntity<>(replyNUMBER, HttpStatus.OK);
    }

    @DeleteMapping("/{replyNUMBER}")
    public ResponseEntity<String> remove(@PathVariable("replyNUMBER") Long replyNUMBER) {
        log.info("replyNUMBER:" + replyNUMBER );
        replyService.remove(replyNUMBER);
        return new ResponseEntity("success", HttpStatus.OK);
    }


    @PutMapping("/{replyNUMBER}")
    public ResponseEntity<String> modify(@RequestBody ReplyDTO replyDTO) {
        log.info(replyDTO);
        replyService.modify(replyDTO);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

}
