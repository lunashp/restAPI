package kr.co.hjsoft.service;

import kr.co.hjsoft.dto.BoardDTO;
import kr.co.hjsoft.dto.MemberDTO;
import kr.co.hjsoft.dto.PageRequestDTO;
import kr.co.hjsoft.dto.PageResultDTO;
import kr.co.hjsoft.entity.Board;
import kr.co.hjsoft.entity.Member;
import kr.co.hjsoft.repository.BoardRepository;
import kr.co.hjsoft.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Log4j2
public class BoardServiceImpl implements BoardService{

    private final BoardRepository boardRepository;

    @Override
    public Long register(BoardDTO dto) {
        //등록을 위해서 Entity 객체로 변환
        Board board = dtoToEntity(dto);
        boardRepository.save(board);
        return board.getBoardNUMBER();
    }


    @Override
    public PageResultDTO<BoardDTO, Object[]> getList(PageRequestDTO dto) {
        Page<Object []> result = boardRepository.searchPage(
                dto.getType(), dto.getKeyword(),
                dto.getPageable(Sort.by("boardNUMBER").descending())
        );

        Function<Object[], BoardDTO> fn = (
                en -> entityToDTO((Board)en[0],
                        (Member)en[1],
                        (Long)en[2]));
        return new PageResultDTO<>(result, fn);
    }

    @Override
    public BoardDTO get(Long boardNUMBER) {

        Object result= boardRepository.getBoardByBno(boardNUMBER);
        Object [] ar = (Object []) result;
        return entityToDTO((Board)ar[0], (Member)ar[1], (Long)ar[2]);

    }

    @Override
    public MemberDTO getmember(String memberEMAIL) {
        Object member = boardRepository.getMemberNickname(memberEMAIL);

        return memberentityToDTO((Member) member);
    }

    private final ReplyRepository replyRepository;

    @Transactional
    @Override
    public void removeWithReplies(Long boardNUMBER) {
        replyRepository.deleteByBoardNUMBER(boardNUMBER);
        boardRepository.deleteById(boardNUMBER);
    }

    @Transactional
    @Override
    public void modify(BoardDTO dto) {
        Optional<Board> board = boardRepository.findById(dto.getBoardNUMBER());
        if(board.isPresent()){
            board.get().changeTitle(dto.getBoardTITLE());
            board.get().changeContent(dto.getBoardCONTENT());

            boardRepository.save(board.get());
        }
    }
}



