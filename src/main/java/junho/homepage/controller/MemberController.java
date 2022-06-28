package junho.homepage.controller;

import junho.homepage.domain.Member;
import junho.homepage.domain.member.request.CreateMemberRequest;
import junho.homepage.domain.member.request.LoginRequest;
import junho.homepage.domain.member.response.MemberDto;
import junho.homepage.repository.MemberRepository;
import junho.homepage.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    private final MemberRepository memberRepository;

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

    // TODO : response Data 정형화
    @PostMapping("/login")
    public MemberDto login(@RequestBody LoginRequest request) {
        Member member1 = new Member(request.getAccount(), request.getPassword());
        Long id = memberService.login(member1.getAccount(), member1.getPassword());
        return new MemberDto(id);
    }
}


