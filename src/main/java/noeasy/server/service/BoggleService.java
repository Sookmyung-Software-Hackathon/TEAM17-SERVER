package noeasy.server.service;

import lombok.RequiredArgsConstructor;
import noeasy.server.domain.Boggle;
import noeasy.server.domain.Member;
import noeasy.server.domain.Participant;
import noeasy.server.domain.dto.BoggleRequestDto;
import noeasy.server.domain.dto.BoggleResponseDto;
import noeasy.server.repository.boggle.BoggleRepository;
import noeasy.server.repository.MemberRepository;
import noeasy.server.repository.ParticipantRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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

//    public List<BoggleResponseDto> readAll(List<String> tags, String keyword) {
//        List<Boggle> boggleList = boggleRepository.findAllByTag(tags, keyword)
//
//    }
//
//    public BoggleResponseDto generateBoggle(BoggleRequestDto requestDto) {
//
//    }
}
