package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.blog.user.User;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Repository
public class BoardRepository {
    private final EntityManager em;

    @Transactional
    public Board save(Board board) {
        em.persist(board);

        return board;
    }

    public List<Board> findAll() {
        Query query = em.createQuery("SELECT b from Board b ORDER BY b.id DESC", Board.class);

        return query.getResultList();
    }

    public List<Board> findAllV2() {
        String q1 = "SELECT b from Board b ORDER BY b.id DESC";
        List<Board> boardList = em.createQuery(q1, Board.class).getResultList();
        int[] userIds = boardList.stream().mapToInt(board ->
                board.getUser().getId()
        ).distinct().toArray();

        String q2 = "SELECT u FROM User u WHERE u.id IN("; // IN 쿼리를 작성시작
        /* DISTINCT 없이
        Set<Integer> userIds = new HashSet<>();
        for (Board board : boardList){
            userIds.add(board.getUser().getId());
        }
        */
        // 동적 쿼리
        for (int i = 1; i <= userIds.length; i++) {
            q2 += i;
            if (i == userIds.length) {
                q2 += ")";
            } else {
                q2 += ", ";
            }
        }

        List<User> userList = em.createQuery(q2, User.class).getResultList();
        // boardList <- userList 주입
        for (Board board : boardList)
            for (int i = 0; i < userList.size(); i++) {
                User user = userList.get(i);
                if (board.getUser().getId() == user.getId()) {
                    board.setUser(user);
                }
            }
        return boardList; // User객체가 들어간 boardList 리턴
    }

    public Board findByIdJoinUser(int id) {
        Query query = em.createQuery("SELECT b FROM Board b JOIN FETCH b.user u WHERE b.id = :id", Board.class);
        query.setParameter("id", id);

        return (Board) query.getSingleResult();
    }

    public Board findById(int id) {
        // id, title, content, user_id(이질감), created_at
        Board board = em.find(Board.class, id);

        return board;
    }

    @Transactional
    public Board updateById(int id, BoardRequest.UpdateDTO requestDTO) {
        Board board = findById(id);
        board.setTitle(requestDTO.getTitle());
        board.setContent(requestDTO.getContent());

        return board;
    }
}
