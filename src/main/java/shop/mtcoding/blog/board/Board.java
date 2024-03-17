package shop.mtcoding.blog.board;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import shop.mtcoding.blog.user.User;
import shop.mtcoding.blog._core.util.MyDateUtil;

import java.sql.Timestamp;

@NoArgsConstructor // Entity는 무조건 기본 생성자가 필요하다.
@Data
@Entity
@Table(name = "board_tb")
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String content;

//    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY) // N : 1, 앞이
    private User user; // user_id

    @CreationTimestamp // PersistContext 에 들어갈 때, 자동으로 날짜주입
    private Timestamp createdAt;

    @Builder
    public Board(int id, String title, String content, User user, Timestamp createdAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.user = user;
        this.createdAt = createdAt;
    }

    public String getTime() {
        return MyDateUtil.timestampFormat(createdAt);
    }
}
