package noeasy.server.service;

import lombok.AllArgsConstructor;
import noeasy.server.domain.Member;
import noeasy.server.domain.Team;
import noeasy.server.domain.dto.MemberDto;
import noeasy.server.repository.MemberRepository;
import noeasy.server.repository.team.TeamRepository;
import noeasy.server.util.exception.CustomException;
import noeasy.server.util.exception.ResponseCode;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@AllArgsConstructor
public class MemberService {

    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;
    private final TeamRepository teamRepository;

    public Member login(MemberDto.LoginDto loginDto){
        return memberRepository.findByEmail(loginDto.getEmail()).orElseThrow(
                () -> new CustomException(ResponseCode.MEMBER_NOT_FOUND)
        );
    }

    public Member signUp(MemberDto.SignUpDto signUpDto){
        MemberDto memberDto = new MemberDto();
        memberDto.setRoles(Collections.singletonList("ROLE_USER"));
        memberDto.setType("local");
        memberDto.setEmail(signUpDto.getEmail());
        memberDto.setBirthYear(signUpDto.getBirthYear());
        memberDto.setNickname(signUpDto.getNickname());
        memberDto.setPw(passwordEncoder.encode(signUpDto.getPw()));

        Team team = teamRepository.findById(signUpDto.getTeamId()).orElse(null);

        return memberRepository.save(new Member(memberDto, team));
    }
}
