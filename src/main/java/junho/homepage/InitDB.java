package junho.homepage;

import junho.homepage.domain.Board;
import junho.homepage.domain.Member;
import junho.homepage.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

@Component
@RequiredArgsConstructor
public class InitDB {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit1();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    public static class InitService {
        private final EntityManager em;
        public void dbInit1() {
            Member member = new Member("abc", "123", "abc@naver.com", "김개똥");
            Member member2 = new Member("hello", "1234", "abc@naver.com", "가나다");
            Member member3 = new Member("bye", "12345", "abc@naver.com", "도레미");
            Board board = new Board("제목", "본문", member);
            Board board2 = new Board("제목2", "본문2", member2);
            Board board3 = new Board("제목3", "본문3", member3);
            em.persist(member);
            em.persist(member2);
            em.persist(member3);
            em.persist(board);
            em.persist(board2);
            em.persist(board3);
        }
    }
}
