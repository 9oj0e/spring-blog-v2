package shop.mtcoding.blog.board;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@Import({BoardPersistRepository.class, BoardRequest.class})
@DataJpaTest
public class BoardPersistRepositoryTest {

    @Autowired
    private BoardPersistRepository boardPersistRepository;

    @Test
    public void save_test() {
        // given
        Board board = new Board("제목5", "내용5", "ssar");

        // when
        boardPersistRepository.save(board);
        System.out.println("save_test : " + board);

        // then

    }
    public void findAll_test() {
        // given

        // when

        // then
        // org.assertj.core.api
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

    public void updateById_test() {
        // given

        // when

        // then
    }

    @Test
    public void deleteById_test() {
        // given

        // when

        // then
    }
}
