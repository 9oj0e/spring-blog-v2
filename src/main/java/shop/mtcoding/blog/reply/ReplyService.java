package shop.mtcoding.blog.reply;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.blog._core.errors.exception.Exception404;
import shop.mtcoding.blog.board.Board;
import shop.mtcoding.blog.board.BoardJPARepository;
import shop.mtcoding.blog.user.User;

@RequiredArgsConstructor
@Service
public class ReplyService {
    private final BoardJPARepository boardJPARepository;
    private final ReplyJPARepository replyJPARepository;

    @Transactional
    public void addReply(ReplyRequest.SaveDTO requestDTO, User sessionUser) {
        Board board = boardJPARepository.findById(requestDTO.getBoardId())
                        .orElseThrow(() -> new Exception404("없는 게시물입니다."));
        replyJPARepository.save(requestDTO.toEntity(sessionUser, board));
    }
}
