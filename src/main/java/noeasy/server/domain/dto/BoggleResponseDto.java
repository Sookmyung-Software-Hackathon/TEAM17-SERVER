package noeasy.server.domain.dto;

import lombok.Getter;
import lombok.Setter;
import noeasy.server.domain.Boggle;
import java.time.LocalDateTime;

@Getter @Setter
public class BoggleResponseDto {
    private String id;
    private String title;
    private String tag;
    private LocalDateTime date_time;
    private int current_person;
    private int max_person;
    private boolean anonymity;
    private boolean success;

    public BoggleResponseDto(Boggle boggle) {
        this.id = boggle.getId();
        this.title = boggle.getTitle();
        this.tag = boggle.getTag();
        this.date_time = boggle.getDate_time();
        this.current_person = boggle.getParticipantList().size();
        this.max_person = boggle.getMax_person();
        this.anonymity = boggle.isAnonymity();
        this.success = boggle.isSuccess();
    }
}
