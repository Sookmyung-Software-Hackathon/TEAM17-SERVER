package noeasy.server.controller;

import lombok.RequiredArgsConstructor;
import noeasy.server.config.security.JwtTokenProvider;
import noeasy.server.domain.Member;
import noeasy.server.domain.Team;
import noeasy.server.domain.Vote;
import noeasy.server.domain.dto.MemberDto;
import noeasy.server.domain.dto.VoteDto;
import noeasy.server.service.VoteService;
import noeasy.server.util.NoDataResponseTemplate;
import noeasy.server.util.ResponseTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/vote")
public class VoteController {

    private final VoteService voteService;
    @PostMapping("/generate")
    public ResponseEntity<Object> generateVote(@RequestHeader("X-AUTH-TOKEN") String token, @RequestBody VoteDto voteDto){

        voteService.generateNewVote(token,voteDto);

        return ResponseEntity
                .ok(new NoDataResponseTemplate(
                                200,
                                "투표 생성 성공"
                        )
                );
    }

//    @PostMapping("/participate")
//    public ResponseEntity<Object> generateVote(@RequestHeader("X-AUTH-TOKEN") String token, @RequestBody String voteItemId){
//
//        voteService.generateNewVote(token,voteDto);
//
//        return ResponseEntity
//                .ok(new ResponseTemplate(
//                                200,
//                                "투표 생성 성공",
//                                voteDto
//                        )
//                );
//    }
}
