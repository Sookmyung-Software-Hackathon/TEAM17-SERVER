package noeasy.server.controller;

import lombok.RequiredArgsConstructor;
import noeasy.server.domain.dto.BoggleRequestDto;
import noeasy.server.service.BoggleService;
import noeasy.server.util.ResponseTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/boggle")
public class BoggleController {
    private final BoggleService boggleService;

    @PostMapping("/generate")
    public ResponseEntity<ResponseTemplate> generateBoggle(
            @RequestBody BoggleRequestDto requestDto,
            HttpServletRequest request
    ) {
        String email = request.getUserPrincipal().getName();

        return ResponseEntity
                .ok(new ResponseTemplate(
                        200,
                        "성공",
                        boggleService.generateBoggle(requestDto, email))
                );
    }

    @PostMapping("/participate")
    public ResponseEntity<ResponseTemplate> participateBoggle(
        @RequestParam String teamId,
        HttpServletRequest request
    ) {
        String email = request.getUserPrincipal().getName();

        return ResponseEntity
                .ok(new ResponseTemplate(
                        200,
                        "성공",
                        boggleService.participateBoggle(teamId, email))
                );
    }

    @GetMapping
    public ResponseEntity<ResponseTemplate> readAll(
            @RequestParam(required = false, defaultValue = "") List<String> tags,
            @RequestParam(required = false, defaultValue = "") String keyword
    ) {
        return ResponseEntity
                .ok(new ResponseTemplate(
                        200,
                        "성공",
                        boggleService.readAll(tags, keyword))
                );
    }

    @GetMapping("/detail/{boggleId}")
    public ResponseEntity<ResponseTemplate> readDetail(@PathVariable String boggleId) {
        return ResponseEntity
                .ok(new ResponseTemplate(
                        200,
                        "성공",
                        boggleService.readDetail(boggleId))
                );
    }

    @PatchMapping("/success")
    public ResponseEntity<ResponseTemplate> completeBoggle(@RequestParam String boggleId) {
        return ResponseEntity
                .ok(new ResponseTemplate(
                        200,
                        "성공",
                        boggleService.completeBoggle(boggleId))
                );
    }
}
