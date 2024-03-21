package shop.mtcoding.blog.board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BoardJPARepository extends JpaRepository<Board, Integer> {

    @Query("SELECT new shop.mtcoding.blog.board.BoardResponse$WithCountDTO(b.id, b.title, b.content, b.user.id, (SELECT COUNT(r.id) FROM Reply r where r.board.id = b.id)) FROM Board b")
    List<BoardResponse.WithCountDTO> findAllWithReplyCount();
    @Query("SELECT b FROM Board b JOIN FETCH b.user u WHERE b.id = :id")
    Optional<Board> findByIdJoinUser(@Param("id") int id);
}
