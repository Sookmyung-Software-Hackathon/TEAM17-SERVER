package noeasy.server.controller;

import lombok.RequiredArgsConstructor;
import noeasy.server.service.BoggleService;
import noeasy.server.util.ResponseTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/home")
public class HomeController {
    private final BoggleService boggleService;

    @GetMapping("/myboggle")
    public ResponseEntity<ResponseTemplate> readMyBoggleList(HttpServletRequest request) {
        String email = request.getUserPrincipal().getName();

        return ResponseEntity
                .ok(new ResponseTemplate(
                        200,
                        "성공",
                        boggleService.readByMemberEmail(email)
                ));
    }
}
