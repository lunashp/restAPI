package kr.co.hjsoft;

import kr.co.hjsoft.dto.MemberDTO;
import kr.co.hjsoft.entity.Member;
import kr.co.hjsoft.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class MemberTest {
    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void insertMember() {
        for (int i = 1; i <= 3; i++) {
            Member member = Member.builder()
                    .memberEMAIL("user" + i + "@gmail.com")
                    .memberNAME("사용자" + i)
                    .memberADDRESS("서울")
                    .memberGENDER("여")
                    .memberPW("1106")
                    .memberPHONE("01012345678")
                    .memberNICKNAME("user" + i)
                    .build();
            memberRepository.save(member);

        }
    }

    //@Test
    public void updateMember() {
        MemberDTO memberdto = MemberDTO.builder()
                .memberADDRESS("서울")
                .memberEMAIL("aaa@naver.com")
                .memberGENDER("남성")
                .memberNAME("유저")
                .memberPW("1234")
                .memberPHONE("01011111111")
                .build();

        Optional<Member> member = memberRepository.findBymemberEMAIL(memberdto.getMemberEMAIL());
        if (member.isPresent()) {
            member.get().changeName(memberdto.getMemberNAME());
            member.get().changePw(memberdto.getMemberPW());
            member.get().changeGender(memberdto.getMemberGENDER());
            member.get().changePhone(memberdto.getMemberPHONE());
            member.get().changeAddress(memberdto.getMemberADDRESS());

            memberRepository.save(member.get());

        }
    }
}
