package junho.homepage.controller;

import junho.homepage.domain.Member;
import junho.homepage.domain.member.CreateMemberRequest;
import junho.homepage.domain.member.LoginRequest;
import junho.homepage.domain.member.MemberDto;
import junho.homepage.repository.MemberRepository;
import junho.homepage.service.JWTService;
import junho.homepage.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;

    private final MemberRepository memberRepository;

    private final JWTService jwtService;

    @GetMapping("/members")
    public List<MemberDto> members() {
        List<Member> members = memberRepository.findAll();
        System.out.println(members);
        List<MemberDto> result = members.stream()
                .map(m -> new MemberDto(m.getId(), m.getEmail(), m.getName()))
                .collect(Collectors.toList());
        return result;
    }

    @PostMapping("/signin")
    public MemberDto createMember(@RequestBody @Valid CreateMemberRequest request) {
        Member member1 = new Member(request.getAccount(), request.getPassword(), request.getEmail(), request.getName());
        Long id = memberService.addMember(member1);
        return new MemberDto(id, request.getEmail(), request.getName());
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) throws Exception {
        String resultJwt = "none";
        Member member = new Member(request.getAccount(), request.getPassword());
        Long id = memberService.login(member.getAccount(), member.getPassword());
        if(id > 0) {
            resultJwt = jwtService.createJwt(member);
        }
        return resultJwt;
    }
}


