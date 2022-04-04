package kr.co.hjsoft.repository;

import kr.co.hjsoft.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, String>{

//    //memberEMAIL을 Where 조건절로 하여, 데이터를 가져올 수 있도록 findBymemberEMAIL() 메소드를 정의
//    Optional<Member> findBymemberEMAIL(String memberEMAIL);
//    //memberEMAIL을 Where 조건절로 하여, 데이터를 삭제할 수 있도록 deleteBymemberEMAIL() 메소드를 정의
//    Optional<Member> deleteBymemberEMAIL(String memberEMAIL);
//
//    //로그인한 사람의 닉네임을 가져오는 메소드
//    @Query("select m from Member m where m.memberEMAIL = :memberEMAIL")
//    Object getMemberNickname(@Param("memberEMAIL") String memberEMAIL);
//
//    //email 중복검사 메소드
//    @Query("select count(m) from Member m where m.memberEMAIL = :memberEMAIL")
//    int emailCheck(@Param("memberEMAIL") String memberEMAIL);
//
//    //nickname 중복검사 메소드
//    @Query("select count(m) from Member m where m.memberNICKNAME = :memberNICKNAME")
//    int nicknameCheck(@Param("memberNICKNAME") String memberNICKNAME);

    Optional<Member> findBymemberEMAIL(String memberEMAIL);

    Optional<Member> deleteBymemberNICKNAME(String memberNICKNAME);
}
