package shop.mtcoding.blog.reply;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import shop.mtcoding.blog.board.Board;
import shop.mtcoding.blog.board.BoardRequest;
import shop.mtcoding.blog.board.BoardService;
import shop.mtcoding.blog.user.User;

@RequiredArgsConstructor
@Controller
public class ReplyController {
    private final HttpSession session;
    private final ReplyService replyService;

    @PostMapping("/reply/save")
    public String save(ReplyRequest.SaveDTO requestDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        replyService.addReply(requestDTO, sessionUser);

        return "redirect:/board/" + requestDTO.getBoardId();
    }

    @PostMapping("/reply/{replyId}/delete")
    public String delete(@PathVariable int replyId, int boardId) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        replyService.removeReply(replyId, sessionUser.getId());

        return "redirect:/board/" + boardId;
    }
}
