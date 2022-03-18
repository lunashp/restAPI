package kr.co.dajsoft.hell0.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApiDTO {
    private int apino;
    private String gubun;
    private String maxclassnm;
    private String minclassnm;
    private String svcstatnm;
    private String svcnm;
    private String payatnm;
    private String placenm;
    private String usetgtinfo;
    private String svcurl;
    private String areanm;
    private String telno;
}
