package kr.co.hjsoft.service;

import kr.co.hjsoft.dto.ReplyDTO;
import kr.co.hjsoft.entity.Board;
import kr.co.hjsoft.entity.Reply;
import kr.co.hjsoft.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReplyServiceImpl implements  ReplyService{

    private final ReplyRepository replyRepository;

    @Override
    public Long register(ReplyDTO replyDTO) {
        Reply reply = dtoToEntity(replyDTO);
        replyRepository.save(reply);
        return reply.getReplyNUMBER();
    }

    @Override
    public void modify(ReplyDTO replyDTO) {
        Reply reply = dtoToEntity(replyDTO);
        replyRepository.save(reply);
    }

    @Override
    public void remove(Long replyNUMBER) {
        replyRepository.deleteById(replyNUMBER);
    }

    @Override
    public List<ReplyDTO> getList(Long boardNUMBER) {

        List<Reply> result =
                replyRepository.getRepliesByBoardOrderByReplyNUMBER(
                        Board.builder().boardNUMBER(boardNUMBER).build());
        //댓글 정렬하기
        result.sort(new Comparator<Reply>() {
            @Override
            public int compare(Reply o1, Reply o2) {
                return o2.getModDate().compareTo(o1.getModDate());
            }
        });

        return result.stream()
                .map(reply -> entityToDTO(reply))
                .collect(Collectors.toList());
    }
}
