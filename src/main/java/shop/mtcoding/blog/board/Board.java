package shop.mtcoding.blog.board;

import jakarta.persistence.*;
import lombok.Data;
import shop.mtcoding.blog.util.MyDateUtil;

import java.sql.Timestamp;

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
    private Timestamp createdAt;

    public String getTime() {
        return MyDateUtil.timestampFormat(createdAt);
    }
}
