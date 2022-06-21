package junho.homepage.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class CategoryBoard {
    @Id @GeneratedValue
    @Column(name = "category_board_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    private LocalDateTime createdAt;
}
