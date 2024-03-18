package shop.mtcoding.blog.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

// @Import도 필요 없다. JPARepository를 JPATest에서 띄운다.
@DataJpaTest
public class UserJPARepositoryTest {
    @Autowired
    private UserJPARepository userJPARepository;
    @Autowired
    private EntityManager em;

    @Test
    public void save_test() {
        // given
        User user = User.builder()
                .username("happy")
                .password("1234")
                .email("happy@nate.com")
                .build();
        // when
        userJPARepository.save(user);
        // then
        // 그냥 눈으로 확인
    }

    @Test
    public void findById_test() {
        // given
        int id = 5;
        // when
        Optional<User> userOp = userJPARepository.findById(id);
        // then
        if (userOp.isPresent()) { // Optional -> Null 예외처리를 위한 자료형.
            User user = userOp.get();
            System.out.println("findById_test : " + user.getUsername());
        }
    }

    @Test
    public void findAll_test() throws JsonProcessingException {
        // given
        // findAll은 order by 하지 않음. 별도의 Sort 선언이 필요하다.
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        // when
        List<User> userList = userJPARepository.findAll(sort);
        // then
        System.out.println("findAll_test : " + userList);
    }

    @Test
    public void findByUsernameAndPassword_test() {
        // given
        String username = "ssar";
        String password = "1234";
        // when
        Optional<User> userOp = userJPARepository.findByUsernameAndPassword(username, password);
        // then
        System.out.println("findByUsernameAndPassword_test : " + userOp.get());
    }

    @Test
    public void deleteById_test() {
        // given
        int id = 1;
        // when
        userJPARepository.deleteById(id);
        // then
        em.flush();
    }
}
