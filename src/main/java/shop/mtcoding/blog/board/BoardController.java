package shop.mtcoding.blog.board;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import shop.mtcoding.blog._core.utils.ApiUtil;
import shop.mtcoding.blog.user.User;

@RequiredArgsConstructor
@Controller
public class BoardController {
    private final HttpSession session;
    private final BoardService boardService;

    @PostMapping("/api/boards")
    public ResponseEntity<?> save(@RequestBody BoardRequest.SaveDTO requestDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        Board board = boardService.addBoard(requestDTO, sessionUser);

        return ResponseEntity.ok(new ApiUtil<>(board));
    }

    // todo : 글 목록 조회 api 필요    @GetMapping("/")


    // todo : 글 상세보기 api 필요    @GetMapping("/api/boards/{id}/detail")


    // todo : 글 조회 api 필요 (update-form) @GetMapping("/api/boards/{id}")


    @PutMapping("/api/boards/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody BoardRequest.UpdateDTO requestDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        Board board = boardService.modifyBoard(id, sessionUser.getId(), requestDTO);

        return ResponseEntity.ok(new ApiUtil<>(board));
    }

    @DeleteMapping("/api/boards/{id}")
    public String delete(@PathVariable int id) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        boardService.removeBoard(id, sessionUser.getId());

        return "redirect:/";
    }
}
