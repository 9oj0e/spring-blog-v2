package shop.mtcoding.blog.board;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

@Import(BoardNativeRepository.class)
@DataJpaTest
public class BoardNativeRepositoryTest {

    @Autowired
    private BoardNativeRepository boardNativeRepository;

    @Test
    public void findAllTest() {
        // given

        // when
        List<Board> boardList = boardNativeRepository.findAll();

        // then
        System.out.println("findAll_test/size : " + boardList.size());
    }
}
