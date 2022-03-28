package kr.co.hjsoft.dto;

import kr.co.hjsoft.entity.Member;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberDTO implements Serializable {

    @NotBlank(message = "비밀번호는 필수 입력 값입니다.") //null 을 허용하지 않음, 적어도 white-space가 아닌 문자가 한 개 이상 포함되어야 함
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}",
            message = "비밀번호는 영문 대,소문자와 숫자, 특수기호가 적어도 1개 이상씩 포함된 8자 ~ 20자의 비밀번호여야 합니다.") //정규 표현식에 맞는 문자열이어야 함
    private String memberPW;

    private String memberNAME;

    @NotBlank(message = "이메일은 필수 입력 값 입니다")
    @Email(message = "이메일 형식에 맞지 않습니다") //email 양식 이어야 함
    private String memberEMAIL;

    private String memberPHONE;

    @NotBlank(message = "닉네임은 필수 입력 값 입니다")
    private String memberNICKNAME;

    private String memberGENDER;

    private String memberADDRESS;

    public Member toEntity() {
        return Member.builder()
                .memberNAME(memberNAME)
                .memberNICKNAME(memberNICKNAME)
                .memberPHONE(memberPHONE)
                .memberGENDER(memberGENDER)
                .memberEMAIL(memberEMAIL)
                .memberADDRESS(memberADDRESS)
                .memberPW(memberPW)
                .build();
    }
}

