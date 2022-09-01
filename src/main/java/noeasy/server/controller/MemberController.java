package noeasy.server.controller;

import lombok.RequiredArgsConstructor;
import noeasy.server.config.security.JwtTokenProvider;
import noeasy.server.domain.Member;
import noeasy.server.domain.dto.MemberDto;
import noeasy.server.repository.team.service.MemberService;
import noeasy.server.util.ResponseTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody MemberDto.LoginDto loginDto){

        Member member = memberService.login(loginDto);

        return ResponseEntity
                .ok(new ResponseTemplate(
                        200,
                        "로그인 성공",
                        jwtTokenProvider.createToken(member.getEmail(), member.getRoles())
                        )
                );
    }

    @PostMapping("/signup")
    public ResponseEntity<Object> signUp(@RequestBody MemberDto.SignUpDto signUpDto){

        Member member = memberService.signUp(signUpDto);

        return ResponseEntity
                .ok(new ResponseTemplate(
                                200,
                                "회원가입 성공",
                                jwtTokenProvider.createToken(member.getEmail(), member.getRoles())
                        )
                );
    }
}
