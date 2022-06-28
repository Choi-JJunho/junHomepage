package junho.homepage.domain.member;

import lombok.Data;

@Data
public class MemberDto {
    private Long id;
    private String email;
    private String name;

    public MemberDto(Long id, String email, String name) {
        this.id = id;
        this.email = email;
        this.name = name;
    }
    public MemberDto(Long id) {
        this.id = id;
    }
}
