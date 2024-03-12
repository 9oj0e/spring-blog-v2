package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class BoardNativeRepository {
    private final EntityManager entityManager;

    @Transactional
    public int save(BoardRequest.SaveDTO requestDTO) {
        String q = """
                INSERT INTO board_tb (username, title, content, created_at) VALUES 
                (?, ?, ?, now())
                """;
        Query query = entityManager.createNativeQuery(q);
        query.setParameter(1, requestDTO.getUsername());
        query.setParameter(2, requestDTO.getTitle());
        query.setParameter(3, requestDTO.getContent());

        return query.executeUpdate();
    }
    public List<Board> findAll() {
        String q = """
                SELECT * FROM board_tb ORDER BY id DESC
                """;
        Query query = entityManager.createNativeQuery(q, Board.class);

        return (List<Board>) query.getResultList();
    }
    public Board findById(int id) {
        String q = """
                SELECT * FROM board_tb WHERE id = ?
                """;
        Query query = entityManager.createNativeQuery(q, Board.class);
        query.setParameter(1, id);

        return (Board) query.getSingleResult();
    }

    @Transactional
    public int updateById(int id, BoardRequest.UpdateDTO requestDTO) {
        String q = """
                UPDATE board_tb set username = ? , title = ?, content = ? WHERE id = ?
                """;
        Query query = entityManager.createNativeQuery(q);
        query.setParameter(1, requestDTO.getUsername());
        query.setParameter(2, requestDTO.getTitle());
        query.setParameter(3, requestDTO.getContent());
        query.setParameter(4, id);

        return query.executeUpdate();
    }

    @Transactional
    public int deleteById(int id) {
        String q = """
                DELETE FROM board_tb WHERE id = ?
                """;
        Query query = entityManager.createNativeQuery(q, Board.class);
        query.setParameter(1, id);

        return query.executeUpdate();
    }
}
