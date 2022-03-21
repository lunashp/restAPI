package kr.co.hjsoft.dto;

import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Data
//DTO 클래스 와 Entity 클래스를 Generic 으로 설정
public class PageResultDTO<DTO, EN> {
    //DTO리스트
    private List<DTO> dtoList;
    //홈 페이지 번호
    private  int totalPage;
    //현재 페이지 번호
    private  int page;
    //목록 사이즈
    private int size;
    //시작 페이지 번호, 끝 페이지 번호
    private int  start, end;
    //이전, 다음
    private boolean prev, next;
    //페이지 번호 목록
    private List<Integer> pageList;

    //생성자
    public PageResultDTO(Page<EN> result, Function<EN, DTO> fn){
        //Entity로 넘어온 결과를 DTO로 변환
        dtoList = result.stream().map(fn).collect(Collectors.toList());
        //전체 페이지 개수 구하기
        totalPage = result.getTotalPages();
        //페이지 번호 목록 구하기
        makePageList(result.getPageable());
    }

    //페이지 번호 목록을 만들어 주는 메서드
    private  void makePageList(Pageable pageable){
        //현재 페이지
        this.page = pageable.getPageNumber() + 1;
        //페이지 당 출력되는 데이터 개수
        this.size = pageable.getPageSize();
        //시작 페이지번호와 종료 페이지 번호 계산
        //여기서 10은 페이지 번호 출력 개수
        //입력 받아서 변경하고자 하면 10을 변수로 변경하고 9는 변수 + 1 로 변경
        int tempEnd = (int)(Math.ceil(page/10.0)) * 10;
        start = tempEnd -9;
        prev = start >1;

        end = totalPage > tempEnd ? tempEnd : totalPage;
        next = totalPage > tempEnd;

        pageList = IntStream.rangeClosed(start,end).boxed().collect(Collectors.toList());
    }
}

