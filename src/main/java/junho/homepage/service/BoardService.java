package junho.homepage.service;

import junho.homepage.domain.Board;
import junho.homepage.domain.board.BoardDto;
import junho.homepage.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    public Long createBoard(Board board) {
        boardRepository.save(board);
        Long id = board.getId();
        return id;
    }

    @Transactional(readOnly = true)
    public List<Board> searchBoardByMemberId(Long memberId) {
        return boardRepository.findByMember(memberId);
    }

    // TODO : 기능구현
    public void updateBoard(Long boardId, BoardDto request) {
        Board board = boardRepository.findOne(boardId);
        board.updateBoard(request.getTitle(), request.getDescription());
        boardRepository.save(board);
    }

    public void deleteBoard(Long boardId) {
        boardRepository.deleteBoard(boardId);
    }

    public List<Board> findBoards() {
        return boardRepository.findAll();
    }

}
