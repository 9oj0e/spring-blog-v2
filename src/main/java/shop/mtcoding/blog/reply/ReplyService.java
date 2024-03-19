package shop.mtcoding.blog.reply;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.blog._core.errors.exception.Exception403;
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

    @Transactional
    public int removeReply(int replyId, int sessionUserId) {
        Reply reply = replyJPARepository.findById(replyId)
                .orElseThrow(() -> new Exception404("해당 댓글을 찾을 수 없습니다."));
        int boardId = reply.getBoard().getId();
        if (reply.getUser().getId() != sessionUserId) {
            throw new Exception403("댓글을 삭제할 권한이 없습니다.");
        }
        replyJPARepository.deleteById(replyId);
        return boardId;
    }
}
