package junho.homepage.domain.board;

import junho.homepage.domain.CategoryBoard;
import junho.homepage.domain.Member;
import lombok.Data;

import java.util.List;

@Data
public class BoardDto {
    // private Long id;
    private String title;
    private String description;
    private Long memberId;
    // TODO : 차후 구현
    // private List<CategoryBoard> categoryBoards;

    public BoardDto(String title, String description, Long memberId) {
        this.title = title;
        this.description = description;
        this.memberId = memberId;
    }

}