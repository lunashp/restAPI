package kr.co.dajsoft.hell0.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardDTO {

 private int boardNUMBER;
 private String boardTITLE;
 private String  boardCONTENT;
 private int boardREADCNT;
 private String boardNICKNAME;

 private String memberNICKNAME;
 //작성된 날짜와 수정된 날짜
 private LocalDateTime regDATE;
 private LocalDateTime modDATE;
 //댓글의 수 표시
 private int replyCount;

}
