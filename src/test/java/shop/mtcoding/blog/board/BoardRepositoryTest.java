package shop.mtcoding.blog.board;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

@Import(BoardRepository.class)
@DataJpaTest
public class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void randomQuery_test() {
        int[] ids = {1, 2, 3};

        String q = "SELECT u FROM User u where u.id in(";
        // SELECT u FROM User u WHERE u.id IN(:id1, :id2, :id3);
        for (int i = 1; i <= ids.length; i++) {
            q += ":id" + i;
            if (i == ids.length){
                q += ")";
            } else {
                q += ", ";
            }
        }
        System.out.println("q: " + q);
        // SELECT u FROM User u WHERE u.id IN (1, 2, 3);
        String q2;
        q2 = q
                .replace(":id1", "" + 1 + "")
                .replace(":id2", "" + 2 + "")
                .replace(":id3", "" + 3 + "");

        System.out.println("q2: " + q2);

    }

    @Test
    public void findAll_lazyloading_test() {
        // given
        // when
        List<Board> boardList = boardRepository.findAll();
        boardList.forEach(board -> {
            System.out.println(board.getUser().getUsername());
        });
        // then
    }

    @Test
    public void findAll_custom_inquery_test() {
        List<Board> boardList = boardRepository.findAll();

        int[] userIds = boardList.stream().mapToInt(board ->
            board.getUser().getId()
        ).distinct().toArray();
        for (int i : userIds) {
            System.out.println(i);
        }
        // SELECT * FROM user_tb WHERE id IN (3,2,1,1);
        // distinct() -> SELECT * FROM user_tb WHERE id IN (3,2,1);
    }

    @Test
    public void findAll_test() {
        // given

        // when
        boardRepository.findAll();
        // then

    }

    @Test
    public void findAllV2_test() {
        // given

        // when
        List<Board> boardList = boardRepository.findAllV2();
        // then
        System.out.println(boardList);
    }

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
