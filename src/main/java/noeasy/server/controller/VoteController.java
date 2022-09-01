package noeasy.server.controller;

import lombok.RequiredArgsConstructor;
import noeasy.server.domain.dto.VoteDto;
import noeasy.server.domain.dto.VoterRequestDto;
import noeasy.server.repository.team.service.VoteService;
import noeasy.server.util.NoDataResponseTemplate;
import noeasy.server.util.ResponseTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/vote")
public class VoteController {

    private final VoteService voteService;
    @PostMapping("/generate")
    public ResponseEntity<Object> generateVote(@RequestBody VoteDto voteDto, HttpServletRequest request){
        String email = request.getUserPrincipal().getName();

        voteService.generateNewVote(email,voteDto);

        return ResponseEntity
                .ok(new NoDataResponseTemplate(
                                200,
                                "투표 생성 성공"
                        )
                );
    }

        @PostMapping("/participate")
        public ResponseEntity<Object> generateVote(@RequestBody VoterRequestDto requestDto, HttpServletRequest request){
            String email = request.getUserPrincipal().getName();

            return ResponseEntity
                    .ok(new ResponseTemplate(
                                    200,
                                    "투표 생성 성공",
                            voteService.participateVote(email, requestDto))
                    );
        }
}
