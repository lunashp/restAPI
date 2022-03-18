package kr.co.dajsoft.hell0.entity;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@Entity
@Getter
@NoArgsConstructor
@ToString
public class Api {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int apino;
    @Column
    private String gubun;
    @Column
    private String maxclassnm;
    @Column
    private String minclassnm;
    @Column
    private String svcstatnm;
    @Column
    private String svcnm;
    @Column
    private String payatnm;
    @Column
    private String placenm;
    @Column
    private String usetgtinfo;
    @Column
    private String svcurl;
    @Column
    private String areanm;
    @Column
    private String telno;

}
