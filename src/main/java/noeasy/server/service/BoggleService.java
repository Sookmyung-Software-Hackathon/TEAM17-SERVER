package noeasy.server.service;

import lombok.RequiredArgsConstructor;
import noeasy.server.domain.Boggle;
import noeasy.server.domain.Member;
import noeasy.server.domain.Participant;
import noeasy.server.domain.dto.BoggleRequestDto;
import noeasy.server.domain.dto.BoggleResponseDto;
import noeasy.server.domain.type.TagType;
import noeasy.server.repository.boggle.BoggleRepository;
import noeasy.server.repository.MemberRepository;
import noeasy.server.repository.ParticipantRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoggleService {
    private final BoggleRepository boggleRepository;
    private final ParticipantRepository participantRepository;
    private final MemberRepository memberRepository;

    public String participateBoggle(String boggleId, String email) {
        Member member = memberRepository.findByEmail(email).orElseThrow(
                //TODO: Exception 추가
        );
        Boggle boggle = boggleRepository.findById(boggleId).orElseThrow(
                //TODO: Exception 추가
        );

        Participant participant = new Participant(member, boggle);
        participantRepository.save(participant);

        return "ok";
    }

    public List<BoggleResponseDto> readAll(List<TagType> tags, String keyword) {
        return boggleRepository.findAllBoggleBySearch(tags, keyword)
                .stream().map(b -> new BoggleResponseDto(b))
                .collect(Collectors.toList());
    }

    public BoggleResponseDto generateBoggle(BoggleRequestDto requestDto, String email) {
        Member member = memberRepository.findByEmail(email).orElseThrow(
                //TODO: Exception 처리
        );

        Boggle boggle = new Boggle(requestDto, member.getTeam());
        Participant leader = new Participant(member, boggle);
        boggle.setLeader(leader);

        participantRepository.save(leader);
        boggleRepository.save(boggle);

        return new BoggleResponseDto(boggle);
    }
}
