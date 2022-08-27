package noeasy.server.controller;

import lombok.RequiredArgsConstructor;
import noeasy.server.domain.dto.TeamRequestDto;
import noeasy.server.service.TeamService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/manage")
public class ManageController {
    private final TeamService teamService;

    @PostMapping("/team")
    public ResponseEntity<?> createTeam(@RequestBody TeamRequestDto requestDto) {
        return ResponseEntity
                .ok()
                .body(teamService.createTeam(requestDto));
    }

}
