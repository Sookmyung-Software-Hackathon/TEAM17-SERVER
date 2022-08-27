package noeasy.server.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import noeasy.server.domain.dto.BoggleRequestDto;
import noeasy.server.domain.type.TagType;
import noeasy.server.util.RandomGenerator;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "boggle")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Boggle extends Timestamped {
    @Id
    @GeneratedValue(generator = RandomGenerator.generatorName)
    @GenericGenerator(name = RandomGenerator.generatorName, strategy = "noeasy.server.util.RandomGenerator")
    @Column
    private String id;

    @Column(length = 24)
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(length = 24)
    private TagType tag;

    @Column
    private LocalDateTime date_time;

    @Column
    private int max_person;

    @Column
    private boolean anonymity;

    @OneToOne(fetch = FetchType.LAZY)
    private Participant leader;

    @Column
    private boolean success = false;

    @ManyToOne(fetch = FetchType.LAZY)
    private Team team;

    public Boggle(BoggleRequestDto requestDto, Participant leader, Team team) {
        this.title = requestDto.getTitle();
        this.tag = requestDto.getTag();
        this.date_time = requestDto.getDate_time();
        this.leader = leader;
        this.team = team;
        this.max_person = requestDto.getMax_person();
        this.anonymity = requestDto.isAnonymity();
    }
}
