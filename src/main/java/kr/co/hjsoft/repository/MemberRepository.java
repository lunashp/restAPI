package kr.co.hjsoft.repository;

import kr.co.hjsoft.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, String>, SearchBoardRepository {
    Optional<Member> findBymemberEMAIL(String memberEMAIL);
    Optional<Member> deleteBymemberEMAIL(String memberEMAIL);

    //로그인한 사람의 닉네임을 가져오는 메소드
    @Query("select m from Member m where m.memberEMAIL = :memberEMAIL")
    Object getMemberNickname(@Param("memberEMAIL") String memberEMAIL);

    @Query("select count(m) from Member m where m.memberEMAIL = :memberEMAIL")
    int emailCheck(@Param("memberEMAIL") String memberEMAIL);

    @Query("select count(m) from Member m where m.memberNICKNAME = :memberNICKNAME")
    int nicknameCheck(@Param("memberNICKNAME") String memberNICKNAME);
}
