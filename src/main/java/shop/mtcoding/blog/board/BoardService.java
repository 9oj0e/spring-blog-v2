package shop.mtcoding.blog.board;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.blog._core.errors.exception.Exception403;
import shop.mtcoding.blog._core.errors.exception.Exception404;
import shop.mtcoding.blog.user.User;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardJPARepository boardJPARepository;

    @Transactional
    public void createBoard(BoardRequest.SaveDTO requestDTO, User sessionUser) {
        boardJPARepository.save(requestDTO.toEntity(sessionUser));
    }

    public Board updateBoardForm(int id, int sessionUserId) {
        Board board = boardJPARepository.findById(id)
                .orElseThrow(() -> new Exception404("게시글을 찾을 수 없습니다."));
        if (sessionUserId != board.getUser().getId()) {
            throw new Exception403("게시글을 수정할 권리가 없습니다.");
        }
        return board;
    }

    @Transactional
    public void updateBoard(int boardId, int sessionUserId, BoardRequest.UpdateDTO requestDTO) {
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

    @Transactional
    public void deleteBoard(int boardId, int sessionUserId) {
        Board board = boardJPARepository.findById(boardId)
                .orElseThrow(() -> new Exception404("게시글을 찾을 수 없습니다."));
        if (sessionUserId != board.getUser().getId()) {
            throw new Exception403("게시글을 삭제할 권한이 없습니다.");
        }

        boardJPARepository.deleteById(boardId);
    }

    public List<Board> getBoardList() { // 글 목록 조회
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        // 날짜 파싱.. 등등이 들어갈 것.
        return boardJPARepository.findAll(sort);
    }

    // board, isOwner
    public BoardResponse.DetailDTO getBoard(int boardId, User sessionUser) {
        Board board = boardJPARepository.findByIdJoinUser(boardId)
                .orElseThrow(() -> new Exception404("게시글을 찾을 수 없습니다"));

        return new BoardResponse.DetailDTO(board, board.getReplies() ,sessionUser);
    }
}
