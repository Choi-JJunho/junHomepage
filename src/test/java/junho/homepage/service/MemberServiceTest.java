package junho.homepage.service;

import junho.homepage.domain.Member;
import junho.homepage.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    MemberService memberService;

    @Test
    public void 회원가입_로그인() throws Exception {
        //given
        createMember("abcd", "1234", "abc@naver.com", "테스터");
        //when
        Member result = memberRepository.findByaccount("abcd");

        assertEquals(true, memberService.login("abcd", "1234"));
        //then
    }

    private void createMember(String account, String password, String email, String 테스터) {
        Member member = new Member(account, password, email, 테스터);
        memberRepository.save(member);
    }

}