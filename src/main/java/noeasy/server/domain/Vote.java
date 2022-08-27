package noeasy.server.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Vote extends Timestamped {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name ="team_id")
    private Team team;

    @NotNull
    private String title;

    @NotNull
    private LocalDateTime deadline;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vote")
    private List<Voter> voterList = List.of();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vote")
    private List<VoteItem> voteItemList = List.of();

    Boolean expired = true;

    public Vote(String title, LocalDateTime deadline, Team team){
        this.title = title;
        this.deadline = deadline;
        this.team = team;
    }
}
