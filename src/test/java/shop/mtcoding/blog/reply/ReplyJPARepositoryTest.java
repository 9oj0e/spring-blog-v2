package shop.mtcoding.blog.reply;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
/**
 * 1. One 관계는 JOIN, Many 관계는 SELECT 한번 더. -> DTO
 * 2. Many 관계를 양방향 매핑
 * */
@DataJpaTest
public class ReplyJPARepositoryTest {
    @Autowired
    private ReplyJPARepository replyJPARepository;
}
