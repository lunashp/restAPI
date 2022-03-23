package kr.co.hjsoft.dto;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class ReplyDTO {

    private Long replyNUMBER;
    private String replyCONTENT;

    private String memberNICKNAME;
    private Long boardNUMBER;

    private LocalDateTime regDATE;
    private LocalDateTime modDATE;
}