package junho.homepage.service;

import junho.homepage.domain.Board;
import junho.homepage.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    // TODO : RequsetBoard DTO 따로 만들기
    public void createBoard(Board board) {
        boardRepository.save(board);
    }

    // TODO : Entity가 외부로 노출된다!! ResponseBoardDTO 만들기
    @Transactional(readOnly = true)
    public List<Board> SearchBoard(Long memberId) {
        return boardRepository.findByMember(memberId);
    }

    @Transactional(readOnly = true)
    public void updateBoard(Board board){
        Board board1 = new Board();

        boardRepository.save(board);
    }

    public void deleteBoard(Long boardId) {
        boardRepository.deleteBoard(boardId);
    }
}
