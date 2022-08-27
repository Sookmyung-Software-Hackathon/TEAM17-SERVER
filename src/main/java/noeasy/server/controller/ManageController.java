package noeasy.server.controller;

import lombok.RequiredArgsConstructor;
import noeasy.server.domain.dto.TeamRequestDto;
import noeasy.server.service.KakaoService;
import noeasy.server.service.TeamService;
import noeasy.server.util.ResponseTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequiredArgsConstructor
@RequestMapping("/manage")
public class ManageController {
    private final TeamService teamService;
    private final KakaoService kakaoService;

    @PostMapping("/team")
    public ResponseEntity<ResponseTemplate> createTeam(@RequestBody TeamRequestDto requestDto) {
        return ResponseEntity
                .ok(new ResponseTemplate(
                        200,
                        "성공",
                        teamService.createTeam(requestDto))
                );
    }

    @GetMapping("/login/kakao")
    public void  kakaoCallback(@RequestParam String code) {
        System.out.println("controller code :" + code);
        String access_Token = kakaoService.getKakaoAccessToken(code);
        System.out.println("controller access_token :" + access_Token);

        HashMap<String, Object> userInfo = kakaoService.getUserInfo(access_Token);
        System.out.println(userInfo);
    }

}
