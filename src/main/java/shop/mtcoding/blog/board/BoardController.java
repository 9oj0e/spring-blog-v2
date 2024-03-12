package shop.mtcoding.blog.board;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BoardController {
    private final BoardNativeRepository boardNativeRepository;

    @GetMapping("/board/save-form")
    public String saveForm() {
        return "/board/save-form";
    }

    @PostMapping("/board/save")
    public String save(BoardRequest.SaveDTO requestDTO) {
        boardNativeRepository.save(requestDTO);

        return "index";
    }

    @GetMapping({ "/", "/board" })
    public String index(HttpServletRequest request) {
        List<Board> boardList = boardNativeRepository.findAll();
        request.setAttribute("boardList", boardList);

        return "index";
    }

    @GetMapping("/board/{id}")
    public String detail(@PathVariable int id, HttpServletRequest request) {
        Board board = boardNativeRepository.findById(id);
        request.setAttribute("board", board);

        return "/board/detail";
    }

    @GetMapping("/board/{id}/update-form")
    public String updateForm(@PathVariable int id, HttpServletRequest request) {
        Board board = boardNativeRepository.findById(id);
        request.setAttribute("board", board);

        return "/board/update-form";
    }

    @PostMapping("/board/{id}/update")
    public String update(int id, BoardRequest.UpdateDTO requestDTO) {
        boardNativeRepository.updateById(id, requestDTO);

        return "/board/" + id;
    }

    @PostMapping("/board/{id}/delete")
    public String delete(int id) {
        boardNativeRepository.deleteById(id);

        return "index";
    }

}
