package kr.co.dajsoft.hell0.dto;

import kr.co.dajsoft.hell0.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReplyDTO {

    private Long replyNUMBER;
    private  String memberNICKNAME;
    private  String replyCONTENT;

//    private  String replySERCERET;
//    private  Long replyPASSWORD;

    private Long boardNUMBER;
    private LocalDateTime regDate;
    private LocalDateTime modDate;
}