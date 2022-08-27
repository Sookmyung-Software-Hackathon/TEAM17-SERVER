package noeasy.server.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class BoggleRequestDto {
    private String title;
    private String tag;
    private LocalDateTime date_time;
    private int max_person;
    private boolean anonymity;

}
