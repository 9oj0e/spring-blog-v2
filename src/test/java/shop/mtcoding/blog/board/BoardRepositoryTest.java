package shop.mtcoding.blog.board;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@Import(BoardRepository.class)
@DataJpaTest
public class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void findByIdJoinUser_test() {
        int id = 1;

        Board board = boardRepository.findByIdJoinUser(id);
    }

    @Test
    public void findById_test(){
        int id = 1;
        System.out.println("--1 start");
        Board board = boardRepository.findById(id);
        System.out.println("--2 FetchType.LAZY");
        System.out.println("user_id : " + board.getUser().getId());
        System.out.println("--3 FetchType.LAZY + LOADING");
        System.out.println("username : " + board.getUser().getUsername());
    }
}
