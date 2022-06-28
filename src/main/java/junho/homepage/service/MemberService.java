package junho.homepage.service;

import junho.homepage.domain.Member;
import junho.homepage.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public List<Member> findAll() {
        return memberRepository.findAll();
    }
    // TODO : RequestMemberDTO 따로 만들기
    public Long addMember(Member member) {
        // ValidDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void ValidDuplicateMember(Member member) {
        if (member.getAccount() == null){
            throw new NullPointerException("아이디값이 비어있습니다.");
        } else if (memberRepository.findByaccount(member.getAccount()) != null) {
            throw new DuplicateKeyException("동일한 아이디가 존재합니다.");
        }
        if (memberRepository.findByname(member.getName()) != null) {
            throw new DuplicateKeyException("동일한 이름이 존재합니다.");
        }
        if (memberRepository.findByemail(member.getEmail()) != null) {
            throw new DuplicateKeyException("동일한 이메일이 존재합니다.");
        }
    }

    @Transactional(readOnly = true) // 향후 토큰 발급하는 방식으로 리팩토링 예정
    public Long login(String account, String password) {
        Member member = memberRepository.findByaccount(account);
        if(member == null) {
            throw new NullPointerException("존재하지 않는 아이디입니다");
        } else {
            return member.matchPassword(password) ? member.getId() : -1;
        }
    }

    // 영속상태의 객체에 상태변화가 일어났으므로 변경감지로 인하여 준영속 엔티티가 수정됨.
    public void updateMember(Long memberId, String name) {
        Member member = memberRepository.findOne(memberId);
        member.setMemberName(name);
    }
}
