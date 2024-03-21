package shop.mtcoding.blog.board;

import lombok.Data;
import shop.mtcoding.blog.reply.Reply;
import shop.mtcoding.blog.user.User;

import java.util.ArrayList;
import java.util.List;

public class BoardResponse {
    @Data
    public static class MainDTO {
        private int id;
        private String title;

        public MainDTO(Board board) {
            this.id = board.getId();
            this.title = board.getTitle();
        }
    }

    @Data
    public static class DetailDTO {
        private int id;
        private String title;
        private String content;
        private int userId;
        private String username; // 게시글 작성자
        private List<ReplyDTO> replies = new ArrayList<>(); // 순환 참조를 막아야한다. entity를 걸지마라.
        private boolean isOwner;

        public DetailDTO(Board board, User sessionUser) {
            this.id = board.getId();
            this.title = board.getTitle();
            this.content = board.getContent();
            this.userId = board.getUser().getId();
            this.username = board.getUser().getUsername();
            if (sessionUser != null) {
                this.isOwner = userId == sessionUser.getId() ? true : false;
            }
            this.replies = board.getReplies()
                    .stream()
                    .map(reply -> new ReplyDTO(reply, sessionUser))
                    .toList();
        }

        @Data
        public class ReplyDTO {
            private int id;
            private String comment;
            private int userId; // 댓글 작성자 아이디
            private String username; // 댓글 작성자 이름
            private boolean isOwner; // 댓글 주인

            public ReplyDTO(Reply reply, User sessionUser) {
                this.id = reply.getId();
                this.comment = reply.getComment();
                this.userId = reply.getUser().getId();
                this.username = reply.getUser().getUsername();
                if (sessionUser != null) {
                    this.isOwner = userId == sessionUser.getId() ? true : false;
                }
            }
        }
    }
}
