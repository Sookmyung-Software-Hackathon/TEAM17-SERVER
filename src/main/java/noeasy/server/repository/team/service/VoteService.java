package noeasy.server.repository.team.service;

import lombok.RequiredArgsConstructor;
import noeasy.server.config.security.JwtTokenProvider;
import noeasy.server.domain.*;
import noeasy.server.domain.dto.VoteDto;
import noeasy.server.domain.dto.VoteItemDto;
import noeasy.server.domain.dto.VoterRequestDto;
import noeasy.server.repository.Member.MemberRepository;
import noeasy.server.repository.VoteItemRepository;
import noeasy.server.repository.VoteRepository;
import noeasy.server.repository.VoterRepository;
import noeasy.server.repository.team.TeamRepository;
import noeasy.server.util.exception.CustomException;
import noeasy.server.util.exception.ResponseCode;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VoteService {

    private final VoteRepository voteRepository;
    private final VoterRepository voterRepository;
    private final VoteItemRepository voteItemRepository;
    private final TeamRepository teamRepository;
    private final JwtTokenProvider jwtTokenProvider;

    private final MemberRepository memberRepository;

    public void generateNewVote(String email, VoteDto voteDto){
        Team team = teamRepository.findTeamByMemberEmail(jwtTokenProvider.getUserEmail(email));
        LocalDateTime deadline = LocalDateTime.now();
        deadline = deadline.plusDays(1);

        Vote newVote = new Vote(voteDto.getTitle(), deadline, team);
        voteRepository.save(newVote);

        for (String itemContent : voteDto.getVoteContent()) {
            VoteItem voteItem = new VoteItem(newVote, itemContent);
            voteItemRepository.save(voteItem);
        }
    }

    public List<VoteDto.HomeDto> readMyVoteList(String email) {
        Member member = memberRepository.findByEmail(email).orElseThrow(
                () -> new CustomException(ResponseCode.MEMBER_NOT_FOUND)
        );
        List<VoteDto.HomeDto> myVoteList = new ArrayList<>();

        for (Vote vote : voteRepository.findByTeamId(member.getTeam().getId())) {
            VoteDto.HomeDto homeVoteDto = new VoteDto.HomeDto();
            homeVoteDto.setTitle(vote.getTitle());
            if (vote.getDeadline().compareTo(LocalDateTime.now()) >= 0) {
                homeVoteDto.setExpired("만료됨");
            } else if (LocalDateTime.now().getHour() - vote.getDeadline().getHour() >= 1) {
                homeVoteDto.setExpired("만료 " + (LocalDateTime.now().getHour() - vote.getDeadline().getHour()) + "시간 전");
            } else {
                homeVoteDto.setExpired("만료 " + (LocalDateTime.now().getMinute() - vote.getDeadline().getMinute()) + "분 전");
            }

            Long allVoterCount = voterRepository.countByVoteId(vote.getId());
            Double rate = (double) (allVoterCount / teamRepository.count());
            homeVoteDto.setParticipateRate("참여율 " + String.format("%.2f", rate));

            for (VoteItemDto.ListDto vl : homeVoteDto.getVoteList()) {
                VoteItemDto.ListDto listDto = new VoteItemDto.ListDto();
                listDto.setId(vl.getId());
                listDto.setContent(vl.getContent());
                Voter voter = voterRepository.findByMemberIdAndVoteId(member.getId(), vote.getId()).orElse(null);
                if (voter.getVoteItem() != null) {
                    listDto.setIsChecked(true);
                } else {
                    listDto.setIsChecked(false);
                }
                listDto.voteRate = (double) (voterRepository.countByVoteItemId(vl.getId()) / allVoterCount);
                homeVoteDto.getVoteList().add(listDto);
            }
            myVoteList.add(homeVoteDto);
        }
        return myVoteList;
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
