package shop.mtcoding.blog.board;

import lombok.Data;
import shop.mtcoding.blog.reply.Reply;
import shop.mtcoding.blog.user.User;

import java.util.List;

public class BoardResponse {
    @Data
    public static class DetailDTO {

        private int id;
        private String title;
        private String content;
        private UserDTO user;
        private List<Reply> replies;
        private boolean isOwner;

        @Data
        private class UserDTO {
            private int id;
            private String username;
            public UserDTO(User user) {
                this.id = user.getId();
                this.username = user.getUsername();
            }
        }

        public DetailDTO(Board board, List<Reply> replies, User sessionUser) {
            this.id = board.getId();
            this.title = board.getTitle();
            this.content = board.getContent();
            this.replies = replies;
            this.user = new UserDTO(board.getUser());

            this.isOwner = false;
            // 로그인을 하고, 게시글의 주인이면 isOwner가 true가 된다.
            if (sessionUser != null){
                if (sessionUser.getId() == board.getUser().getId()) {
                    isOwner = true;
                }
            }
        }

        public DetailDTO(Board board, List<Reply> replies) {
            this.id = board.getId();
            this.title = board.getTitle();
            this.content = board.getContent();
            this.replies = replies;
            this.user = new UserDTO(board.getUser());
        }
    }
}
