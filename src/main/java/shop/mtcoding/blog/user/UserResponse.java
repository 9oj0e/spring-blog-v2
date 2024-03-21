package shop.mtcoding.blog.user;

import lombok.Data;

public class UserResponse {
    @Data
    public static class DTO {
        private int id;
        private String username;
        private String email;

        public DTO(User user) {
            // setter로 받는 것 보다 이게 훨씬 편하다.
            this.id = user.getId();
            this.username = user.getUsername();
            this.email = user.getEmail();
        }
    }
}
