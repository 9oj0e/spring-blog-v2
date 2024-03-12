package shop.mtcoding.blog.board;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import shop.mtcoding.blog.util.MyDateUtil;

import java.sql.Timestamp;

@NoArgsConstructor // Entity는 무조건 기본 생성자가 필요하다.
@Data
@Entity
@Table(name = "board_tb")
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String title;
    private String content;

    @CreationTimestamp // PersistContext 에 들어갈 때, 자동으로 날짜주입
    private Timestamp createdAt;

    public Board(String username, String title, String content) {
        this.username = username;
        this.title = title;
        this.content = content;
    }

    public String getTime() {
        return MyDateUtil.timestampFormat(createdAt);
    }
}
