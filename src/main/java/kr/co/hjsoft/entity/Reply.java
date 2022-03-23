package kr.co.hjsoft.entity;


import lombok.*;

import javax.persistence.*;


@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = {"board","replywriter"})
public class Reply extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long replyNUMBER;

    @Column(length = 300, nullable = false)
    private  String replyCONTENT;

    @Column
    private String memberNICKNAME;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member replywriter;

    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;

}
