package shop.mtcoding.blog.user;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import shop.mtcoding.blog._core.errors.exception.Exception400;
import shop.mtcoding.blog._core.errors.exception.Exception401;

@RequiredArgsConstructor
@Controller
public class UserController {
    private final HttpSession session;
    private final UserService userService;

    @PostMapping("/join")
    public String join(UserRequest.JoinDTO requestDTO) {
        userService.addUser(requestDTO);

        return "/user/join-form";
    }

    @PostMapping("/login")
    public String login(UserRequest.LoginDTO requestDTO) {
        User sessionUser = userService.findUser(requestDTO);
        session.setAttribute("sessionUser", sessionUser);

        return "redirect:/";
    }

    // todo: 회원정보 조회 api

    @PostMapping("/user/update")
    public String update(UserRequest.UpdateDTO requestDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        User newSessionUser = userService.modifyUser(sessionUser.getId(), requestDTO);
        session.setAttribute("sessionUser", newSessionUser);

        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout() {
        session.invalidate();

        return "redirect:/";
    }
}
