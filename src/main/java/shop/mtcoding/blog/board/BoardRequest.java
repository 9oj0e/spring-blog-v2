package shop.mtcoding.blog.board;

import lombok.Data;

public class BoardRequest {

    @Data
    public class SaveDTO {
        private String username;
        private String title;
        private String content;
    }

    @Data
    public class UpdateDTO {
        private String username;
        private String title;
        private String content;
    }
}
