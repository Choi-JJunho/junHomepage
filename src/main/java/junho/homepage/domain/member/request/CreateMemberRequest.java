package junho.homepage.domain.member.request;

import lombok.Data;

@Data
public class CreateMemberRequest {
    private String account;
    private String password;
    private String email;
    private String name;
}
