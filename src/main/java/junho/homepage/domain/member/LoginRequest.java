package junho.homepage.domain.member;

import lombok.Data;

@Data
public class LoginRequest {
        private String account;
        private String password;
}
