package kr.co.hjsoft.repository;

import kr.co.hjsoft.dto.MemberDTO;
import kr.co.hjsoft.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, String>, SearchBoardRepository {
    Optional<Member> findBymemberEMAIL(String memberEMAIL);
    //Optional<Member> findBymemberNICKNAME(String memberNICKNAME);
    Optional<Member> deleteBymemberEMAIL(String memberEMAIL);

}
