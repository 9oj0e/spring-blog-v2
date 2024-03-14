package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class BoardRepository {
    private final EntityManager em;

    public List<Board> findAll() {
        Query query = em.createQuery("SELECT b from Board b ORDER BY b.id DESC", Board.class);

        return query.getResultList();
    }

    public Board findByIdJoinUser(int id) {
        Query query = em.createQuery("SELECT b FROM Board b JOIN FETCH b.user u WHERE b.id = :id", Board.class);
        query.setParameter("id", id);

        return (Board) query.getSingleResult();
    }

    public Board findById(int id){
        // id, title, content, user_id(이질감), created_at
        Board board = em.find(Board.class, id);

        return board;
    }
}
