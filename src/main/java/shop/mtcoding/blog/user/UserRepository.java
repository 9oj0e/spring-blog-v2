package shop.mtcoding.blog.user;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class UserRepository {
    private final EntityManager em;

    public User findByUsernameAndPassword(UserRequest.LoginDTO requestDTO) {
        String q = "SELECT u FROM User u WHERE u.username = :username AND u.password = :password";

        User user = em.createQuery(q, User.class)
                .setParameter("username", requestDTO.getUsername())
                .setParameter("password", requestDTO.getPassword())
                .getSingleResult();

        return user;
    }
}
