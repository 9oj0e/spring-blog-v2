package shop.mtcoding.blog.board;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import shop.mtcoding.blog.user.User;

@RequiredArgsConstructor
@Controller
public class BoardController {
    private final HttpSession session;
    private final BoardService boardService;

    @PostMapping("/api/boards")
    public String save(BoardRequest.SaveDTO requestDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        boardService.addBoard(requestDTO, sessionUser);

        return "redirect:/";
    }

    // todo : 글 목록 조회 api 필요    @GetMapping("/")


    // todo : 글 상세보기 api 필요    @GetMapping("/api/boards/{id}/detail")


    // todo : 글 조회 api 필요 (update-form) @GetMapping("/api/boards/{id}")


    @PutMapping("/api/boards/{id}")
    public String update(@PathVariable int id, BoardRequest.UpdateDTO requestDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        boardService.modifyBoard(id, sessionUser.getId(), requestDTO);

        return "redirect:/board/" + id;
    }

    @DeleteMapping("/api/boards/{id}")
    public String delete(@PathVariable int id) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        boardService.removeBoard(id, sessionUser.getId());

        return "redirect:/";
    }
}
