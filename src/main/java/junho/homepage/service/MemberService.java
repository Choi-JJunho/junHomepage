package junho.homepage.service;

import junho.homepage.domain.Member;
import junho.homepage.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public void signin(Member member) {
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

        memberRepository.save(member);
    }

    @Transactional(readOnly = true) // 향후 토큰 발급하는 방식으로 리팩토링 예정
    public Boolean login(String account, String password) {
        Member member = memberRepository.findByaccount(account);
        if(member == null) {
            throw new NullPointerException("존재하지 않는 아이디입니다");
        } else {
            return member.matchPassword(password);
        }
    }



}
