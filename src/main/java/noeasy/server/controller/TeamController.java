package noeasy.server.controller;

import lombok.RequiredArgsConstructor;
import noeasy.server.service.TeamService;
import noeasy.server.util.ResponseTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/team")
public class TeamController {
    private final TeamService teamService;

    @GetMapping("/ranking")
    public ResponseEntity<ResponseTemplate> readTeamRanking() {

        return ResponseEntity
                .ok(new ResponseTemplate(
                        200,
                        "성공",
                        teamService.readTeamRanking())
                );
    }
}
