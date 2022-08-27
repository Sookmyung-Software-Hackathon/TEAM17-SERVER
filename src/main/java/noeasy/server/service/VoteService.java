package noeasy.server.service;

import lombok.RequiredArgsConstructor;
import noeasy.server.config.security.JwtTokenProvider;
import noeasy.server.domain.Member;
import noeasy.server.domain.Team;
import noeasy.server.domain.Vote;
import noeasy.server.domain.VoteItem;
import noeasy.server.domain.dto.MemberDto;
import noeasy.server.domain.dto.VoteDto;
import noeasy.server.repository.MemberRepository;
import noeasy.server.repository.VoteItemRepository;
import noeasy.server.repository.VoteRepository;
import noeasy.server.repository.team.TeamRepository;
import noeasy.server.util.exception.CustomException;
import noeasy.server.util.exception.ResponseCode;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VoteService {

    private final VoteRepository voteRepository;
    private final VoteItemRepository voteItemRepository;
    private final TeamRepository teamRepository;
    private final JwtTokenProvider jwtTokenProvider;

    private final MemberRepository memberRepository;

    public void generateNewVote(String token, VoteDto voteDto){

        Team team = teamRepository.findTeamByMemberEmail(jwtTokenProvider.getUserEmail(token));
        LocalDateTime deadline = LocalDateTime.now();
        deadline = deadline .plusDays(1);

        Vote newVote = new Vote(voteDto.getTitle(),deadline, team);
        voteRepository.save(newVote);

        for(String itemContent : voteDto.getVoteContent()){
            VoteItem voteItem = new VoteItem(newVote, itemContent);
            voteItemRepository.save(voteItem);
        }
    }

    public List<VoteDto> readMyVoteList(String email){
        Member member = memberRepository.findByEmail(email).orElseThrow(
                ()-> new CustomException(ResponseCode.MEMBER_NOT_FOUND)
        );

        for( Vote vote : voteRepository.findByMemberId(member.getId())){

        }
    }

    public void participateVote(String token, VoteDto voteDto){

        Member member = memberRepository.findByEmail(jwtTokenProvider.getUserEmail(token)).orElseThrow(
                ()-> new CustomException(ResponseCode.MEMBER_NOT_FOUND)
        );



        LocalDateTime deadline = LocalDateTime.now();
        deadline = deadline .plusDays(1);

        Vote newVote = new Vote(voteDto.getTitle(),deadline, team);
        voteRepository.save(newVote);

        for(String itemContent : voteDto.getVoteContent()){
            VoteItem voteItem = new VoteItem(newVote, itemContent);
            voteItemRepository.save(voteItem);
        }
    }
}
