package junho.homepage.controller;

import junho.homepage.domain.Board;
import junho.homepage.domain.Member;
import junho.homepage.domain.board.BoardDto;
import junho.homepage.repository.MemberRepository;
import junho.homepage.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    private final MemberRepository memberRepository;

    @PostMapping("/board/post")
    public Long postBoard(@RequestBody BoardDto request) {
        Member member = memberRepository.findOne(request.getMemberId());
        Board board = new Board(request.getTitle(), request.getDescription(), member);
        Long id = boardService.createBoard(board);
        return id;
    }
}
