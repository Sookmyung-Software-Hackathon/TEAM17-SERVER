package noeasy.server.controller;

import lombok.RequiredArgsConstructor;
import noeasy.server.service.TeamService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/team")
public class TeamController {
    private final TeamService teamService;

    @GetMapping("/ranking")
    public ResponseEntity<?> readTeamRanking() {
        return ResponseEntity
                .ok()
                .body(teamService.readTeamRanking());
    }
}
