package junho.homepage.domain.board;

import com.fasterxml.jackson.annotation.JsonIgnore;
import junho.homepage.domain.Board;
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

    @JsonIgnore
    private Long hitCount = (long) 0;
    // TODO : 차후 구현
    // private List<CategoryBoard> categoryBoards;

    public BoardDto(Board board) {
        this.title = board.getTitle();
        this.description = board.getDescription();
        this.memberId = board.getMember().getId();
    }

    public BoardDto(String title, String description, Long memberId) {
        this.title = title;
        this.description = description;
        this.memberId = memberId;
    }
}