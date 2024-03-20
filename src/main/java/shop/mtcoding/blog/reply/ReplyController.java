package shop.mtcoding.blog.reply;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
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

    @PostMapping("/api/replies/")
    public ResponseEntity<?> save(@RequestBody ReplyRequest.SaveDTO requestDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        Reply reply = replyService.addReply(requestDTO, sessionUser);

        return ResponseEntity.ok(reply);
    }

    @DeleteMapping("/api/replies/{id}")
    public ResponseEntity<?> delete(@PathVariable int id, @RequestBody int boardId) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        replyService.removeReply(id, sessionUser.getId());

        return ResponseEntity.ok(null);
    }
}
