package shop.mtcoding.blog.user;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.mtcoding.blog._core.utils.ApiUtil;

@RequiredArgsConstructor
@RestController
public class UserController {
    private final HttpSession session;
    private final UserService userService;

    @PostMapping("/join")
    public ResponseEntity<?> join(@RequestBody UserRequest.JoinDTO requestDTO) {
        User user = userService.addUser(requestDTO);

        return ResponseEntity.ok(new ApiUtil<>(user));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserRequest.LoginDTO requestDTO) {
        User sessionUser = userService.findUser(requestDTO);
        session.setAttribute("sessionUser", sessionUser);

        return ResponseEntity.ok(new ApiUtil<>(null));
    }

    @GetMapping("/api/users/{id}")
    public ResponseEntity<?> updateForm(@PathVariable Integer id) {
        User user = userService.findUser(id);

        return ResponseEntity.ok(new ApiUtil<>(user));
    }

    @PutMapping("/api/users/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody UserRequest.UpdateDTO requestDTO) {
        // 여기서 id는 필요없다. 하지만 가독성을 높이기 위해서, 넣는다.
        User sessionUser = (User) session.getAttribute("sessionUser");
        User newSessionUser = userService.modifyUser(sessionUser.getId(), requestDTO);
        session.setAttribute("sessionUser", newSessionUser);

        return ResponseEntity.ok(new ApiUtil<>(newSessionUser));
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logout() {
        session.invalidate();

        return ResponseEntity.ok(new ApiUtil<>(null));
    }
}
