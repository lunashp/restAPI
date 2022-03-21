package kr.co.hjsoft.repository;

import kr.co.hjsoft.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, String> {
    Optional<Member> findBymemberEMAIL(String memberEMAIL);
}