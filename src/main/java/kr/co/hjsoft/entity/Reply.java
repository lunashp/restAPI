package kr.co.hjsoft.entity;


import lombok.*;

import javax.persistence.*;


@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "board")
public class Reply extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long replyNUMBER;

    @Column(length = 300, nullable = false)
    private  String replyCONTENT;
//
//    @Column(length = 3, nullable = false)
//    private  String replySERCERET;
//
//    @Column(length = 4, nullable = false)
//    private  Long replyPASSWORD;

    @Column
    private String memberNICKNAME;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "boardNUMBER")
    private Board board;

}
