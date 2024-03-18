package shop.mtcoding.blog.board;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.blog._core.errors.exception.Exception403;
import shop.mtcoding.blog._core.errors.exception.Exception404;
import shop.mtcoding.blog.user.User;

@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardJPARepository boardJPARepository;

    @Transactional
    public void upload(BoardRequest.SaveDTO requestDTO, User sessionUser) {
        boardJPARepository.save(requestDTO.toEntity(sessionUser));
    }

    public Board updateForm(int id, int sessionUserId) {
        Board board = boardJPARepository.findById(id)
                .orElseThrow(() -> new Exception404("게시글을 찾을 수 없습니다."));
        if (sessionUserId != board.getUser().getId()) {
            throw new Exception403("게시글을 수정할 권리가 없습니다.");
        }
        return board;
    }

    @Transactional
    public void update(int boardId, int sessionUserId, BoardRequest.UpdateDTO requestDTO) {
        // 1. 조회 및 예외 처리
        Board board = boardJPARepository.findById(boardId)
                .orElseThrow(() -> new Exception404("게시물을 찾을 수 없습니다."));
        // 2. 권한 처리
        if (sessionUserId != board.getUser().getId()) {
            throw new Exception403("게시글을 수정할 권한이 없습니다.");
        }
        // 3. 글 수정
        board.setTitle(requestDTO.getTitle());
        board.setContent(requestDTO.getContent());
    }
}
