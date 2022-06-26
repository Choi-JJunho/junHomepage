package junho.homepage.service;

import junho.homepage.domain.Member;
import junho.homepage.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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

    // 좋은 테스트 방식은 아님. 단위테스트가 필요함.
    @Test
    public void 회원가입_로그인() throws Exception {
        //given
        createMember();
        //when
        Member result = memberRepository.findByaccount("abcd");
        //then
        assertEquals(true, memberService.login("abcd", "1234"));
    }

    @Test
    public void 회원정보_갱신() throws Exception {
        //given
        Member tester = createMember();
        //when
        memberService.updateMember(tester.getId(),"helloMan");
        //then
        assertEquals("helloMan", memberRepository.findOne(tester.getId()).getName());

    }

    @Test
    public void 사용자_삭제() throws Exception{
        Member member = createMember();
        memberRepository.deleteMember(member.getId());
        Member one = memberRepository.findOne(member.getId());

        assertEquals(null, one);
    }

    private Member createMember() {
        Member member = new Member("abcd", "1234", "abc@naver.com", "테스터");
        memberRepository.save(member);
        return member;
    }

}