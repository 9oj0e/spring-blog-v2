package shop.mtcoding.blog.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

// 자동 Compound scan이 이루어진다. @Repository 주석을 달아줄 필요가 없다.
public interface UserJPARepository extends JpaRepository<User, Integer> {
    // 앞으로 UserJPARepository 상속받는 모든 것은
    // JPARepository에 이미 구현되어있는 모든 CRUD 메서드를 다 자동 구현.

    Optional<User> findByUsernameAndPassword(@Param("username") String username, @Param("password")String password);
    // JPA Query Methods
    // camel표기법에서 대문자로 바뀌는 부분을 알아서 WHERE절에 넣고, PARAM부분을 ?로 만들어서 받아온다.
    Optional<User> findByUsername(@Param("username")String username);
}