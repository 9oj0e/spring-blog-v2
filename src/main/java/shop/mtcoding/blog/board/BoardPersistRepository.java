package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RequiredArgsConstructor
@Repository
public class BoardPersistRepository {
    private final EntityManager entityManager;

    @Transactional
    public Board save(Board board) {
        // 여기까지 비영속 객체
        entityManager.persist(board); // 이후 영속 객체

        return board; //여기 board는 callByReference. 객체의 내용은 바뀌지만, 같은 객체이다.
    }
    public List<Board> findAll() {
        Query query = entityManager.createQuery("SELECT b FROM Board b ORDER BY b.id desc", Board.class);

        return query.getResultList();
    }
    public Board findById(int id) {
        Board board = entityManager.find(Board.class, id);

        return board;
    }

    @Transactional
    public void updateById(BoardRequest.UpdateDTO requestDTO, int id) {
        Board board = findById(id);
        board.update(requestDTO);
    }


    @Transactional
    public void deleteById(int id) {
        entityManager.createQuery("DELETE FROM Board b WHERE b.id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }
}
