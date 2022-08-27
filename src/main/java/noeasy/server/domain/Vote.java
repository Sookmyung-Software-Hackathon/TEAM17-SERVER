package noeasy.server.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Vote extends Timestamped {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name ="team_id")
    private Team team;

    @NotNull
    private String title;

    @NotNull
    private LocalDateTime deadline;

    Boolean expired = true;

    public Vote(String title, LocalDateTime deadline, Team team){
        this.title = title;
        this.deadline = deadline;
        this.team = team;
    }
}
