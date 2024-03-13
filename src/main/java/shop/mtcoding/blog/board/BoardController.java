package shop.mtcoding.blog.board;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class BoardController {
    private final BoardRepository boardRepository;

    @GetMapping("/board/save-form")
    public String saveForm() {
        return "/board/save-form";
    }

    @PostMapping("/board/save")
    public String save() {

        return "redirect:/";
    }

    @GetMapping({ "/", "/board" })
    public String index() {

        return "index";
    }

    @GetMapping("/board/{id}")
    public String detail(@PathVariable int id, HttpServletRequest request) {
        Board board = boardRepository.findByIdJoinUser(id);
        request.setAttribute("board", board);

        return "/board/detail";
    }

    @GetMapping("/board/{id}/update-form")
    public String updateForm(@PathVariable int id) {

        return "/board/update-form";
    }

    @PostMapping("/board/{id}/update")
    public String update(@PathVariable int id) {

        return "redirect:/board/" + id;
    }

    @PostMapping("/board/{id}/delete")
    public String delete(@PathVariable int id) {

        return "redirect:/";
    }

}
