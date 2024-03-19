package shop.mtcoding.blog.reply;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import shop.mtcoding.blog.board.Board;
import shop.mtcoding.blog.board.BoardJPARepository;
/**
 * 1. One 관계는 JOIN, Many 관계는 SELECT 한번 더. -> DTO
 * 2. Many 관계를 양방향 매핑
 * */
@DataJpaTest
public class ReplyJPARepositoryTest {
    @Autowired
    private ReplyJPARepository replyJPARepository;
    @Autowired
    private BoardJPARepository boardJPARepository;

    @Test
    public void saveV2_test() {
        // given
        Board board = boardJPARepository.findById(1).get();
        // when

        // then
        System.out.println("save_test, id : " + board.getId());
    }
}
