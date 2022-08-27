package noeasy.server.service;

import lombok.RequiredArgsConstructor;
import noeasy.server.domain.Boggle;
import noeasy.server.domain.Member;
import noeasy.server.domain.Participant;
import noeasy.server.domain.Team;
import noeasy.server.domain.dto.BoggleRequestDto;
import noeasy.server.domain.dto.BoggleResponseDto;
import noeasy.server.repository.boggle.BoggleRepository;
import noeasy.server.repository.MemberRepository;
import noeasy.server.repository.ParticipantRepository;
import noeasy.server.repository.team.TeamRepository;
import noeasy.server.util.exception.CustomException;
import noeasy.server.util.exception.ResponseCode;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoggleService {
    private final BoggleRepository boggleRepository;

    private final ParticipantRepository participantRepository;

    private final MemberRepository memberRepository;
    private final TeamRepository teamRepository;

    public String participateBoggle(String boggleId, String email) {
        Member member = memberRepository.findByEmail(email).orElseThrow(
                () -> new CustomException(ResponseCode.MEMBER_NOT_FOUND)
        );
        Boggle boggle = boggleRepository.findById(boggleId).orElseThrow(
                () -> new CustomException(ResponseCode.BOGGLE_NOT_FOUND)
        );

        Participant participant = new Participant(member, boggle);
        participantRepository.save(participant);

        return "ok";
    }

    public List<BoggleResponseDto> readAll(List<String> tags, String keyword) {
        return boggleRepository.findAllBoggleBySearch(tags, keyword)
                .stream().map(b -> new BoggleResponseDto(b))
                .collect(Collectors.toList());
    }

    public BoggleResponseDto generateBoggle(BoggleRequestDto requestDto, String email) {
        Member member = memberRepository.findByEmail(email).orElseThrow(
                () -> new CustomException(ResponseCode.MEMBER_NOT_FOUND)
        );

        Boggle boggle = new Boggle(requestDto, member.getTeam());
        boggleRepository.save(boggle);

        Participant leader = new Participant(member, boggle);
        participantRepository.save(leader);

        boggle.setLeader(leader);
        boggleRepository.save(boggle);

        return new BoggleResponseDto(boggle);
    }

    public List<BoggleResponseDto> readByMemberEmail(String email) {
        Member member = memberRepository.findByEmail(email).orElseThrow(
                () -> new CustomException(ResponseCode.MEMBER_NOT_FOUND)
        );

        return participantRepository.findByMember(member)
                .stream().map(p -> new BoggleResponseDto(p.getBoggle()))
                .collect(Collectors.toList());
    }

    public BoggleResponseDto readDetail(String boggleId) {
        Boggle boggle = boggleRepository.findById(boggleId).orElseThrow(
                () -> new CustomException(ResponseCode.BOGGLE_NOT_FOUND)
        );

        return new BoggleResponseDto(boggle);
    }

    public String completeBoggle(String boggleId) {
        Boggle boggle = boggleRepository.findById(boggleId).orElseThrow(
                () -> new CustomException(ResponseCode.BOGGLE_NOT_FOUND)
        );
        boggle.setSuccess(true);
        boggleRepository.save(boggle);

        Team team = boggle.getTeam();
        team.setTemperature(team.getTemperature() + 0.5);
        teamRepository.save(team);

        return "ok";
    }
}
