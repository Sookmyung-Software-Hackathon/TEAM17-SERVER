package noeasy.server.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import noeasy.server.domain.dto.BoggleRequestDto;
import noeasy.server.util.RandomGenerator;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

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

    @Column(length = 24)
    private String tag;

    @Column
    private LocalDateTime date_time;

    @Column
    private int max_person;

    @Column
    private boolean anonymity;

    @OneToOne(fetch = FetchType.LAZY)
    private Participant leader = null;

    @Column
    private boolean success = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "boggle")
    private List<Participant> participantList = List.of();

    public Boggle(BoggleRequestDto requestDto, Team team) {
        this.title = requestDto.getTitle();
        this.tag = requestDto.getTag();
        this.date_time = requestDto.getDate_time();
        this.team = team;
        this.max_person = requestDto.getMax_person();
        this.anonymity = requestDto.isAnonymity();
    }

    public void setLeader(Participant leader) {
        this.leader = leader;
    }

    public void setSuccess(boolean isSuccess) {
        this.success = isSuccess;
    }
}
