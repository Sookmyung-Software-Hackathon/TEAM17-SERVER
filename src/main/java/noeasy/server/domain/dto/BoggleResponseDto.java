package noeasy.server.domain.dto;

import noeasy.server.domain.Boggle;
import noeasy.server.domain.type.TagType;
import java.time.LocalDateTime;

public class BoggleResponseDto {
    private String id;
    private String title;
    private TagType tag;
    private LocalDateTime date_time;
    private int max_person;
    private boolean anonymity;
    private boolean success;

    public BoggleResponseDto(Boggle boggle) {
        this.id = boggle.getId();
        this.title = boggle.getTitle();
        this.tag = boggle.getTag();
        this.date_time = boggle.getDate_time();
        this.max_person = boggle.getMax_person();
        this.anonymity = boggle.isAnonymity();
        this.success = boggle.isSuccess();
    }
}
