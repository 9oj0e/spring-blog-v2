package shop.mtcoding.blog.board;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import shop.mtcoding.blog._core.errors.exception.Exception403;
import shop.mtcoding.blog.user.User;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BoardController {
    private final HttpSession session;
    private final BoardRepository boardRepository;
    private final BoardService boardService;

    @GetMapping("/board/save-form")
    public String saveForm() {
        return "/board/save-form";
    }

    @PostMapping("/board/save")
    public String save(BoardRequest.SaveDTO requestDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        boardService.createBoard(requestDTO, sessionUser);

        return "redirect:/";
    }

    @GetMapping({"/", "/board"})
    public String index(HttpServletRequest request) {
        List<Board> boardList = boardService.getBoardList();
        request.setAttribute("boardList", boardList);

        return "index";
    }

    @GetMapping("/board/{id}")
    public String detail(@PathVariable Integer id, HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        BoardResponse.DetailDTO boardDetail = boardService.getBoard(id, sessionUser);
        request.setAttribute("boardDetail", boardDetail);

        return "board/detail";
    }

    @GetMapping("/board/{id}/update-form")
    public String updateForm(@PathVariable int id, HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        Board board = boardService.updateBoardForm(id, sessionUser.getId());
        request.setAttribute("board", board);

        return "/board/update-form";
    }

    @PostMapping("/board/{id}/update")
    public String update(@PathVariable int id, BoardRequest.UpdateDTO requestDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        boardService.updateBoard(id, sessionUser.getId(), requestDTO);

        return "redirect:/board/" + id;
    }

    @PostMapping("/board/{id}/delete")
    public String delete(@PathVariable int id) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        boardService.deleteBoard(id, sessionUser.getId());

        return "redirect:/";
    }
}
