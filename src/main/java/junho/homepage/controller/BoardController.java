package junho.homepage.controller;

import junho.homepage.domain.Board;
import junho.homepage.domain.Member;
import junho.homepage.domain.board.BoardDto;
import junho.homepage.repository.BoardRepository;
import junho.homepage.repository.MemberRepository;
import junho.homepage.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    private final BoardRepository boardRepository;

    private final MemberRepository memberRepository;

    @PostMapping("/board/post")
    public Long postBoard(@RequestBody BoardDto request) {
        Member member = memberRepository.findOne(request.getMemberId());
        Board board = new Board(request.getTitle(), request.getDescription(), member);
        Long id = boardService.createBoard(board);
        return id;
    }

    @DeleteMapping("/{board_id}")
    public void deleteBoard(@PathVariable(value = "board_id") Long boardId) {
        boardService.deleteBoard(boardId);
    }

    @PatchMapping("/board/{board_id}/modify")
    public void patchBoard(@PathVariable(value = "board_id") Long boardId, @RequestBody BoardDto request) {
        boardService.updateBoard(boardId, request);
    }

    @GetMapping("/board/{board_id}")
    public BoardDto findBoard(@PathVariable(value = "board_id") Long boardId) {
        Board board = boardRepository.findOne(boardId);
        return new BoardDto(board);
    }

    @GetMapping("/boards")
    public List<BoardDto> findAllBoards() {
        List<Board> boards = boardService.findBoards();
        return boards.stream()
                .map(o -> new BoardDto(o))
                .collect(Collectors.toList());
    }
}
