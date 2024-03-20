package shop.mtcoding.blog.reply;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.mtcoding.blog._core.utils.ApiUtil;
import shop.mtcoding.blog.user.User;

@RequiredArgsConstructor
@RestController
public class ReplyController {
    private final HttpSession session;
    private final ReplyService replyService;

    @PostMapping("/api/replies/")
    public ResponseEntity<?> save(@RequestBody ReplyRequest.SaveDTO requestDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        Reply reply = replyService.addReply(requestDTO, sessionUser);

        return ResponseEntity.ok(new ApiUtil<>(reply));
    }

    @DeleteMapping("/api/replies/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        replyService.removeReply(id, sessionUser.getId());

        return ResponseEntity.ok(new ApiUtil<>(null));
    }
}
