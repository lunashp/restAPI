package kr.co.hjsoft.repository;

import kr.co.hjsoft.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, String> {
    Optional<Member> findBymemberEMAIL(String memberEMAIL);
    //Optional<Member> finBymemberNICKNAME(String memberNICKNAME);
}
