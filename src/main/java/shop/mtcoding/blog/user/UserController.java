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
    private final UserRepository userRepository;

    @GetMapping("/join-form")
    public String joinForm() {
        return "/user/join-form";
    }

    @PostMapping("/join")
    public String join(UserRequest.JoinDTO requestDTO) {
        try {
            userRepository.save(requestDTO.toEntity());
        } catch (DataIntegrityViolationException e) {
            throw new Exception400("동일한 유저네임이 존재합니다.");
        }

        return "/user/join-form";
    }

    @GetMapping("/login-form")
    public String loginForm() {
        return "/user/login-form";
    }

    @PostMapping("/login")
    public String login(UserRequest.LoginDTO requestDTO) {
        try {
            User sessionUser = userRepository.findByUsernameAndPassword(requestDTO);
            session.setAttribute("sessionUser", sessionUser);

            return "redirect:/";
        } catch (EmptyResultDataAccessException e) {
            throw new Exception401("아이디 혹은 비밀번호가 틀렸습니다");
        }
    }

    @GetMapping("/user/update-form")
    public String updateForm(HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        User user = userRepository.findById(sessionUser.getId());
        request.setAttribute("user", user);

        return "/user/update-form";
    }

    @PostMapping("/user/update")
    public String update(UserRequest.UpdateDTO requestDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        User newSessionUser = userRepository.updateById(sessionUser.getId(), requestDTO);
        session.setAttribute("sessionUser", newSessionUser);

        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout() {
        session.invalidate();

        return "redirect:/";
    }
}
