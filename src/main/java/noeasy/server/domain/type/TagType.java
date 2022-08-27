package noeasy.server.domain.type;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public enum TagType {
    LUNCH(0, "LUNCH"),
    DINNER(1, "DINNER"),
    WORKSHOP(2, "WORKSHOP"),
    DINING_TOGETHER(3, "DINING_TOGETHER");

    private final int id;
    private final String tag;
}
