package junho.homepage.domain.member;

import lombok.Data;

@Data
public class CreateMemberRequest {
    private String account;
    private String password;
    private String email;
    private String name;
}
