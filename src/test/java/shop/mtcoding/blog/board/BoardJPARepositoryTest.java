package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Sort;
import shop.mtcoding.blog.reply.Reply;
import shop.mtcoding.blog.reply.ReplyJPARepository;
import shop.mtcoding.blog.user.User;

import java.util.List;
import java.util.Optional;

@DataJpaTest
public class BoardJPARepositoryTest {
    @Autowired
    private BoardJPARepository boardJPARepository;
    @Autowired
    private EntityManager em;

    @Test
    public void save_test() {
        // given
        User sessionUser = User.builder().id(1).build();
        Board board = Board.builder()
                .title("제목5")
                .content("내용5")
                .user(sessionUser)
                .build();
        // when
        boardJPARepository.save(board);
        // then
        System.out.println("save_test, id : " + board.getId());
    }

    @Test
    public void findById_test() {
        // given
        int id = 1;

        // when
        Optional<Board> boardOp = boardJPARepository.findById(id);
        // then
        if (boardOp.isPresent()) {
            Board board = boardOp.get();
            System.out.println("findById_test : " + board);
        }
    }

    @Test
    public void findByIdJoinUser_test() {
        // given
        int id = 1;
        // when
        Optional<Board> boardOp = boardJPARepository.findByIdJoinUser(id);
        // then
        if (boardOp.isPresent()) {
            Board board = boardOp.get();
            System.out.println(board);
        }
    }

    @Test
    public void findByIdJoinUserAndReplies_test() {
        // given
        int id = 4;
        // when
        Board board = boardJPARepository.findByIdJoinUser(id).get();
        // then
    }

    @Test
    public void findAllWithReplyCount_test(){
        // given

        // when
        List<BoardResponse.WithCountDTO> boardCountDTOList = boardJPARepository.findAllWithReplyCount();
        // then
        System.out.println(boardCountDTOList);
    }

    // findAll
    @Test
    public void findAll_test() {
        // given
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        // when
        List<Board> boardList = boardJPARepository.findAll(sort);
        // then
        System.out.println("findAll_test : " + boardList);
    }

    @Test
    public void deleteById_test() {
        // given
        int id = 1;
        // when
        boardJPARepository.deleteById(id);
        // then
        em.flush();
    }
}
