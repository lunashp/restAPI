package kr.co.hjsoft.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@AllArgsConstructor
//@Builder
public class Member extends BaseEntity {
    @Id
    private String memberNICKNAME;

    @Column(length = 200, nullable = false)
    private String memberPW;

    @Column(length = 30, nullable = false)
    private String memberNAME;

    @Column(length = 200, nullable = false)
    private String memberEMAIL;

    @Column(length = 11, nullable = false)
    private String memberPHONE;

    @Column(length = 3, nullable = false)
    private String memberGENDER;

    @Column(length = 600)
    private String memberADDRESS;


    @Builder
    public Member(String memberADDRESS, String memberEMAIL, String memberGENDER, String memberNAME, String memberNICKNAME, String memberPHONE, String memberPW) {
        this.memberADDRESS = memberADDRESS;
        this.memberEMAIL = memberEMAIL;
        this.memberGENDER = memberGENDER;
        this.memberNAME = memberNAME;
        this.memberNICKNAME = memberNICKNAME;
        this.memberPHONE = memberPHONE;
        this.memberPW = memberPW;
    }

    //수정 메소드
    public void changeName(String name){this.memberNAME = name;}
    public void changeEmail(String email){this.memberEMAIL = email;}
    public void changePw(String pw){this.memberPW = pw;}
    public void changeGender(String gender){this.memberGENDER = gender;}
    public void changePhone(String phone){this.memberPHONE = phone;}
    public void changeAddress(String address){this.memberADDRESS = address;}

}
