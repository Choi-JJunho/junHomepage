package junho.homepage.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String account;
    private String password;
    private String email;
    private String name;

    private LocalDateTime createdAt;
    private LocalDateTime deletedAt;
    private Boolean isDeleted;

    @Enumerated(EnumType.STRING)
    private MemberStatus memberStatus;

    @OneToMany
    @JoinColumn(name = "board_id")
    private List<Board> boards = new ArrayList<>();

    public Member(String account, String password, String email, String name) {
        this.account = account;
        savePassword(password);
        this.email = email;
        this.name = name;
    }

    // 비즈니스 로직
    public void savePassword(String password) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        this.password = bCryptPasswordEncoder.encode(password);
    }

    public boolean matchPassword(String password) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder.matches(password, this.password);
    }

    public void setMemberName(String name) {
        this.name = name;
    }
}
