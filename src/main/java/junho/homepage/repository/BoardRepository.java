package junho.homepage.repository;

import junho.homepage.domain.Board;
import junho.homepage.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardRepository {

    private final EntityManager em;

    public void save(Board board) {
        em.persist(board);
    }

    public Board findOne(Long id) {
        return em.find(Board.class, id);
    }

    public List<Board> findAll() {
        return em.createQuery("select b from Board b", Board.class).getResultList();
    }

    public List<Board> findByMember(Long memberId) {
        return em.createQuery("select b from Board b where b.member.id = :id", Board.class)
                .setParameter("id", memberId)
                .getResultList();
    }

    public void deleteBoard(Long boardId) {
        em.remove(findOne(boardId));
        // em.createQuery("update Board b set b.isDeleted = true", Board.class);
    }
}
