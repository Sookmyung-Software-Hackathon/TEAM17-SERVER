package noeasy.server.service;

import lombok.RequiredArgsConstructor;
import noeasy.server.config.security.JwtTokenProvider;
import noeasy.server.domain.*;
import noeasy.server.domain.dto.VoteDto;
import noeasy.server.domain.dto.VoterRequestDto;
import noeasy.server.repository.MemberRepository;
import noeasy.server.repository.VoteItemRepository;
import noeasy.server.repository.VoteRepository;
import noeasy.server.repository.VoterRepository;
import noeasy.server.repository.team.TeamRepository;
import noeasy.server.util.exception.CustomException;
import noeasy.server.util.exception.ResponseCode;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VoteService {

    private final VoteRepository voteRepository;
    private final VoteItemRepository voteItemRepository;
    private final TeamRepository teamRepository;
    private final JwtTokenProvider jwtTokenProvider;

    private final MemberRepository memberRepository;

    private final VoterRepository voterRepository;

    public void generateNewVote(String email, VoteDto voteDto){
        Team team = teamRepository.findTeamByMemberEmail(jwtTokenProvider.getUserEmail(email));
        LocalDateTime deadline = LocalDateTime.now();
        deadline = deadline .plusDays(1);

        Vote newVote = new Vote(voteDto.getTitle(),deadline, team);
        voteRepository.save(newVote);

        for(String itemContent : voteDto.getVoteContent()){
            VoteItem voteItem = new VoteItem(newVote, itemContent);
            voteItemRepository.save(voteItem);
        }
    }

    public List<VoteDto.HomeDto> readMyVoteList(String email){
        Member member = memberRepository.findByEmail(email).orElseThrow(
                ()-> new CustomException(ResponseCode.MEMBER_NOT_FOUND)
        );

        return voteRepository.findByMemberId(member.getId()).stream().map(v -> new VoteDto.HomeDto(v)).collect(Collectors.toList());
    }

    public VoteDto.HomeDto participateVote(String email, VoterRequestDto requestDto){
        Member member = memberRepository.findByEmail(jwtTokenProvider.getUserEmail(email)).orElseThrow(
                ()-> new CustomException(ResponseCode.MEMBER_NOT_FOUND)
        );

        Vote vote = voteRepository.findById(requestDto.getVoteId()).orElseThrow(
                () -> new CustomException(ResponseCode.VOTE_NOT_FOUND)
        );

        VoteItem voteItem = voteItemRepository.findById(requestDto.getVoteItemId()).orElseThrow(
                () -> new CustomException(ResponseCode.VOTE_NOT_FOUND)
        );

        Voter voter = new Voter(vote, voteItem, member);
        voterRepository.save(voter);

        return new VoteDto.HomeDto(vote);
    }
}
