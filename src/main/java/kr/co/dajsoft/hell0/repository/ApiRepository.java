package kr.co.dajsoft.hell0.repository;

import kr.co.dajsoft.hell0.entity.Api;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ApiRepository extends JpaRepository<Api, Long>,SearchBoardRepository {
    @Query(value = "select a, count(a) from Api a",
            countQuery = "select count(a) from Api a")
    Page<Object []> getApiCount(Pageable pageable);

    //게시글 상세보기를 위한 메서드
    @Query("select a, count(a) from Api a where a.apino = :apino")
    Object getApiByBno(@Param("apino") Long apino);
}
