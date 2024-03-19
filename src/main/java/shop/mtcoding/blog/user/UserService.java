package shop.mtcoding.blog.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.blog._core.errors.exception.Exception400;
import shop.mtcoding.blog._core.errors.exception.Exception401;
import shop.mtcoding.blog._core.errors.exception.Exception404;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserJPARepository userJPARepository;

    @Transactional
    public void signUp(UserRequest.JoinDTO requestDTO) {
        // 유저네임 중복 검사
        Optional<User> userOp = userJPARepository.findByUsername(requestDTO.getUsername());
        if (userOp.isPresent()) { // 존재하면 안된다.
            throw new Exception400("중복된 유저네임입니다.");
        }
        userJPARepository.save(requestDTO.toEntity());
    }

    public User login(UserRequest.LoginDTO requestDTO) {
        // hash 검사가 추가될 것.
        User sessoinUser = userJPARepository
                .findByUsernameAndPassword(requestDTO.getUsername(), requestDTO.getPassword())
                .orElseThrow(() -> new Exception401("인증되지 않았습니다."));

        return sessoinUser;
    }

    public User updateForm(int id) {
        return userJPARepository.findById(id)
                .orElseThrow(() -> new Exception404("찾을 수 없는 계정입니다"));
    }

    @Transactional
    public User update(int id, UserRequest.UpdateDTO requestDTO) {
        User user = userJPARepository.findById(id)
                .orElseThrow(() -> new Exception404("회원정보를 찾을 수 없습니다."));
        user.setPassword(requestDTO.getPassword());
        user.setEmail(requestDTO.getEmail());

        return user;
    }
}
