package noeasy.server.domain.type;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public enum TagType {
    LUNCH("LUNCH"),
    DINNER("DINNER"),
    WORKSHOP("WORKSHOP"),
    DINING_TOGETHER("DINING_TOGETHER");

    String content;
}
