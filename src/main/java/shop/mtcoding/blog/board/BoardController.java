package shop.mtcoding.blog.board;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.mtcoding.blog._core.utils.ApiUtil;
import shop.mtcoding.blog.user.User;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BoardController {
    private final HttpSession session;
    private final BoardService boardService;

    @PostMapping("/api/boards")
    public ResponseEntity<?> save(@RequestBody BoardRequest.SaveDTO requestDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        Board board = boardService.addBoard(requestDTO, sessionUser);

        return ResponseEntity.ok(new ApiUtil<>(board));
    }

    @GetMapping("/")
    public ResponseEntity<?> index() {
        List<BoardResponse.MainDTO> boardList = boardService.findBoardList();

        return ResponseEntity.ok(new ApiUtil<>(boardList));
    }

    @GetMapping("/api/boards/{id}/detail")
    public ResponseEntity<?> detail(@PathVariable Integer id) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        BoardResponse.DetailDTO responseDTO = boardService.findBoard(id, sessionUser);

        return ResponseEntity.ok(new ApiUtil<>(responseDTO));
    }

    @GetMapping("/api/boards/{id}")
    public ResponseEntity<?> updateForm(@PathVariable Integer id) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        Board board = boardService.findBoard(id, sessionUser.getId());

        return ResponseEntity.ok(new ApiUtil<>(board));
    }


    @PutMapping("/api/boards/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody BoardRequest.UpdateDTO requestDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        Board board = boardService.modifyBoard(id, sessionUser.getId(), requestDTO);

        return ResponseEntity.ok(new ApiUtil<>(board));
    }

    @DeleteMapping("/api/boards/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        boardService.removeBoard(id, sessionUser.getId());

        return ResponseEntity.ok(new ApiUtil<>(null));
    }
}
