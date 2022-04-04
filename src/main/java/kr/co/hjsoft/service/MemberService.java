//package kr.co.hjsoft.service;
//
//import kr.co.hjsoft.dto.MemberDTO;
//import kr.co.hjsoft.dto.ReplyDTO;
//import kr.co.hjsoft.entity.Member;
//
//public interface MemberService {
//
//
//
//    //데이터 수정을 위한 메소드
//    void modify(MemberDTO memberdto);
//    //데이터 삭제를 위한 메소드
//    void delete(String memberEMAIL);
//
//    //사용자 정보 가져오는 메소드
//    MemberDTO getmember(String memberEMAIL);
//
//    //email 중복 확인
//    public int emailCheck(String memberEMAIL);
//
//    //nickname 중복 확인
//    public int nicknameCheck(String memberNICKNAME);
//
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
//
//    default Member dtoToEntity(MemberDTO memberdto){
//        Member member = Member.builder()
//                .memberADDRESS(memberdto.getMemberADDRESS())
//                .memberEMAIL(memberdto.getMemberEMAIL())
//                .memberGENDER(memberdto.getMemberGENDER())
//                .memberNAME(memberdto.getMemberNAME())
//                .memberNICKNAME(memberdto.getMemberNICKNAME())
//                .memberPHONE(memberdto.getMemberPHONE())
//                .memberPW(memberdto.getMemberPW())
//                .build();
//        return member;
//    }
//}
