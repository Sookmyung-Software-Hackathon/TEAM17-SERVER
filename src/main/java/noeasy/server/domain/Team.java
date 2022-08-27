package noeasy.server.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import noeasy.server.domain.dto.TeamRequestDto;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Team extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(length = 24)
    String name;

    @Column
    double temperature = 0.0;

    public Team(TeamRequestDto teamRequestDto) {
        this.name = teamRequestDto.getName();
    }

}
