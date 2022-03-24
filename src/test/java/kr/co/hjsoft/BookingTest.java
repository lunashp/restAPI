package kr.co.hjsoft;

import kr.co.hjsoft.entity.Board;
import kr.co.hjsoft.entity.Member;
import kr.co.hjsoft.repository.BoardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

@SpringBootTest
public class BookingTest {
    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void insertboard(){
        IntStream.rangeClosed(1,100).forEach(i -> {
            Member member = Member.builder().memberNICKNAME("www").build();
            Board board = Board.builder()
                    .boardTITLE("title" + i)
                    .boardCONTENT("content" + i)
                    .writer(member)
                    .boardNICKNAME("www")
                    .build();

            boardRepository.save(board);
        });
    }

//    @Test
    public  void deleteboard(){
        boardRepository.deleteAll();
    }
}
