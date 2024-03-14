package shop.mtcoding.blog.user;

import lombok.Data;

public class UserRequest {
    @Data
    public class LoginDTO {
        private String username;
        private String password;
    }
}
