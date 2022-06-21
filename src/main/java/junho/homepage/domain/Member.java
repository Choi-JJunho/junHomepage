package junho.homepage.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String account;
    private String password;
    private String email;
    private String nickname;

    private LocalDateTime createdAt;
    private LocalDateTime deletedAt;
    private Boolean isDeleted;

    @Enumerated(EnumType.STRING)
    private MemberStatus memberStatus;

    @OneToMany
    @JoinColumn(name = "board_id")
    private List<Board> boards = new ArrayList<>();
}
