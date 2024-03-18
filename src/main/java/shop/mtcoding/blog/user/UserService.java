package shop.mtcoding.blog.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.blog._core.errors.exception.Exception400;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserJPARepository userJPARepository;

    @Transactional
    public void signUp(UserRequest.JoinDTO requestDTO) {
        // 유저네임 중복 검사
        Optional<User> userOp = userJPARepository.findByUsername(requestDTO.getUsername());
        if (userOp.isPresent()) {
            throw new Exception400("중복된 유저네임입니다.");
        }
        userJPARepository.save(requestDTO.toEntity());
    }
}
