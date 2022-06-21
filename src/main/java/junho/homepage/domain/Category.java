package junho.homepage.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class Category {

    @Id @GeneratedValue
    @Column(name = "category_id")
    private Long id;

    private String name;

    @OneToMany
    @JoinColumn(name = "category_board_id")
    private List<CategoryBoard> categoryBoards;
}
