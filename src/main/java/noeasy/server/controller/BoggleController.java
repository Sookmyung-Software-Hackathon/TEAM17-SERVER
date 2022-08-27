package noeasy.server.controller;

import lombok.RequiredArgsConstructor;
import noeasy.server.domain.dto.BoggleRequestDto;
import noeasy.server.domain.type.TagType;
import noeasy.server.service.BoggleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/boggle")
public class BoggleController {
    private final BoggleService boggleService;

    @PostMapping("/generate")
    public ResponseEntity<?> generateBoggle(
            @RequestBody BoggleRequestDto requestDto,
            HttpServletRequest request
    ) {
        String email = request.getUserPrincipal().getName();

        return ResponseEntity
                .ok()
                .body(boggleService.generateBoggle(requestDto, email));
    }

    @PostMapping("/participate")
    public ResponseEntity<?> participateBoggle(
        @RequestParam String teamId,
        HttpServletRequest request
    ) {
        String email = request.getUserPrincipal().getName();

        return ResponseEntity
                .ok()
                .body(boggleService.participateBoggle(teamId, email));
    }

    @GetMapping
    public ResponseEntity<?> readAll(
            @RequestParam(required = false, defaultValue = "") List<TagType> tags,
            @RequestParam(required = false, defaultValue = "") String keyword
    ) {
        return ResponseEntity
                .ok()
                .body(boggleService.readAll(tags, keyword));
    }
}
