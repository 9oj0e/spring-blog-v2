package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

@Import(BoardPersistRepository.class)
@DataJpaTest
public class BoardPersistRepositoryTest {

    @Autowired
    private BoardPersistRepository boardPersistRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    public void save_test() {
        // given
        Board board = new Board("제목5", "내용5", "ssar");

        // when
        boardPersistRepository.save(board);
        System.out.println("save_test : " + board);

        // then

    }
    @Test
    public void findAll_test() {
        // given

        // when
        List<Board> boardList = boardPersistRepository.findAll();

        // then
        System.out.println("findAll_test/size : " + boardList.size());

        // org.assertj.core.api
//        Assertions.assertThat(boardList.size()).isEqualTo(4);
//        Assertions.assertThat(boardList.get(2).getUsername()).isEqualTo("ssar");
    }

    @Test
    public void findById_test() {
        // given
        int id = 1;

        // when
        Board board = boardPersistRepository.findById(id);

        // then
        System.out.println("findById_test : " + board);
    }

    @Test
    public void updateById_test() {
        // given
        int id = 1;
        String title = "제목수정1";

        // when
        Board board = boardPersistRepository.findById(id);
        board.setTitle(title);
        entityManager.flush();

        // then 더티체킹
    }

    @Test
    public void deleteById_test() {
        // given
        int id = 1;
//        int falseId = 18; //  attempt to create delete event with null entity

        // when
        boardPersistRepository.deleteById(id);
//        boardPersistRepository.deleteById(falseId);

        // then 눈 검증 (log 확인)
        entityManager.flush();
    }
}
