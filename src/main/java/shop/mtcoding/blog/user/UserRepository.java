package shop.mtcoding.blog.user;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Repository
public class UserRepository {
    private final EntityManager em;

    @Transactional
    public User save(User user){
        em.persist(user);

        return user;
    }

    public User findById(int id){
        User user = em.find(User.class, id);

        return user;
    }

    public User findByUsernameAndPassword(UserRequest.LoginDTO requestDTO) {
        String q = "SELECT u FROM User u WHERE u.username = :username AND u.password = :password";

        User user = em.createQuery(q, User.class)
                .setParameter("username", requestDTO.getUsername())
                .setParameter("password", requestDTO.getPassword())
                .getSingleResult();

        return user;
    }

    @Transactional
    public User updateById(int id, UserRequest.UpdateDTO requestDTO){
        User user = findById(id);
        user.setPassword(requestDTO.getPassword());
        user.setEmail(requestDTO.getEmail());

        return user;
    }
}
