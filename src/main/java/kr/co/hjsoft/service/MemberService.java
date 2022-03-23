package kr.co.hjsoft.service;

import kr.co.hjsoft.dto.MemberDTO;
import kr.co.hjsoft.entity.Member;
import org.springframework.stereotype.Service;


public interface MemberService {


    //데이터 수정을 위한 메소드
    void modify(MemberDTO memberdto);
    //데이터 삭제를 위한 메소드
    void delete(String memberEMAIL);


    //MemberDTO 를 Reply Entity로 변환해주는 메서드
//    default Member dtoToEntity(MemberDTO memberdto){
//        Member member = Member.builder()
//                .memberNICKNAME(memberdto.getMemberNICKNAME())
//                .memberEMAIL(memberdto.getMemberEMAIL())
//                .memberADDRESS(memberdto.getMemberADDRESS())
//                .memberGENDER(memberdto.getMemberGENDER())
//                .memberPW(memberdto.getMemberPW())
//                .memberNAME(memberdto.getMemberNAME())
//                .memberPHONE(memberdto.getMemberPHONE())
//                .build();
//        return member;
//    }
////
////    //Member Entity를 ReplyDTO로 변환해주는 메서드
//    default MemberDTO entityToDTO(Member member){
//        MemberDTO memberdto = MemberDTO.builder()
//                .memberADDRESS(member.getMemberADDRESS())
//                .memberEMAIL(member.getMemberEMAIL())
//                .memberGENDER(member.getMemberGENDER())
//                .memberNAME(member.getMemberNAME())
//                .memberNICKNAME(member.getMemberNICKNAME())
//                .memberPHONE(member.getMemberPHONE())
//                .memberPW(member.getMemberPW())
//                .build();
//        return memberdto;
//    }
}
